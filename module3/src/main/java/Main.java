import controller.MainController;
import jbdc.factory.JdbcFactory;

public class Main {

    public static void main(String[] args) {
        JdbcFactory.getInstance().initDB(Main.class);
        MainController mainController = new MainController();
        mainController.start();
    }
}
