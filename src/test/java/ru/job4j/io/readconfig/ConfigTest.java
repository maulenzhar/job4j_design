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
    public void whenPairNotValidOne() {
        String path = "./data/app1.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairNotValidTwo() {
        String path = "./data/app2.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairNotValidThree() {
        String path = "./data/app3.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairNotValidFour() {
        String path = "./data/app4.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairNotValidFive() {
        String path = "./data/app5.properties";
        Config config = new Config(path);
        config.load();
    }
}