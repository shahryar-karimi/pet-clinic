package model.pet;

import model.Color;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Bird extends Pet {

    public Bird(String name, int ownerId, int age, int colorId) {
        super(name, ownerId, age, colorId);
        this.typeId = 3;
    }

    @Override
    public void makeSound() {
        System.out.println("Jik Jik!");
    }

    @Override
    public String toString() {
        return "Bird{" +
                "name='" + name + '\'' +
                ", ownerId=" + ownerId +
                ", age=" + age +
                ", color='" + Color.getColor(colorId) + '\'' +
                '}';
    }
}
