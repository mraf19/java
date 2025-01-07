package programmerzamannow.webmvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import programmerzamannow.webmvc.service.HelloService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerMockTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloService helloService;

    @BeforeEach
    void setUp() {
        Mockito.when(helloService.hello(Mockito.anyString()))
                .thenReturn("Hello Guys");
    }

    @Test
    void testName() throws Exception {
        try {
            mockMvc.perform(
                    get("/hello").queryParam("name", "Shiro")
            ).andExpectAll(
                    status().isOk(),
                    content().string(Matchers.containsString("Hello Guys"))
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testModelandView() throws Exception {
        mockMvc.perform(
                get("/web/hello")
                        .queryParam("name", "Shiro")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Belajar View")),
                content().string(Matchers.containsString("Hello Shiro"))
        );
    }

    @Test
    void testModelandViewRedirect() throws Exception {
        mockMvc.perform(
                get("/web/hello")
        ).andExpectAll(
                status().is3xxRedirection()
        );
    }
}
