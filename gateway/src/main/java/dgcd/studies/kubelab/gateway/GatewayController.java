package dgcd.studies.kubelab.gateway;

import dgcd.studies.kubelab.api.RequestDto;
import dgcd.studies.kubelab.api.ResponseDto;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@Slf4j
@RestController
public class GatewayController {

    private final AtomicLong counter = new AtomicLong(0L);
    private long previousCounter = 0L;
    private String hostname;

    @Value("${spring.profiles.active:none}")
    private String profile;

    @Value("${kubelab.gateway.backend.url:}")
    private String serverUrl;

    @Value("${kubelab.gateway.secret:none}")
    private String secret;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${kubelab.gateway.log.stats:true}")
    private Boolean logStats;

    @Value("${kubelab.gateway.log.requests:true}")
    private Boolean logRequests;

    @Value("${kubelab.gateway.log.responses:true}")
    private Boolean logResponses;


    @PostConstruct
    public void init() throws UnknownHostException {
        hostname = InetAddress.getLocalHost().getHostName();
    }

    @Scheduled(fixedDelay = 1000)
    public void statistics() {
        if (!logStats) {
            return;
        }
        log.info("requests per second: {}", counter.get() - previousCounter);
        previousCounter = counter.get();
    }

//    @SuppressWarnings("unused")
//    @Scheduled(fixedDelay = 15000)
//    public void testPollBack() {
//        try {
//            var responseDto = get(new RequestDto("test", 666L, null, null));
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//    }

    @GetMapping(value = "/test", produces = TEXT_PLAIN_VALUE)
    public String getData() {
        String responseString;
        try {
            var responseDto = get(new RequestDto("test", 666L, null, null));
            responseString = responseDto.getBackendName();
        } catch (Exception e) {
            responseString = e.getMessage();
            log.error(e.getMessage());
        }

        return String.format("""
                        java version: %s
                        %s (build %s, %s)
                        current time: %s
                        gateway instance: %s
                        backend response: %s
                        spring profile: %s
                        """,
                System.getProperty("java.version"),
                System.getProperty("java.vm.name"),
                System.getProperty("java.vm.version"),
                System.getProperty("java.vm.info"),
                LocalDateTime.now(),
                hostname,
                responseString,
                profile
        );
    }


    @PostMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
    public ResponseDto get(@RequestBody RequestDto requestDto) {
        if (logRequests) {
            log.info("get(): requestDto: {}", requestDto);
        }

        var newRequestDto = new RequestDto(
                requestDto.getRequestName(),
                requestDto.getRequestId(),
                hostname + "+" + secret,
                counter.incrementAndGet()
        );

        try {
            if (!StringUtils.hasLength(serverUrl)) {
                log.error("no url for request!");
                return null;
            }
            var responseEntity = restTemplate.postForEntity(serverUrl, newRequestDto, ResponseDto.class);
            var responseDto = responseEntity.getBody();
            var newResponseDto = responseDto == null ? null : new ResponseDto(
                    responseDto.getRequestName(),
                    responseDto.getRequestId(),
                    responseDto.getGatewayName(),
                    responseDto.getGatewayCounter(),
                    responseDto.getBackendName(),
                    responseDto.getBackendCounter(),
                    LocalDateTime.now()
            );

            if (logResponses) {
                log.info("get(): responseDto: {}", newResponseDto);
            }
            return responseDto;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
