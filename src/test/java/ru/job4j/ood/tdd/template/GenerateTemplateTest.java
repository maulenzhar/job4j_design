package ru.job4j.ood.tdd.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.collection.map.Map;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class GenerateTemplateTest {
    @Test
    public void whenGanarateSuccessull() {
        GenerateTemplate generateTemplate = new GenerateTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        String result = generateTemplate.produce("I am a ${name}, Who are ${subject}?", map);
        assertThat(result).isEqualTo("I am a Petr Arsentev, Who are you?");
    }

    @Test
    public void whenKeyNotFoundThenGetException() {
        GenerateTemplate generateTemplate = new GenerateTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("subject", "you");
        assertThatThrownBy(() -> generateTemplate.produce("I am a ${name}, Who are ${subject}?", map)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenSurnameExcessThenGetException() {
        GenerateTemplate generateTemplate = new GenerateTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr");
        map.put("surname", "Arsentev");
        map.put("subject", "you");
        assertThatThrownBy(() -> generateTemplate.produce("I am a ${name}, Who are ${subject}?", map)).
                isInstanceOf(IllegalArgumentException.class);
    }
}