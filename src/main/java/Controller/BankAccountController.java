package Controller;
import Model.Account;
import Model.Transactions;
import Service.TransactionsService;
import Service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        app.post("register", this::postCreateAccount);
        app.get("/Transactions/{user_id}", this::getBalanceByUserIdHandler);
        app.patch("/Transactions/{user_id}", this::patchBalanceByUserIdHandler);
        app.get("/Transactions/{user_id}", this::getAllTransactionsByUserIdHandler);
        return app;
    }

    ObjectMapper mapper = new ObjectMapper();

    /** Register new account handler */
    private void postCreateAccount(Context ctx) throws JsonProcessingException {

        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);
        if(addedAccount != null){
            ctx.json(addedAccount);
        }else{
            ctx.status(400);
        }

    }

    /** Get balance by user id handler */
    private void getBalanceByUserIdHandler(Context ctx){
        int user_id = Integer.parseInt(ctx.pathParam("account_id"));
        Transactions retrieveBalance = transactionsService.getBalanceById(user_id);
        if(retrieveBalance != null){
            ctx.json(retrieveBalance);
        }
    }

    /** Update balance by user id handler */
    private void patchBalanceByUserIdHandler(Context ctx){

    }

    /** Get all transactions by id handler */
    private void getAllTransactionsByUserIdHandler(Context ctx){

    }
}