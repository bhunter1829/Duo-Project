package Controller;
import Service.BalanceService;
import Service.AccountService;
import io.javalin.Javalin;

public class BankAccountController {
    AccountService accountService;
    BalanceService balanceService;
    public BankAccountController(){
        this.accountService = new AccountService();
        this.balanceService = new BalanceService();
    }
    public Javalin startAPI() {

        Javalin app = Javalin.create();
        return app;
    }
}
