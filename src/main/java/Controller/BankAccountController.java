package Controller;
import Model.Transactions;
import Service.TransactionsService;
import Service.AccountService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class BankAccountController {
    AccountService accountService;
    TransactionsService transactionsService;
    public BankAccountController(){
        this.accountService = new AccountService();
        this.transactionsService = new TransactionsService();
    }
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("/Transactions/{user_id}", this::getBalanceByUserIdHandler);
        app.patch("/Transactions/{user_id}", this::patchBalanceByUserIdHandler);
        app.get("/Transactions/{user_id}", this::getAllTransactionsByUserIdHandler);
        return app;
    }

    private void getBalanceByUserIdHandler(Context ctx){
        int user_id = Integer.parseInt(ctx.pathParam("account_id"));
        Transactions retrieveBalance = transactionsService.getBalanceById(user_id);
        if(retrieveBalance != null){
            ctx.json(retrieveBalance);
        }
    }

    private void patchBalanceByUserIdHandler(Context ctx){

    }

    private void getAllTransactionsByUserIdHandler(Context ctx){

    }
}
