package model.pet;

import model.Color;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Dog extends Pet {

    public Dog(String name, int ownerId, int age, int colorId) {
        super(name, ownerId, age, colorId);
        this.typeId = 1;
    }

    @Override
    public void makeSound() {
        System.out.println("Bark Bark!");
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", ownerId=" + ownerId +
                ", age=" + age +
                ", color='" + Color.getColor(colorId) + '\'' +
                '}';
    }
}
