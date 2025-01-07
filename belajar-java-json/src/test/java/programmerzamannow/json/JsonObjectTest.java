package programmerzamannow.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JsonObjectTest {

    @Test
    void createJson() throws JsonProcessingException {

        Map<String, Object> map = Map.of(
                "firstName", "Shiro",
                "lastName", "Hana",
                "age", 24,
                "married", false,
                "address", Map.of(
                        "street", "Jl. Bahagia",
                        "city", "Jakarta",
                        "country", "Indonesia"
                ),
                "hobbies", List.of("Reading", "Singing", "Travelling", "Eating")
        );

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(map);

        System.out.println(json);
    }

    @Test
    void readJson() throws JsonProcessingException {
        String json = """
                {
                  "lastName": "Hana",
                  "age": 24,
                  "firstName": "Shiro",
                  "address": {
                    "city": "Jakarta",
                    "country": "Indonesia",
                    "street": "Jl. Bahagia"
                  },
                  "married": false,
                  "hobbies": [
                    "Reading",
                    "Singing",
                    "Travelling",
                    "Eating"
                  ]
                }
                """;

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> map = objectMapper.readValue(json, new TypeReference<>() {
        });

        Assertions.assertEquals("Shiro", map.get("firstName"));

        Assertions .assertEquals(List.of("Reading", "Singing", "Travelling", "Eating"), map.get("hobbies"));
    }

    @Test
    void testObjectToJson() throws JsonProcessingException {
        Address address = new Address();
        address.setStreet("Jl. Bahagia");
        address.setCity("Jakarta");
        address.setCountry("Indonesia");

        Person person = new Person();
        person.setId("1");
        person.setName("Shiro");
        person.setHobbies(List.of("Travelling", "Gaming"));
        person.setAddress(address);

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(person);

        System.out.println(json);

    }

    @Test
    void testJsonToObject() throws JsonProcessingException {
        String json = """
                {
                  "id": "1",
                  "name": "Shiro",
                  "hobbies": [
                    "Travelling",
                    "Gaming"
                  ],
                  "address": {
                    "city": "Jakarta",
                    "street": "Jl. Bahagia",
                    "country": "Indonesia"
                  }
                }
                """;

        ObjectMapper objectMapper = new ObjectMapper();

        Person person = objectMapper.readValue(json, Person.class);

        Assertions.assertEquals("1", person.getId());
        Assertions.assertEquals("Shiro", person.getName());
        Assertions.assertEquals("Jl. Bahagia", person.getAddress().getStreet());
        Assertions.assertEquals("Jakarta", person.getAddress().getCity());
        Assertions.assertEquals("Indonesia", person.getAddress().getCountry());
        Assertions.assertEquals(List.of("Travelling", "Gaming"), person.getHobbies());
    }

    @Test
    void mapperFeature() throws JsonProcessingException {
        String json = """
                {"ID": "1", "Name": "Shiro"}
                """;
        ObjectMapper objectMapper = new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        Person person = objectMapper.readValue(json, Person.class);
        Assertions.assertEquals("1", person.getId());
        Assertions.assertEquals("Shiro", person.getName());
    }

    @Test
    void deserializationFeature() throws JsonProcessingException {
        String json = """
                { "id": "1", "name": "Shiro", "age": 23, "hobbies": "eating"}
                """;

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Person person = objectMapper.readValue(json, Person.class);

        Assertions.assertEquals("1", person.getId());
        Assertions.assertEquals("Shiro", person.getName());
        Assertions.assertEquals(List.of("eating"), person.getHobbies());
    }

    @Test
    void serializationFeature() throws JsonProcessingException {
        Address address = new Address();
        address.setStreet("Jl. Bahagia");
        address.setCity("Jakarta");
        address.setCountry("Indonesia");

        Person person = new Person();
        person.setId("1");
        person.setName("Shiro");
        person.setHobbies(List.of("Travelling", "Gaming"));
        person.setAddress(address);

        ObjectMapper objectMapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);

        String json = objectMapper.writeValueAsString(person);

        System.out.println(json);

    }

    @Test
    void serializationInclusion() throws JsonProcessingException {

        Person person = new Person();
        person.setId("1");
        person.setName("Shiro");

        ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

        String json = objectMapper.writeValueAsString(person);

        System.out.println(json);

    }

    @Test
    void dateToMilis() throws JsonProcessingException {

        Person person = new Person();
        person.setId("1");
        person.setName("Shiro");
        person.setCreatedAt(new Date());
        person.setUpdatedAt(new Date());

        ObjectMapper objectMapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);

        String json = objectMapper.writeValueAsString(person);

        System.out.println(json);

    }

    @Test
    void dateToString() throws JsonProcessingException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Person person = new Person();
        person.setId("1");
        person.setName("Shiro");
        person.setCreatedAt(new Date());
        person.setUpdatedAt(new Date());

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .setDateFormat(simpleDateFormat);

        String json = objectMapper.writeValueAsString(person);

        System.out.println(json);

    }

    @Test
    void annotations() throws JsonProcessingException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Person person = new Person();
        person.setId("1");
        person.setName("Shiro");
        person.setFullName("Shiro Kirei Hana");
        person.setPassword("rahasia");
        person.setCreatedAt(new Date());
        person.setUpdatedAt(new Date());

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .setDateFormat(simpleDateFormat);

        String json = objectMapper.writeValueAsString(person);

        System.out.println(json);

    }
}
