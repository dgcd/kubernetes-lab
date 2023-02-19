package dgcd.studies.kubelab.backend;

import dgcd.studies.kubelab.api.RequestDto;
import dgcd.studies.kubelab.api.ResponseDto;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.Objects.isNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@Slf4j
@RestController
public class BackendController {

    private final AtomicLong responseCounter = new AtomicLong(0L);
    private long previousCounter = 0L;
    private String hostname;

    @Value("${spring.profiles.active:none}")
    private String profile;

    @Value("${kubelab.backend.log.stats:true}")
    private Boolean logStats;

    @Value("${kubelab.backend.log.requests:true}")
    private Boolean logRequests;

    @Value("${kubelab.backend.log.responses:true}")
    private Boolean logResponses;

    @Autowired
    private CatfactService catfactService;


    @PostConstruct
    public void init() throws UnknownHostException {
        hostname = InetAddress.getLocalHost().getHostName();
    }


    @Scheduled(fixedDelay = 1000)
    public void statistics() {
        if (!logStats) {
            return;
        }
        log.info("requests per second: {}", responseCounter.get() - previousCounter);
        previousCounter = responseCounter.get();
    }


    @GetMapping(value = "/test", produces = TEXT_PLAIN_VALUE)
    public String getData() {
        return String.format("""
                        java version: %s
                        %s (build %s, %s)
                        current time: %s
                        backend instance: %s
                        spring profile: %s
                        """,
                System.getProperty("java.version"),
                System.getProperty("java.vm.name"),
                System.getProperty("java.vm.version"),
                System.getProperty("java.vm.info"),
                LocalDateTime.now(),
                hostname,
                profile
        );
    }

    @PostMapping(value = "/rest", produces = APPLICATION_JSON_VALUE)
    public ResponseDto get(@RequestBody RequestDto requestDto) {
        if (logRequests) {
            log.info("get(): requestDto: {}", requestDto);
        }
        var fact = catfactService.getFact();
        var factString = isNull(fact) ? "" : " - fact: " + fact.fact();
        var responseDto = new ResponseDto(
                requestDto.getRequestName(),
                requestDto.getRequestId(),
                requestDto.getGatewayName(),
                requestDto.getGatewayCounter(),
                hostname + factString,
                responseCounter.incrementAndGet(),
                LocalDateTime.now()
        );
        if (logResponses) {
            log.info("get(): responseDto: {}", responseDto);
        }
        return responseDto;
    }

}
