package com.example.carsdb;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarsDBApplicationTests {

  @Autowired
  private MockMvc mvc;

  @Test
  public void postCar() throws Exception {
    
    // GET /cars == []
    mvc.perform(get("/cars").
      contentType(MediaType.APPLICATION_JSON)).
      andExpect(status().isOk()).
      andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).
      andExpect(jsonPath("$.length()", Matchers.is(0)));
    
    // POST /cars {...}
    mvc.perform(post("/cars").
      contentType(MediaType.APPLICATION_JSON).content(
        "{" +
        "  \"manufacturer\":\"Citroen\"," +
        "  \"type\":\"Xsara\"," +
        "  \"registration\":\"1PP 1234\"" +
        "}"
      )).
      andExpect(status().isOk()).
      andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).
      andExpect(jsonPath("$.manufacturer", Matchers.is("Citroen"))).
      andExpect(jsonPath("$.type", Matchers.is("Xsara"))).
      andExpect(jsonPath("$.registration", Matchers.is("1PP 1234")));
    
    // GET /cars [{...}]
    mvc.perform(get("/cars").
      contentType(MediaType.APPLICATION_JSON)).
      andExpect(status().isOk()).
      andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).
      andExpect(jsonPath("$.length()", Matchers.is(1))).
      andExpect(jsonPath("$[0].manufacturer", Matchers.is("Citroen"))).
      andExpect(jsonPath("$[0].type", Matchers.is("Xsara"))).
      andExpect(jsonPath("$[0].registration", Matchers.is("1PP 1234")));
    
    // DELETE /cars
    mvc.perform(delete("/cars")).
      andExpect(status().isOk());
    
    // GET /cars == []
    mvc.perform(get("/cars").
      contentType(MediaType.APPLICATION_JSON)).
      andExpect(status().isOk()).
      andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).
      andExpect(jsonPath("$.length()", Matchers.is(0)));
  }

  @Test
  public void putCars() throws Exception {
    
    // GET /cars == []
    mvc.perform(get("/cars").
      contentType(MediaType.APPLICATION_JSON)).
      andExpect(status().isOk()).
      andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).
      andExpect(jsonPath("$.length()", Matchers.is(0)));
    
    // PUT /cars [{...}, {...}]
    mvc.perform(put("/cars").
      contentType(MediaType.APPLICATION_JSON).content(
        "[" +
        "  {" +
        "    \"manufacturer\":\"Ford\"," +
        "    \"type\":\"Mondeo\"," +
        "    \"registration\":\"2AE 0331\"" +
        "  }," +
        "  {" +
        "    \"manufacturer\":\"Citroen\"," +
        "    \"type\":\"Xsara\"," +
        "    \"registration\":\"1PP 1234\"" +
        "  }" +
        "]"
      )).
      andExpect(status().isOk());
    
    // GET /cars [{...}]
    mvc.perform(get("/cars").
      contentType(MediaType.APPLICATION_JSON)).
      andExpect(status().isOk()).
      andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).
      andExpect(jsonPath("$.length()", Matchers.is(2))).
      andExpect(jsonPath("$[0].manufacturer", Matchers.is("Ford"))).
      andExpect(jsonPath("$[0].type", Matchers.is("Mondeo"))).
      andExpect(jsonPath("$[0].registration", Matchers.is("2AE 0331"))).
      andExpect(jsonPath("$[1].manufacturer", Matchers.is("Citroen"))).
      andExpect(jsonPath("$[1].type", Matchers.is("Xsara"))).
      andExpect(jsonPath("$[1].registration", Matchers.is("1PP 1234")));
    
    // DELETE /cars
    mvc.perform(delete("/cars")).
      andExpect(status().isOk());
    
    // GET /cars == []
    mvc.perform(get("/cars").
      contentType(MediaType.APPLICATION_JSON)).
      andExpect(status().isOk()).
      andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).
      andExpect(jsonPath("$.length()", Matchers.is(0)));
  }

}
