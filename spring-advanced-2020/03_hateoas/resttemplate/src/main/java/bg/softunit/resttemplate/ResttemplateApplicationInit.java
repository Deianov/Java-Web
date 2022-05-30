package bg.softunit.resttemplate;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ResttemplateApplicationInit implements CommandLineRunner {

  @Autowired
  RestTemplate template;

  @Override
  public void run(String... args) throws Exception {
    ResponseEntity<List<Author>> authorsReponse =template.exchange(
        "http://localhost:8080/authors",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Author>>() {
        }
    );

    if (authorsReponse.hasBody()) {
      authorsReponse.getBody().forEach(System.out::println);
    }


  }
}
