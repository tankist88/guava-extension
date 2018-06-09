package org.object2source.extension.guava.data;

import java.util.Objects;

public class TestClass {
    private int id;

    public TestClass() {

    }

    public TestClass(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestClass testClass = (TestClass) o;
        return id == testClass.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
