package DAO;

import Model.Transactions;
import Util.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
