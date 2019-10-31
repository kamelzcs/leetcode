package common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.Data;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.junit.Test;

@Data
class Person {
    private final Object name;
}

interface InputOut<IN, OUT> {
    OUT transform(IN in);
}

@Data
class IntToString implements InputOut<Integer, String> {
    private final Integer input;
    @Override
    public String transform(Integer integer) {
        return null;
    }
}
@Log
public class JacksonMapperTest {
    JsonMapper jsonMapper = new JsonMapper();
    @Test
    public void test() throws JsonProcessingException {
        Person person = new Person("null");
        String personValue = jsonMapper.writeValueAsString(person);
        Person person1 = jsonMapper.readValue(personValue, Person.class);
        Assert.assertEquals(person, person1);
    }

    @Test
    public void testGenerics() throws JsonProcessingException {
        IntToString input = new IntToString(1);
        String value = jsonMapper.writeValueAsString(input);
        log.info(value);
        IntToString input2 = jsonMapper.readValue(value, IntToString.class);
        Assert.assertEquals(input, input2);

    }
}
