package programmerzamannow.webmvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import programmerzamannow.webmvc.model.CreateAddressRequest;
import programmerzamannow.webmvc.model.CreatePersonRequest;
import programmerzamannow.webmvc.model.CreateSocialMediaRequest;

import java.util.List;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class PersonApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void createPersonValid() throws Exception {

        CreateAddressRequest address = new CreateAddressRequest();
        address.setCity("Jakarta");
        address.setCountry("Indonesia");
        address.setStreet("Jl. Kp. Dadap");
        address.setPostalCode("15318");

        CreateSocialMediaRequest socialMedia = new CreateSocialMediaRequest();
        socialMedia.setName("shiro");
        socialMedia.setLocation("instagram");

        CreatePersonRequest request = new CreatePersonRequest();
        request.setFirstName("Shiro");
        request.setMiddleName("Kirei");
        request.setLastName("Hana");
        request.setEmail("shirohana@gmail.com");
        request.setPhone("08123456789");
        request.setAddress(address);
        request.setHobbies(List.of("Gaming", "Music", "Travel"));
        request.setSocialMedias(List.of(socialMedia));

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpectAll(
                status().isOk(),
                content().json(jsonRequest)
        );
    }

    @Test
    void createPersonNotValid() throws Exception {

        CreateAddressRequest address = new CreateAddressRequest();
        address.setCity("Jakarta");
        address.setCountry("Indonesia");
        address.setStreet("Jl. Kp. Dadap");
        address.setPostalCode("15318");

        CreateSocialMediaRequest socialMedia = new CreateSocialMediaRequest();
        socialMedia.setName("shiro");
        socialMedia.setLocation("instagram");

        CreatePersonRequest request = new CreatePersonRequest();
//        request.setFirstName("Shiro");
        request.setMiddleName("Kirei");
        request.setLastName("Hana");
        request.setEmail("shirohana@gmail.com");
        request.setPhone("08123456789");
        request.setAddress(address);
        request.setHobbies(List.of("Gaming", "Music", "Travel"));
        request.setSocialMedias(List.of(socialMedia));

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("Validation Error: "))
        );
    }
}
