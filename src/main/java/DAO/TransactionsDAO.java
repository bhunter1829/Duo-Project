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
    public static Transactions getBalanceById(int user_id) {
        Connection connection = ConnectionSingleton.getConnection();
        try{
            String sql = "SELECT balance FROM Transactions WHERE user_id = ?";
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
            String sql = "SELECT * FROM Transactions WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, user_id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Transactions transaction = new Transactions(rs.getInt("transaction_id"),
                        rs.getInt("amount"),
                        rs.getInt("balance"),
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
            String sql = "INSERT INTO Transactions (user_id, amount, time_posted_epoch) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, transactions.getUser_id());
            preparedStatement.setInt(2, transactions.getAmount());
            preparedStatement.setLong(3, transactions.getTime_posted_epoch());

            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_transaction_id = (int) pkeyResultSet.getLong(1);
                return new Transactions(generated_transaction_id,
                        transactions.getUser_id(),
                        transactions.getAmount(),
                        transactions.getTime_posted_epoch());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

//    public Transactions patchBalanceById(){
//
//        return null;
//    }
}
