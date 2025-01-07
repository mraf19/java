package programmerzamannow.webmvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import programmerzamannow.webmvc.model.HelloRequest;
import programmerzamannow.webmvc.model.HelloResponse;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class BodyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testBody() throws Exception {
        HelloRequest request = new HelloRequest();
        request.setName("Shiro");

        mockMvc.perform(
                post("/body/hello")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk(),
                header().string(HttpHeaders.CONTENT_TYPE, Matchers.containsString(MediaType.APPLICATION_JSON_VALUE))
        ).andExpect(result -> {
            String responseBody = result.getResponse().getContentAsString();
            HelloResponse response = objectMapper.readValue(responseBody, HelloResponse.class);
            Assertions.assertEquals("Hello Shiro", response.getHello());
        });
    }
}
