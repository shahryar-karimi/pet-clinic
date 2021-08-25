package model.person;

import model.pet.Pet;

public class PersonController {
    private final Person person;

    public PersonController(Person person) {
        this.person = person;
    }

    public void addPet(Pet pet) {
        person.getPets().add(pet);
    }

    public void removePet(Pet pet) {
        person.getPets().remove(pet);
    }

    public Person getPerson() {
        return person;
    }
}
