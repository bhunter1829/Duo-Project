package Controller;
import Service.TransactionsService;
import Service.AccountService;
import Model.Transactions;
import Model.Account;
import io.javalin.Javalin;

public class BankAccountController {
    AccountService accountService;
    TransactionsService transactionsService;
    public BankAccountController(){
        this.accountService = new AccountService();
        this.transactionsService = new TransactionsService();
    }
    public Javalin startAPI() {

        Javalin app = Javalin.create();
        return app;
    }
}
