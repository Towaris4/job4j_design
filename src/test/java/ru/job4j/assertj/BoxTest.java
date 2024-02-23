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
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }


    @Test
    void getNumberOfVerticesNull() {
        Box box = new Box(0, 10);
        assertThat(box.getNumberOfVertices()).isEqualTo(0);
    }

    @Test
    void getNumberOfVerticesFive() {
        Box box = new Box(5, 15);
        assertThat(box.getNumberOfVertices()).isEqualTo(-1);
    }

    @Test
    void isExistTrue() {
        Box box = new Box(0, 5);
        assertThat(box.isExist()).isEqualTo(true);
    }

    @Test
    void isExistFalse() {
        Box box = new Box(3, 2);
        assertThat(box.isExist()).isEqualTo(false);
    }

    @Test
    void getAreaCubeEdgeTen() {
        Box box = new Box(8, 10);
        assertThat(box.getArea()).isEqualTo(600);
    }

    @Test
    void getAreaCubeEdgeTenArea() {
        Box box = new Box(8, 11);
        assertThat(box.getArea()).isEqualTo(726);
    }
}