/**
 * MAD204-01 | Assignment 1 - Part A
 * Author: Manpreet Singh
 * Student ID: A00198842
 * Date: 2025-10-06
 * Description: Abstract Person class for People Management System
 */

public abstract class Person {
    protected int id;
    protected String name;
    protected int age;

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public abstract void introduce();

    public void celebrateBirthday() {
        this.age++;
        System.out.println(name + " is now " + age + " years old.");
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
