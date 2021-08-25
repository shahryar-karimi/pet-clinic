package model.pet;

public class PetFactory {
    public static Pet getPet(String name, int ownerId, int age, int colorId, int typeId) {
        return switch (typeId) {
            case 1 -> new Dog(name, ownerId, age, colorId);
            case 2 -> new Cat(name, ownerId, age, colorId);
            case 3 -> new Bird(name, ownerId, age, colorId);
            default -> null;
        };
    }
}
