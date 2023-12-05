package dgcd.studies.kubelab.gateway;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class GatewayConfig {

    @Bean
    public RestTemplate restTemplate(MappingJackson2HttpMessageConverter converter) {
        return new RestTemplate(Collections.singletonList(converter));
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }

    @Bean
    public CountedAspect countedAspect(MeterRegistry registry) {
        return new CountedAspect(registry);
    }

    @Bean
    public Gauge xxxGauge(MeterRegistry registry) {
        return Gauge
                .builder("xxx.gauge.ret42", () -> 42)
                .tag("version", "v1")
                .description("xxx user gauge description")
                .register(registry);
    }

}
