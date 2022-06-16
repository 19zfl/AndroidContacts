package com.example.studentdemo;

public class Student {
    public int ID=-1;
    public String  name;
    public int age;
    public float height;


    public Student(int ID, String name, int age, float height) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + ID +
                ", 姓名='" + name + '\'' +
                ", 年龄=" + age +
                ", 身高=" + height +
                '}';
    }
}
