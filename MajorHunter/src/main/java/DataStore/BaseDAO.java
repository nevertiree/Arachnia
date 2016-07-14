package DataStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Lance on 7/14/16.
 */
public class BaseDAO {

    public Connection connectMysql(){
        try {
            //load the jdbc driver class of mysql
            Class.forName("com.mysql.jdbc.Driver");
            //try to connect mysql with user as root and password as 0512
            Connection connection= DriverManager.getConnection(
                    "jdbc:mysql://lcoalhost:3306/Spider",
                    "root","0512");
            return connection;
        }catch (Exception e) {e.printStackTrace();}
        return null;
    }

    public void closeMysql(Connection connection){
        if (connection!=null){
            try {
                connection.close();
            }catch (SQLException e){e.printStackTrace();}
        }
    }


}
