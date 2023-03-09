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

//    public Transactions patchBalanceById(Transactions transactions, int user_id) {
//        if(transactions.getUser_id() == user_id){
//            transactionsDAO.patchBalanceById(transactions, user_id);
//        }
//        return null;
//    }
}
