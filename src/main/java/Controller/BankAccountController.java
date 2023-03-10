package Controller;
import Model.Account;
import Model.Transactions;
import Service.TransactionsService;
import Service.AccountService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

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

        app.get("login", this::getLogin);

        app.get("/Transactions/{user_id}", this::getBalanceByUserIdHandler);

        app.post("/addTransaction", this::postTransactionHandler);

        app.get("/Accounts/{account_id}/transactions", this::getAllTransactionsByUserIdHandler);

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

    /** Login into existing account handler */
    private void getLogin(Context ctx) throws JsonProcessingException {

        Account account = mapper.readValue(ctx.body(), Account.class);
        Account login = accountService.login(account);
        if(login!=null){
            ctx.json(login);
        }else{
            ctx.status(401);        //401 indicates improper authentication. Should return 401 instead of 400 if login endpoint is returned null.
        }
    }

    /** Get balance by user id handler */
    private void getBalanceByUserIdHandler(Context ctx) throws JsonProcessingException{
        int user_id = Integer.parseInt(ctx.pathParam("user_id"));
        Transactions retrieveBalance = transactionsService.getBalanceById(user_id);
        if(retrieveBalance != null){
            ctx.json(retrieveBalance);
        }

    }

    /** Post a new transaction handler */
    private void postTransactionHandler(Context ctx) throws JsonProcessingException {

        Transactions addTransaction = mapper.readValue(ctx.body(), Transactions.class);
        Transactions transaction = transactionsService.newTransaction(addTransaction);
        if (transaction != null) {
            ctx.json(transaction);
        } else {
            ctx.status(400);
        }
    }

    /** Get all transactions by id handler */
    private void getAllTransactionsByUserIdHandler(Context ctx) throws JsonProcessingException{
        int user_id = Integer.parseInt(ctx.pathParam("account_id"));
        List<Transactions> transactions = transactionsService.getTransactionsByUserId(user_id);
        ctx.json(transactions);
    }
}