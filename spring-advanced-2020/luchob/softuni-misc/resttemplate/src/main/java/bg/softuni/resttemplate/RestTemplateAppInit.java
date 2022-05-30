package bg.softuni.resttemplate;

import bg.softuni.resttemplate.authors.Author;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateAppInit implements CommandLineRunner {

  final RestTemplate restTemplate;

  public RestTemplateAppInit(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public void run(String... args) throws Exception {
    ResponseEntity<List<Author>> authorsResponse = restTemplate.exchange(
        "http://localhost:8080/authors",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Author>>() {});
    if(authorsResponse.hasBody()){
      List<Author> authors = authorsResponse.getBody();
      authors.forEach(System.out::println);
    }
  }
}
