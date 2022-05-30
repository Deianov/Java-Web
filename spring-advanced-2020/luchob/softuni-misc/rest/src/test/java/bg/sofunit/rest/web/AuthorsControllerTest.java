package bg.sofunit.rest.web;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import bg.sofunit.rest.model.Author;
import bg.sofunit.rest.repository.AuthorRepository;
import java.util.List;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@Ignore //In order to work Comment the application init
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class AuthorsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private AuthorRepository authorRepository;

  @Before
  public void setUp() {
    Author author1 = new Author();
    Author author2 = new Author();
    author1.setName("author1");
    author2.setName("author2");

    authorRepository.saveAll(List.of(author1, author2));
  }

  @Test
  public void testGetAuthorsOK() throws Exception {
      this.mockMvc.perform(get("/authors")).
          andExpect(status().isOk());
  }

  @Test
  public void testGetAuthorsCorrect() throws Exception {
    this.mockMvc.perform(get("/authors")).
        andExpect(status().isOk()).
        andExpect(jsonPath("$", hasSize(2))).
        andExpect(jsonPath("$.[0].name", is("author1"))).
        andExpect(jsonPath("$.[1].name", is("author2")));
  }

}
