package database;

import model.person.Person;
import model.person.PersonController;
import model.pet.Pet;
import model.pet.PetFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

@Component
public class DBManager {
    private Connection connection;

    public DBManager() {
        String url = "jdbc:sqlite:pet-shop.db";
        try {
            this.connection = DriverManager.getConnection(url);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
//        createTables();
    }

    private void createTables() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS pets (\n" +
                    "   name TEXT,\n" +
                    "   ownerId INTEGER,\n" +
                    "   age INTEGER,\n" +
                    "   colorId INTEGER,\n" +
                    "   typeId INTEGER,\n" +
                    ");";
            statement.execute(query);
            query = "CREATE TABLE IF NOT EXISTS persons (\n" +
                    "   name TEXT,\n" +
                    "   age INTEGER,\n" +
                    "   address TEXT,\n" +
                    "   city TEXT,\n" +
                    "   phoneNumber TEXT,\n" +
                    "   id INTEGER,\n" +
                    ");";
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePets(ArrayList<Pet> pets) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM pets;";
            statement.execute(query);
            query = "INSERT INTO pets (name, ownerId, age, colorId, typeId) VALUES ('%s', %d, %d, %d, %d)";
            String insertQuery;
            for (Pet pet : pets) {
                insertQuery = String.format(query, pet.getName(), pet.getOwnerId(), pet.getAge(), pet.getColorId(),
                        pet.getTypeId());
                statement.execute(insertQuery);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePersons(ArrayList<PersonController> personsController) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM persons;";
            statement.execute(query);
            query = "INSERT INTO persons " +
                    "(name, age, address, city, phoneNumber, id) " +
                    "VALUES ('%s', %d, '%s', '%s', '%s', %d)";
            String insertQuery;
            for (PersonController personController : personsController) {
                Person person = personController.getPerson();
                insertQuery = String.format(query, person.getName(), person.getAge(), person.getAddress(),
                        person.getCity(), person.getPhoneNumber(), person.getId());
                statement.execute(insertQuery);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pet> loadPets(ArrayList<PersonController> personControllers) {
        ArrayList<Pet> pets = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM pets;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int ownerId = resultSet.getInt("ownerId");
                int age = resultSet.getInt("age");
                int colorId = resultSet.getInt("colorId");
                int typeId = resultSet.getInt("typeId");
                Pet pet = PetFactory.getPet(name, ownerId, age, colorId, typeId);
                pets.add(pet);
                for (PersonController personController : personControllers) {
                    if (personController.getPerson().getId() == ownerId) {
                        personController.addPet(pet);
                        break;
                    }
                }
            }
            statement.close();
        } catch (Exception e) {
            System.out.println("failed to load pets");
        }
        return pets;
    }

    public ArrayList<PersonController> loadPersons() {
        ArrayList<PersonController> personControllers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM persons;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String phoneNumber = resultSet.getString("phoneNumber");
                int id = resultSet.getInt("id");
                Person person = new Person(name, age, address, city, phoneNumber, id);
                PersonController personController = new PersonController(person);
                personControllers.add(personController);
            }
            statement.close();
        } catch (Exception e) {
            System.out.println("failed to load persons");
        }
        return personControllers;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
