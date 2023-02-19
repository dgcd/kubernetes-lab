package dgcd.studies.kubelab.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDto {

    // устанавливается фронтом
    private final String requestName;
    private final Long requestId;

    // добавляется шлюзом
    private final String gatewayName;
    private final Long gatewayCounter;

    // добавляется бэком
    private final String backendName;
    private final Long backendCounter;

    // добавляется шлюзом на обратном пути
    private final LocalDateTime responseCreated;

    @JsonCreator
    public ResponseDto(
            @JsonProperty("requestName") String requestName,
            @JsonProperty("requestId") Long requestId,
            @JsonProperty("gatewayName") String gatewayName,
            @JsonProperty("gatewayCounter") Long gatewayCounter,
            @JsonProperty("backendName") String backendName,
            @JsonProperty("backendCounter") Long backendCounter,
            @JsonProperty("responseCreated") LocalDateTime responseCreated
    ) {
        this.requestName = requestName;
        this.requestId = requestId;
        this.gatewayName = gatewayName;
        this.gatewayCounter = gatewayCounter;
        this.backendName = backendName;
        this.backendCounter = backendCounter;
        this.responseCreated = responseCreated;
    }

}
