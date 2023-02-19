package dgcd.studies.kubelab.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatfactService {

    private final RestTemplate restTemplate;

    public FactDto getFact() {
        try {
            var responseEntity = restTemplate.getForEntity("https://catfact.ninja/fact", FactDto.class);
            var factDto = responseEntity.getBody();
            log.info("Received cat fact: {}", factDto);
            return factDto;
        } catch (Exception e) {
            log.error("Exception while fetching cats fact: ", e);
            return null;
        }
    }

}
