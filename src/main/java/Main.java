import controller.MainController;
import database.DBManager;
import model.person.PersonController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import view.MainCLI;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        DBManager dbManager = context.getBean(DBManager.class);
        ArrayList<PersonController> personControllers = dbManager.loadPersons();
        MainController mainController = new MainController(dbManager, personControllers, dbManager.loadPets(personControllers));
        new MainCLI(mainController);
    }
}
