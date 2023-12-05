package dgcd.studies.kubelab.gateway;

import dgcd.studies.kubelab.api.RequestDto;
import dgcd.studies.kubelab.api.ResponseDto;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import static org.springframework.util.StringUtils.hasLength;

@Slf4j
@Service
public class BackendService {

    @Value("${kubelab.gateway.backend.url:}")
    private String serverUrl;

    @Autowired
    private RestTemplate restTemplate;


    @Value("${kubelab.gateway.log.responses:true}")
    private Boolean logResponses;


    @Timed(value = "xxx.backend.time", description = "Time taken to return greeting", percentiles = {0.25, 0.5, 0.75, 0.95, 1})
    public ResponseDto getResponseDto(RequestDto newRequestDto) {
        if (!hasLength(serverUrl)) {
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
    }

}
