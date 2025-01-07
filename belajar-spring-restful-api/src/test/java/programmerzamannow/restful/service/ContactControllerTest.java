package programmerzamannow.restful.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import programmerzamannow.restful.entity.Contact;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.ContactResponse;
import programmerzamannow.restful.model.CreateContactRequest;
import programmerzamannow.restful.model.UpdateContactRequest;
import programmerzamannow.restful.model.WebResponse;
import programmerzamannow.restful.repository.ContactRepository;
import programmerzamannow.restful.repository.UserRepository;
import programmerzamannow.restful.security.BCrypt;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        contactRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setUsername("test");
        user.setName("test");
        user.setPassword(BCrypt.hashpw("test", BCrypt.gensalt()));
        user.setToken("test");
        user.setTokenExpiredAt(System.currentTimeMillis() + 100000000L);
        userRepository.save(user);
    }

    @Test
    void testCreateContactBadRequest() throws Exception {
        CreateContactRequest contactRequest = new CreateContactRequest();
        contactRequest.setFirstName("");
        contactRequest.setEmail("salah");

        mockMvc.perform(
                post("/api/contacts")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
                        .content(objectMapper.writeValueAsString(contactRequest))
        ).andExpect(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNotNull(response.getErrors());
        });
    }

    @Test
    void testCreateSuccess() throws Exception {
        CreateContactRequest contactRequest = new CreateContactRequest();
        contactRequest.setFirstName("Shiro");
        contactRequest.setLastName("Hana");
        contactRequest.setEmail("shirohana@email.com");
        contactRequest.setPhone("085171019022");

        mockMvc.perform(
                post("/api/contacts")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
                        .content(objectMapper.writeValueAsString(contactRequest))
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<ContactResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNull(response.getErrors());
            Assertions.assertNotNull(response.getData().getId());
            Assertions.assertEquals("Shiro", response.getData().getFirstName());
            Assertions.assertEquals("Hana", response.getData().getLastName());
            Assertions.assertEquals("shirohana@email.com", response.getData().getEmail());
            Assertions.assertEquals("085171019022", response.getData().getPhone());

            Assertions.assertTrue(contactRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void testGetContactNotFound() throws Exception {

        mockMvc.perform(
                get("/api/contacts/salah")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpect(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNotNull(response.getErrors());
        });
    }

    @Test
    void testGetContactSuccess() throws Exception {
        User user = userRepository.findById("test").orElse(null);

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setUser(user);
        contact.setFirstName("Shiro");
        contact.setLastName("Hana");
        contact.setEmail("shirohana@email.com");
        contact.setPhone("085171019022");
        contactRepository.save(contact);

        mockMvc.perform(
                get("/api/contacts/" + contact.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<ContactResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNull(response.getErrors());
            Assertions.assertEquals(contact.getId(), response.getData().getId());
            Assertions.assertEquals(contact.getFirstName(), response.getData().getFirstName());
            Assertions.assertEquals(contact.getLastName(), response.getData().getLastName());
            Assertions.assertEquals(contact.getEmail(), response.getData().getEmail());
            Assertions.assertEquals(contact.getPhone(), response.getData().getPhone());
        });
    }

    @Test
    void testUpdateContactBadRequest() throws Exception {
        UpdateContactRequest contactRequest = new UpdateContactRequest();
        contactRequest.setFirstName("");
        contactRequest.setEmail("salah");

        mockMvc.perform(
                put("/api/contacts/098986876")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
                        .content(objectMapper.writeValueAsString(contactRequest))
        ).andExpect(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNotNull(response.getErrors());
        });
    }

    @Test
    void testUpdateSuccess() throws Exception {
        User user = userRepository.findById("test").orElse(null);

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setUser(user);
        contact.setFirstName("Shiro");
        contact.setLastName("Hana");
        contact.setEmail("shirohana@email.com");
        contact.setPhone("085171019022");
        contactRepository.save(contact);

        CreateContactRequest contactRequest = new CreateContactRequest();
        contactRequest.setFirstName("Edit");
        contactRequest.setLastName("Test");
        contactRequest.setEmail("edittest@email.com");
        contactRequest.setPhone("0812345678");

        mockMvc.perform(
                put("/api/contacts/" + contact.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
                        .content(objectMapper.writeValueAsString(contactRequest))
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<ContactResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNull(response.getErrors());
            Assertions.assertNotNull(response.getData().getId());
            Assertions.assertEquals(contactRequest.getFirstName(), response.getData().getFirstName());
            Assertions.assertEquals(contactRequest.getLastName(), response.getData().getLastName());
            Assertions.assertEquals(contactRequest.getEmail(), response.getData().getEmail());
            Assertions.assertEquals(contactRequest.getPhone(), response.getData().getPhone());

            Assertions.assertTrue(contactRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void testDeleteNotFound() throws Exception {
        mockMvc.perform(
                delete("/api/contacts/salah")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpect(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNotNull(response.getErrors());
        });
    }

    @Test
    void testDeleteContactSuccess() throws Exception {
        User user = userRepository.findById("test").orElse(null);

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setUser(user);
        contact.setFirstName("Shiro");
        contact.setLastName("Hana");
        contact.setEmail("shirohana@email.com");
        contact.setPhone("085171019022");
        contactRepository.save(contact);

        mockMvc.perform(
                delete("/api/contacts/" + contact.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNull(response.getErrors());
            Assertions.assertEquals("OK", response.getData());
        });
    }

    @Test
    void testSearchNotFound() throws Exception {
        mockMvc.perform(
                get("/api/contacts")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<ContactResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNull(response.getErrors());
            Assertions.assertEquals(0, response.getData().size());
            Assertions.assertEquals(0, response.getPaging().getCurrentPage());
            Assertions.assertEquals(0, response.getPaging().getTotalPage());
            Assertions.assertEquals(10, response.getPaging().getSize());
        });
    }

    @Test
    void testSearchUsingName() throws Exception {
        User user = userRepository.findById("test").orElse(null);


        for (int i = 0; i < 100; i++) {
            Contact contact = new Contact();
            contact.setId(UUID.randomUUID().toString());
            contact.setUser(user);
            contact.setFirstName("Shiro " + i);
            contact.setLastName("Hana");
            contact.setEmail("shirohana@email.com");
            contact.setPhone("085171019022");
            contactRepository.save(contact);
        }

        mockMvc.perform(
                get("/api/contacts")
                        .queryParam("name", "Shiro")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<ContactResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNull(response.getErrors());
            Assertions.assertEquals(10, response.getData().size());
            Assertions.assertEquals(0, response.getPaging().getCurrentPage());
            Assertions.assertEquals(10, response.getPaging().getTotalPage());
            Assertions.assertEquals(10, response.getPaging().getSize());
        });
    }
}
