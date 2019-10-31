package common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.*;
import org.junit.Assert;
import org.junit.Test;

@Data
class Person {
    private final Object name;
}
public class JacksonMapperTest {
    JsonMapper jsonMapper = new JsonMapper();
    @Test
    public void test() throws JsonProcessingException {
        Person person = new Person("null");
        String personValue = jsonMapper.writeValueAsString(person);
        Person person1 = jsonMapper.readValue(personValue, Person.class);
        Assert.assertEquals(person, person1);
    }
}
