package bd_task;

import java.sql.*;


public class BD_PSQL {
    private String userName;
    private String userPassword;
    private String URL;


    public BD_PSQL(String userName, String userPassword, String URL) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.URL = URL;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Connection getConnection(){
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(URL, userName, userPassword);

            if (conn == null) {
                System.out.println("Don't connect to "+ URL);
                System.exit(-1);
            }
        } catch (ClassNotFoundException|SQLException e) {
           e.printStackTrace();
        }

        return conn;
    }
}
