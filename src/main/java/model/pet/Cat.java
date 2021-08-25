package model.pet;

import model.Color;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Cat extends Pet {

    public Cat(String name, int ownerId, int age, int colorId) {
        super(name, ownerId, age, colorId);
        this.typeId = 2;
    }

    @Override
    public void makeSound() {
        System.out.println("Meow Meow!");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", ownerId=" + ownerId +
                ", age=" + age +
                ", color='" + Color.getColor(colorId) + '\'' +
                '}';
    }
}
