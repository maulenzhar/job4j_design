package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        assertThat(box.whatsThis()).isEqualTo("Tetrahedron");
        assertThat(box.getArea()).isEqualTo(173.20d, withPrecision(0.006d));
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        assertThat(box.whatsThis()).isEqualTo("Cube");
        assertThat(box.getArea()).isEqualTo(600.0d, withPrecision(0.06d));
        assertThat(box.getNumberOfVertices()).isEqualTo(8);
    }

}