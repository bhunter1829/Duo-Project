package Service;

import Model.AccountModel;
import DAO.AccountDAO;
public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public AccountModel addAccount(AccountModel account){
        if(account.getUsername() =="" || account.getPassword().length() <= 3){
            return null;
        }

        return accountDAO.insertAccount(account);


    }
}
