package server.model;

public class User {

    String userName;
    String userLogin;
    String userPassword;
    int userRoleID;

    public User() {
    }

    public User(String userName, String userLogin, String userPassword, int userRoleID) {
        this.userName = userName;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userRoleID = userRoleID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserRoleID() {
        return userRoleID;
    }

    public void setUserRoleID(int userRoleID) {
        this.userRoleID = userRoleID;
    }
}
