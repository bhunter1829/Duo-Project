package DAO;

import Model.Transactions;
import Util.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

//    public Transactions patchBalanceById(){
//
//        return null;
//    }
}
