package Controller;

import io.javalin.Javalin;

public class BankAccountController {
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        return app;
    }
}
