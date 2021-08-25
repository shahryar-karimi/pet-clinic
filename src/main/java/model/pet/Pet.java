package model.pet;

import model.Color;
import model.person.Person;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Lazy
@Component
@Scope("prototype")
public abstract class Pet {
    protected String name;
    protected int ownerId;
    protected int age;
    protected int colorId;
    protected int typeId;

    public Pet(String name, int ownerId, int age, int colorId) {
        this.name = name;
        this.ownerId = ownerId;
        this.age = age;
        this.colorId = colorId;
    }

    public Pet setName(String name) {
        this.name = name;
        return this;
    }

    public Pet setOwnerId(int ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public Pet setAge(int age) {
        this.age = age;
        return this;
    }

    public Pet setColor(int colorId) {
        this.colorId = colorId;
        return this;
    }

    public String getName() {
        return name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getAge() {
        return age;
    }

    public int getColorId() {
        return colorId;
    }

    public int getTypeId() {
        return typeId;
    }

    public abstract void makeSound();
}
