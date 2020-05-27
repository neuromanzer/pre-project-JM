package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcDBHelper {

    private static final String connectionUrl;

    static {                        //init static block
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
        StringBuilder url = new StringBuilder();

        url
                .append("jdbc:mysql://")        //db type
                .append("localhost:")         //host name
                .append("3306/")            //port
                .append("db_user?")          //db name
                .append("user=root&")          //login
                .append("password=root&")
                .append("serverTimezone=UTC");

        System.out.println("URL: " + url + "\n");

        connectionUrl = url.toString();
    }

    private JdbcDBHelper() {

    }

    public static Connection getMysqlConnection() {
        try {
            return DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
}
