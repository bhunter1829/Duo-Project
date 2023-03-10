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

            return transactionsDAO.getBalanceById(user_id);

    }

    /** Post a new transaction Service */
    public Transactions newTransaction(Transactions transactions){
            transactions.setBalance(transactions.getAmount());
            return transactionsDAO.insertTransaction(transactions);

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
