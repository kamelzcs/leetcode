package common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
class Person {
    private Object name;
}

interface InputOut<IN, OUT> {
    OUT transform(IN in);
}

@NoArgsConstructor
@AllArgsConstructor
@Data
class IntToString implements InputOut<Integer, String> {
    private Integer input;
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

    @Test
    public void testParallelStream() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 3);
        map.values().parallelStream()
                .peek(x -> System.out.println("processing "+x+" in "+Thread.currentThread()))
                .forEach(System.out::println);
    }
}
