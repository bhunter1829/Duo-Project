package Service;

import DAO.TransactionsDAO;
import Model.Transactions;

public class TransactionsService {
    TransactionsDAO transactionsDAO;

    public TransactionsService(){
        transactionsDAO = new TransactionsDAO();
    }

    public TransactionsService(TransactionsDAO transactionsDAO){
        this.transactionsDAO = transactionsDAO;
    }
    /** View balance by user_id */
    public Transactions getBalanceById(int user_id) {
        Transactions transactions = TransactionsDAO.getBalanceById(user_id);
        if(TransactionsDAO.getBalanceById(user_id) != null){
            return TransactionsDAO.getBalanceById(user_id);
        }
        return transactions;
    }
}
