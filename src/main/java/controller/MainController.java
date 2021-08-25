package controller;

import database.DBManager;
import model.person.Person;
import model.person.PersonController;
import model.pet.Pet;
import model.pet.PetFactory;

import java.util.ArrayList;

public class MainController {
    private final DBManager dbManager;
    private final ArrayList<PersonController> personControllers;
    private final ArrayList<Pet> pets;

    public MainController(DBManager dbManager, ArrayList<PersonController> personControllers, ArrayList<Pet> pets) {
        this.dbManager = dbManager;
        this.personControllers = personControllers;
        this.pets = pets;
    }

    public void save() {
        dbManager.savePets(pets);
        dbManager.savePersons(personControllers);
    }

    public void exit() {
        save();
        dbManager.close();
    }

    public String addPet(String name, int ownerId, int age, int colorId, int typeId) {
        Pet pet = PetFactory.getPet(name, ownerId, age, colorId, typeId);
        if (pet != null) {
            pets.add(pet);
            searchPersonController(ownerId).addPet(pet);
            return "This pet added successfully";
        } else return "Failed to add this pet";
    }

    public void remove(Pet pet) {
        pets.remove(pet);
    }

    public String addPerson(String name, int age, String address, String city, String phoneNumber) {
        synchronized (personControllers) {
            Person person = new Person(name, age, address, city, phoneNumber, personControllers.size());
            PersonController personController = new PersonController(person);
            personControllers.add(personController);
            return "This person added successfully";
        }
    }

    public void removePerson(Person person) {
        for (PersonController personController : personControllers) {
            if (personController.getPerson().equals(person)) {
                personControllers.remove(personController);
                return;
            }
        }
    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public ArrayList<PersonController> getPersonControllers() {
        return personControllers;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public PersonController searchPersonController(int id) {
        for (PersonController personController : personControllers)
            if (personController.getPerson().getId() == id)
                return personController;
        return null;
    }
}
