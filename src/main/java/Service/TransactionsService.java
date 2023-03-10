package Service;

import DAO.TransactionsDAO;
import Model.Transactions;

import java.util.List;

public class TransactionsService {
    TransactionsDAO transactionsDAO;

    public TransactionsService(){
        transactionsDAO = new TransactionsDAO();
    }

    public TransactionsService(TransactionsDAO transactionsDAO){
        this.transactionsDAO = transactionsDAO;
    }
    /** View balance by user_id Service */
    public Transactions getBalanceById(int user_id) {
        Transactions transactions = TransactionsDAO.getBalanceById(user_id);
        if(TransactionsDAO.getBalanceById(user_id) != null){
            return transactions;
        }
        return null;
    }

    /** Post a new transaction Service */
    public Transactions newTransaction(Transactions transactions){
        if(transactions.getAmount() != 0){
            return transactionsDAO.insertTransaction(transactions);
        }
        return null;
    }

    /** Retrieve all transaction by user_id Service */
    public List<Transactions> getTransactionsByUserId(int user_id) {
        return transactionsDAO.getTransactionsByUserId(user_id);
    }


//    public Transactions patchBalanceById(Transactions transactions, int user_id) {
//        if(transactions.getUser_id() == user_id){
//            transactionsDAO.patchBalanceById(transactions, user_id);
//        }
//        return null;
//    }
}
