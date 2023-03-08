import Util.ConnectionSingleton;
import Controller.BankAccountController;
import io.javalin.Javalin;

public class Application {
    public static void main(String[] args) {
//        this line is just for testing that your tables get set up correctly
//        if not, you'll get a stack trace
        ConnectionSingleton.getConnection();

        BankAccountController controller = new BankAccountController();
        Javalin app = controller.startAPI();
        app.start(8080);






    }
}
