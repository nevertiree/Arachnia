package spider;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;

/**
 * Created by Lance on 7/10/16.
 */
public class Major {

    private int major_code;//专业代码（唯一）
    private String major_name;//专业名称
    private String major_type;//专业大类别
    private String major_level;//专业层次（本科or专科）
    private int bk_num;//开设本专业本科学校数量
    private int zk_num;//开设本专业专科学校数量

    public Major() {}//构造器

    //set方法
    public void setMajor_code(int major_code) {this.major_code = major_code;}
    public void setMajor_name(String major_name) {this.major_name = major_name;}
    public void setMajor_type(String major_type) {this.major_type = major_type;}
    public void setMajor_level(String major_level) {this.major_level = major_level;}
    public void setBk_num(int bk_num) {this.bk_num = bk_num;}
    public void setZk_num(int zk_num) {this.zk_num = zk_num;}
    //get方法
    public int getMajor_code() {return major_code;}
    public String getMajor_name() {return major_name;}
    public String getMajor_type() {return major_type;}
    public String getMajor_level() {return major_level;}
    public int getBk_num() {return bk_num;}
    public int getZk_num() {return zk_num;}
    //JSON解析方法
    public boolean parseJSON(String json_string) {
        try {
            JSONObject jsonObject =new JSONObject(json_string);
            //用JSON对象赋值
            setMajor_code(Integer.valueOf(jsonObject.getString("code")));
            setMajor_name(jsonObject.getString("specialname"));
            setMajor_type(jsonObject.getString("zytype"));
            setMajor_level(jsonObject.getString("zycengci"));
            setBk_num(Integer.valueOf(jsonObject.getString("bnum")));
            setZk_num(Integer.valueOf(jsonObject.getString("znum")));
            return true;
        }catch (JSONException e) {e.printStackTrace();}
        finally {return false;}
    }

    public void insertJSONData(Major major){

        major_code=major.getMajor_code();
        major_name=major.getMajor_name();
        major_type=major.getMajor_type();
        major_level=major.getMajor_level();
        bk_num=major.getBk_num();
        zk_num=major.getZk_num();

    }
}
