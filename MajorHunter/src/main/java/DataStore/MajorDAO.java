package DataStore;

import ValueObject.MajorVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by Lance on 7/14/16.
 */
public class MajorDAO extends BaseDAO{

    public boolean insertMajor(MajorVO majorVO){
        Connection connection =null;

        try{
            //try to connect mysql with function connectMysql();
            connection=this.connectMysql();

            //edit the SQL query
            String insertQuery= "insert into major (no,name,rank) value"+"values(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(insertQuery);

            //set the ? as the specific value with getMethod of VO
            preparedStatement.setString(1,majorVO.getNo());
            preparedStatement.setString(2,majorVO.getName());
            preparedStatement.setInt(3,majorVO.getRank());

            //execute the SQL query
            int affectedCount = preparedStatement.executeUpdate();

            //judge if success insert
            if (affectedCount>0){return true;}
            else return false;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeMysql(connection);
            return false;
        }
    }

}
