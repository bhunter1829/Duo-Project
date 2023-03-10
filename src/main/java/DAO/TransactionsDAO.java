package DAO;

import Model.Transactions;
import Util.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class TransactionsDAO {

    /** Get balance by user_id DAO */
    public Transactions getBalanceById(int user_id) {
        Connection connection = ConnectionSingleton.getConnection();
        try{
            String sql = "SELECT balance,user_id FROM Transactions WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, user_id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Transactions transactions = new Transactions(rs.getInt("balance"),
                        rs.getInt("user_id"));
                        return transactions;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /** Retrieve all transactions by user_id DAO */
    public List<Transactions> getTransactionsByUserId(int user_id) {
        Connection connection = ConnectionSingleton.getConnection();
        List<Transactions> transactions = new ArrayList<>();
        try {
            String sql = "SELECT transaction_id, amount, time_posted_epoch, user_id FROM Transactions WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, user_id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Transactions transaction = new Transactions(rs.getInt("transaction_id"),
                        rs.getInt("amount"),
                        rs.getLong("time_posted_epoch"),
                        rs.getInt("user_id"));
                        transactions.add(transaction);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return transactions;
    }

    /** insert transaction DAO */
    public Transactions insertTransaction(Transactions transactions) {
        Connection connection = ConnectionSingleton.getConnection();
        try {
            String sql = "INSERT INTO Transactions (amount, time_posted_epoch, user_id) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            preparedStatement.setInt(1, transactions.getAmount());
            preparedStatement.setLong(2, transactions.getTime_posted_epoch());
            preparedStatement.setInt(3, transactions.getUser_id());


            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_transaction_id = (int) pkeyResultSet.getLong(1);
                return new Transactions(generated_transaction_id,
                        transactions.getAmount(),
                        transactions.getTime_posted_epoch(),
                        transactions.getUser_id()
                        );
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void patchBalanceById(int id, int amount) {

    }
}
