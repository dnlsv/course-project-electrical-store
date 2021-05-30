package server.database;

import server.model.Order;
import server.model.Product;
import server.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserTable {

    private Connection connection;
    private Statement statement;
    private ArrayList<User> arrayList;
    private ResultSet resultSet;
    private String query;

    public UserTable(Connection connection){
        this.connection = connection;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<User> getUserTable(){
        query = "SELECT * FROM user;";
        arrayList = new ArrayList<User>();
        try {
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                User user = new User(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4));
                arrayList.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arrayList;
    }

    public boolean AccountVerification(String login, String password){
        boolean flag = false;
        arrayList = getUserTable();
        for(int i = 0; i < arrayList.size(); i++){
            if(arrayList.get(i).getUserLogin().equals(login) && arrayList.get(i).getUserPassword().equals(password))
                flag = true;
        }
        return flag;
    }

    public int getUserRoleID(String login){
        int roleID = 0;
        arrayList = getUserTable();
        for(int i = 0; i < arrayList.size(); i++){
            if(arrayList.get(i).getUserLogin().equals(login))
                roleID = arrayList.get(i).getUserRoleID();
        }
        return roleID;
    }

    public ArrayList<String> getUserNameList(){
        ArrayList<String> userNameList = new ArrayList<String>();
        ArrayList<User> arrayList = getUserTable();

        for(int i = 0; i < arrayList.size(); i++){
            userNameList.add(arrayList.get(i).getUserName());
        }

        return userNameList;
    }
}
