package programmerzamannow.webmvc.controller;

import jakarta.servlet.http.Cookie;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLoginOK() throws Exception {
        mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "shiro")
                        .param("password", "rahasia")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString(
                        "OK"
                )),
                cookie().value("Username", Matchers.is("shiro"))
        );
    }

    @Test
    void testLoginKO() throws Exception {
        mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "salah")
                        .param("password", "rahasia")
        ).andExpectAll(
                status().isUnauthorized(),
                content().string(Matchers.containsString(
                        "KO"
                ))

        );
    }

    @Test
    void testGetUser() throws Exception {
        mockMvc.perform(
                get("/auth/user")
                        .cookie(new Cookie("username", "Shiro"))
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello Shiro"))
        );
    }
}
