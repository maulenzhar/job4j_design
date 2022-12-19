package ru.job4j.io.readconfig;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ConfigTest {
    @Test
    public void whenPairWithComment() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairNotValid() {
        String path = "./data/app1.properties";
        Config config = new Config(path);
        config.load();
    }
}