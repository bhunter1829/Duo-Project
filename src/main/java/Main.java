import Controller.BankAccountController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args){
        BankAccountController controller = new BankAccountController();
        Javalin app = controller.startAPI();
        app.start(8080);
    }
}