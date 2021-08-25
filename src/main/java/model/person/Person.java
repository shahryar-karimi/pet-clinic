package model.person;

import model.pet.Pet;

import java.util.ArrayList;

public class Person {
    private String name;
    private int age;
    private String address;
    private String city;
    private String phoneNumber;
    private transient ArrayList<Pet> pets;
    private final int id;

    public Person(String name, int age, String address, String city, String phoneNumber, int id) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.pets = new ArrayList<>();
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", pets=" + pets +
                ", id=" + id +
                '}';
    }
}
