package programmerzamannow.webmvc.controller;

import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.regex.Matcher;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGuest() throws Exception {
        try {
            mockMvc.perform(get("/hello"))
                    .andExpectAll(
                            status().isOk(),
                            content().string(Matchers.containsString("Hello Guest"))
                    );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testName() throws Exception {
        try {
            mockMvc.perform(
                    get("/hello").queryParam("name", "Shiro")
            ).andExpectAll(
                    status().isOk(),
                    content().string(Matchers.containsString("Hello Shiro"))
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testPost() throws Exception {
        mockMvc.perform(
                post("/hello")
        ).andExpectAll(
                status().isMethodNotAllowed()
        );
    }
}
