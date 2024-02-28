package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRolenameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        Role result = store.findById("1");
        assertThat(result.getModel()).isEqualTo("Petr");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRolenameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.add(new Role("1", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getModel()).isEqualTo("Petr");
    }

    @Test
    void whenReplaceThenRolenameIsMaxim() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.replace("1", new Role("1", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getModel()).isEqualTo("Maxim");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleModel() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.replace("10", new Role("10", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getModel()).isEqualTo("Petr");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRolenameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getModel()).isEqualTo("Petr");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        boolean result = store.replace("1", new Role("1", "Maxim"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        boolean result = store.replace("10", new Role("10", "Maxim"));
        assertThat(result).isFalse();
    }
}