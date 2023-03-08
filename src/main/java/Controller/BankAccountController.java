package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.javalin.Javalin;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;



import Service.AccountService;
import Model.AccountModel;

public class BankAccountController {
    AccountService accountService;

    public BankAccountController(){
        this.accountService = new AccountService();
    }

    public Javalin startAPI(){
        Javalin app = Javalin.create();

        app.post("register", this::postCreateAccount);

        return app;
    }

    ObjectMapper mapper = new ObjectMapper();

    private void postCreateAccount(Context ctx) throws JsonProcessingException {

        AccountModel account = mapper.readValue(ctx.body(),AccountModel.class);
        AccountModel addedAccount = accountService.addAccount(account);
        if(addedAccount != null){
            ctx.json(addedAccount);
        }else{
            ctx.status(400);
        }

    }
}


