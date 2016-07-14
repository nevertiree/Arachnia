package spider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Lance on 7/10/16.
 */
public class MysqlLibs {

    private static final String url="jdbc:mysql://127.0.0.1";
    private static final String name="com.mysql.jdbc.Driver";
    private static final String user="root";
    private static final String password="0512";

    public Connection connection=null;
    public PreparedStatement preparedStatement =null;

    public MysqlLibs(String sql){
        try {
            Class.forName(name);//指定链接类型;
            connection= DriverManager.getConnection(url,user,password);//获取链接
            preparedStatement=connection.prepareStatement(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeMysql(){
        try {
            this.connection.close();
            this.preparedStatement.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


}
