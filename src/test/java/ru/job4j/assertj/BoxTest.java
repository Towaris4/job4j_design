package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }


    @Test
    void getNumberOfVertices() {
        Box box = new Box(0, 10);
        assertThat(box.getNumberOfVertices()).isEqualTo(0);


    }

    @Test
    void isExist() {
        Box box = new Box(0, 10);
        assertThat(box.isExist()).isEqualTo(true);
    }

    @Test
    void getArea() {
        Box box = new Box(8, 10);
        assertThat(box.getArea()).isEqualTo(600);
    }
}