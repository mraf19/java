package programmerzamannow.webmvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreatePerson() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Shiro")
                        .param("middleName", "Kirei")
                        .param("lastName", "Hana")
                        .param("email", "shirohana@gmail.com")
                        .param("phone", "08123456789")
                        .param("address.street", "Jl. KP. DADAP")
                        .param("address.city", "Tangerang Selatan")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "15318")
                        .param("hobbies[0]", "membaca")
                        .param("hobbies[1]", "bermain game")
                        .param("socialMedias[0].name", "shiroihana")
                        .param("socialMedias[0].location", "instagram")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString(
                        "Success create person Shiro Kirei Hana with email shirohana@gmail.com and phone 08123456789 with address Jl. KP. DADAP, Tangerang Selatan, Indonesia, 15318"
                ))
        );

    }

    @Test
    void testCreatePersonNotValid() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("firstName", "Shiro")
                        .param("middleName", "Kirei")
                        .param("lastName", "Hana")
                        .param("email", "shirohana@gmail.com")
                        .param("phone", "08123456789")
                        .param("address.street", "Jl. KP. DADAP")
                        .param("address.city", "Tangerang Selatan")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "15318")
                        .param("hobbies[0]", "membaca")
                        .param("hobbies[1]", "bermain game")
                        .param("socialMedias[0].name", "shiroihana")
                        .param("socialMedias[0].location", "instagram")
        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("You send invalid data!"))
        );

    }
}
