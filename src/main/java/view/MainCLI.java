package view;

import controller.MainController;
import model.Color;
import model.person.PersonController;
import model.pet.Pet;

import java.util.Scanner;
import java.util.regex.Pattern;

public class MainCLI extends Thread {
    private final MainController mainController;
    private static final Scanner scanner = new Scanner(System.in);

    public MainCLI(MainController mainController) {
        this.mainController = mainController;
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            showMenu();
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> addPet();
                case "2" -> addPerson();
                case "3" -> printPersons();
                case "4" -> printPets();
                case "5" -> exit();
            }
        }
    }

    public void showMenu() {
        System.out.println("1.add pet\n" +
                "2.add person\n" +
                "3.print all persons\n" +
                "4.print all pets\n" +
                "5.exit");
    }

    public void addPet() {
        System.out.println("Enter your pet name:");
        String name = scanner.nextLine();
        if (name.equals("cancel")) return;

        System.out.println("Enter your pet ownerId from 0 to " + (mainController.getPersonControllers().size() - 1));
        String temporaryOwnerId = scanner.nextLine();
        if (temporaryOwnerId.equals("cancel")) return;
        String regex = "[0-9]+";
        int ownerId = getInt(regex, temporaryOwnerId, mainController.getPersonControllers().size());

        System.out.println("Enter your pet Color:");
        showColorMenu();
        String temporaryColor = scanner.nextLine();
        if (temporaryColor.equals("cancel")) return;
        int colorId = getInt(regex, temporaryColor, Color.values().length);

        System.out.println("Enter your pet age from 0 to 20:");
        String temporaryAge = scanner.nextLine();
        if (temporaryAge.equals("cancel")) return;
        regex = "[1-9]+";
        int age = getInt(regex, temporaryAge, 21);

        System.out.println("What kind of pet do you want?\n1.dog\n2.cat\n3.bird");
        String temporaryTypeId = scanner.nextLine();
        regex = "[1-3]";
        int typeId = getInt(regex, temporaryTypeId, 4);

        String msg = mainController.addPet(name, ownerId, age, colorId, typeId);
        System.out.println(msg);
    }

    public void addPerson() {
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        if (name.equals("cancel")) return;

        System.out.println("Enter your age:");
        String temporaryAge = scanner.nextLine();
        if (temporaryAge.equals("cancel")) return;
        String regex = "[0-9]+";
        int age = getInt(regex, temporaryAge, 200);

        System.out.println("Enter your address:");
        String address = scanner.nextLine();
        if (address.equals("cancel")) return;

        System.out.println("Enter your city:");
        String city = scanner.nextLine();
        if (city.equals("cancel")) return;

        System.out.println("Enter your phoneNumber:");
        String phoneNumber = scanner.nextLine();
        if (phoneNumber.equals("cancel")) return;

        String msg = mainController.addPerson(name, age, address, city, phoneNumber);
        System.out.println(msg);
    }

    public void printPersons() {
        for (PersonController personController : mainController.getPersonControllers())
            System.out.println(personController.getPerson());
    }

    private void printPets() {
        for (Pet pet : mainController.getPets())
            System.out.println(pet);
    }

    private void exit() {
        mainController.exit();
        System.exit(0);
    }

    public int getInt(String regex, String input, int size) {
        int result;
        while (true) {
            while (!Pattern.matches(regex, input)) {
                System.err.println("Wrong input plz just enter a number:");
                input = scanner.nextLine();
            }
            result = Integer.parseInt(input);
            if (result >= 0 && result < size)
                return result;
        }
    }

    private void showColorMenu() {
        System.out.println("1.BLACK\n" +
                "2.WHITE\n" +
                "3.RED\n" +
                "4.ORANGE\n" +
                "5.YELLOW\n" +
                "6.GREEN\n" +
                "7.BLUE\n" +
                "8.PURPLE\n" +
                "9.BROWN\n" +
                "10.GRAY");
    }
}
