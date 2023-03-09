package DAO;

import java.sql.*;

import Model.Account;
import Util.ConnectionSingleton;
public class AccountDAO {

    public Account insertAccount(Account account){      //Insert new account using username + password, returns generated key id + username + password.
        Connection connection = ConnectionSingleton.getConnection();

        try {
            String sql = "INSERT INTO Accounts (username, password) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();
            ResultSet primarykeyRSet = preparedStatement.getGeneratedKeys();
            if(primarykeyRSet.next()){
                int generated_account_id = (int) primarykeyRSet.getLong(1);
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;

    }
}
