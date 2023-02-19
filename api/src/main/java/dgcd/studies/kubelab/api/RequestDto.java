package dgcd.studies.kubelab.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDto {

    // устанавливается фронтом
    private final String requestName;
    private final Long requestId;

    // добавляется шлюзом
    private final String gatewayName;
    private final Long gatewayCounter;


    @JsonCreator
    public RequestDto(
            @JsonProperty("requestName") String requestName,
            @JsonProperty("requestId") Long requestId,
            @JsonProperty("gatewayName") String gatewayName,
            @JsonProperty("gatewayCounter") Long gatewayCounter
    ) {
        this.requestName = requestName;
        this.requestId = requestId;
        this.gatewayName = gatewayName;
        this.gatewayCounter = gatewayCounter;
    }

}
