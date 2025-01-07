package programmerzamannow.restful.service;

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
import programmerzamannow.restful.entity.Address;
import programmerzamannow.restful.entity.Contact;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.AddressResponse;
import programmerzamannow.restful.model.CreateAddressRequest;
import programmerzamannow.restful.model.UpdateAddressRequest;
import programmerzamannow.restful.model.WebResponse;
import programmerzamannow.restful.repository.AddressRepository;
import programmerzamannow.restful.repository.ContactRepository;
import programmerzamannow.restful.repository.UserRepository;
import programmerzamannow.restful.security.BCrypt;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        addressRepository.deleteAll();
        contactRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setUsername("test");
        user.setName("test");
        user.setPassword(BCrypt.hashpw("test", BCrypt.gensalt()));
        user.setToken("test");
        user.setTokenExpiredAt(System.currentTimeMillis() + 100000000L);
        userRepository.save(user);


        Contact contact = new Contact();
        contact.setId("test");
        contact.setUser(user);
        contact.setFirstName("Shiro");
        contact.setLastName("Hana");
        contact.setEmail("shirohana@email.com");
        contact.setPhone("085171019022");
        contactRepository.save(contact);
    }

    @Test
    void createAddressBadRequest() throws Exception {
        CreateAddressRequest request = new CreateAddressRequest();
        request.setCountry("");

        mockMvc.perform(
                post("/api/contacts/test/addresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNotNull(response.getErrors());
        });
    }

    @Test
    void createAddressSuccess() throws Exception {
        CreateAddressRequest request = new CreateAddressRequest();
        request.setStreet("Jl. Bahagia");
        request.setCity("Jakarta");
        request.setProvince("DKI Jakarta");
        request.setCountry("Indonesia");
        request.setPostalCode("15318");

        mockMvc.perform(
                post("/api/contacts/test/addresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNull(response.getErrors());
            Assertions.assertNotNull(response);
            Assertions.assertEquals("Jl. Bahagia", response.getData().getStreet());
            Assertions.assertEquals("Jakarta", response.getData().getCity());
            Assertions.assertEquals("DKI Jakarta", response.getData().getProvince());
            Assertions.assertEquals("Indonesia", response.getData().getCountry());
            Assertions.assertEquals("15318", response.getData().getPostalCode());

            Assertions.assertTrue(addressRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void getAddressNotFound() throws Exception {

        mockMvc.perform(
                get("/api/contacts/test/addresses/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNotNull(response.getErrors());
        });
    }

    @Test
    void getAddressSuccess() throws Exception {
        Contact contact = contactRepository.findById("test").orElseThrow();

        Address address = new Address();
        address.setId("test");
        address.setStreet("Jl. Bahagia");
        address.setCity("Jakarta");
        address.setProvince("DKI Jakarta");
        address.setCountry("Indonesia");
        address.setPostalCode("15318");
        address.setContact(contact);

        addressRepository.save(address);

        mockMvc.perform(
                get("/api/contacts/test/addresses/test")
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNull(response.getErrors());
            Assertions.assertNotNull(response);
            Assertions.assertEquals("Jl. Bahagia", response.getData().getStreet());
            Assertions.assertEquals("Jakarta", response.getData().getCity());
            Assertions.assertEquals("DKI Jakarta", response.getData().getProvince());
            Assertions.assertEquals("Indonesia", response.getData().getCountry());
            Assertions.assertEquals("15318", response.getData().getPostalCode());

            Assertions.assertTrue(addressRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void updateAddressBadRequest() throws Exception {
        CreateAddressRequest request = new CreateAddressRequest();
        request.setCountry("");

        mockMvc.perform(
                put("/api/contacts/test/addresses/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNotNull(response.getErrors());
        });
    }

    @Test
    void updateAddressSuccess() throws Exception {
        Contact contact = contactRepository.findById("test").orElseThrow();

        Address address = new Address();
        address.setId("test");
        address.setStreet("Jl. Bahagia");
        address.setCity("Jakarta");
        address.setProvince("DKI Jakarta");
        address.setCountry("Indonesia");
        address.setPostalCode("15318");
        address.setContact(contact);

        addressRepository.save(address);

        UpdateAddressRequest request = new UpdateAddressRequest();
        request.setStreet("Jl. Bahagia Edit");
        request.setCity("Jakarta Edit");
        request.setProvince("DKI Jakarta Edit");
        request.setCountry("Indonesia Edit");
        request.setPostalCode("15318 Edit");

        mockMvc.perform(
                put("/api/contacts/test/addresses/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNull(response.getErrors());
            Assertions.assertNotNull(response);
            Assertions.assertEquals(request.getStreet(), response.getData().getStreet());
            Assertions.assertEquals(request.getCity(), response.getData().getCity());
            Assertions.assertEquals(request.getProvince(), response.getData().getProvince());
            Assertions.assertEquals(request.getCountry(), response.getData().getCountry());
            Assertions.assertEquals(request.getPostalCode(), response.getData().getPostalCode());

            Assertions.assertTrue(addressRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void deleteAddressBadRequest() throws Exception {

        mockMvc.perform(
                delete("/api/contacts/test/addresses/test")
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNotNull(response.getErrors());
        });
    }

    @Test
    void deleteAddressSuccess() throws Exception {
        Contact contact = contactRepository.findById("test").orElseThrow();

        Address address = new Address();
        address.setId("test");
        address.setStreet("Jl. Bahagia");
        address.setCity("Jakarta");
        address.setProvince("DKI Jakarta");
        address.setCountry("Indonesia");
        address.setPostalCode("15318");
        address.setContact(contact);

        addressRepository.save(address);

        mockMvc.perform(
                delete("/api/contacts/test/addresses/test")
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNull(response.getErrors());
            Assertions.assertNotNull(response);
            Assertions.assertEquals("OK", response.getData());

            Assertions.assertFalse(addressRepository.existsById("test"));
        });
    }

    @Test
    void getListAddressNotFound() throws Exception {

        mockMvc.perform(
                get("/api/contacts/salah/addresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNotNull(response.getErrors());
        });
    }

    @Test
    void getListAddressSuccess() throws Exception {
        Contact contact = contactRepository.findById("test").orElseThrow();

        for (int i = 0; i < 5; i++) {
            Address address = new Address();
            address.setId("test-" + i);
            address.setStreet("Jl. Bahagia");
            address.setCity("Jakarta");
            address.setProvince("DKI Jakarta");
            address.setCountry("Indonesia");
            address.setPostalCode("15318");
            address.setContact(contact);

            addressRepository.save(address);
        }



        mockMvc.perform(
                get("/api/contacts/test/addresses")
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<AddressResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNull(response.getErrors());
            Assertions.assertNotNull(response);
            Assertions.assertEquals(5, response.getData().size());
        });
    }
}
