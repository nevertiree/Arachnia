package spider;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.xml.ws.http.HTTPException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lance on 7/9/16.
 */
public class Main {
    public static String sendGet(String url) {

        StringBuilder result =new StringBuilder();//定义存储结果的字符串
        BufferedReader input =null;//定义缓冲字符输入流

        try {
            URL realURL=new URL(url);//将string转成URL对象
            URLConnection connection=realURL.openConnection();//初始化链接
            connection.connect();//开始链接
            input=new BufferedReader(new InputStreamReader(connection.getInputStream()));//初始化输入缓冲流
            String aLine;//临时存储抓到的每一行数据
            while ((aLine=input.readLine())!=null) {
                result.append(aLine);//遍历抓取到的每一行数据并将其存储到result里
            }
        }
        catch (Exception e) {
            System.out.println("发送GET请求出现异常"+e);
            e.printStackTrace();
        }finally {
            try{
                if (input!=null){   input.close();}
            }catch (Exception e2){e2.printStackTrace();}
        }
        return result.toString();
    }

    public static String RegexString(String targetStr,String patternStr){
        //定义一个样式模板，在此使用regex。在targetStr中寻找与patternStr匹配的文字内容

        Pattern  pattern = Pattern.compile(patternStr);//定义要挑出的字段
        Matcher matcher= pattern.matcher(targetStr);//把要大段目标文字进行匹配

        String matcheredString= null;

        if (matcher.find()){
            for (int i=1;i<matcher.groupCount();i++){
                matcheredString+=matcher.group(i)+"\n";
            }
        }
        return matcheredString;
    }

    public static int IsMatcher(String targetStr,String patternStr){

        Pattern  pattern = Pattern.compile(patternStr);//定义要挑出的字段
        Matcher matcher= pattern.matcher(targetStr);//把要大段目标文字进行匹配

        if (matcher.find()){//如果找到匹配的部分
            return 1;
        }
        else return 0;
    }

    public static void main(String[] args){

        String urlAllMajorBK ="http://data.api.gkcx.eol.cn/soudaxue/queryspecialty.html?messtype=jsonp&zycengci=本科&zytype=&page=1&size=10&keyWord2=&schoolsort=&callback=jQuery1830032572212268669354_1468135281347&_=1468135282182";//全部本科专业目录
        String urlAllMajorZK ="http://data.api.gkcx.eol.cn/soudaxue/queryspecialty.html?messtype=jsonp&zycengci=专科&zytype=&page=1&size=10&keyWord2=&schoolsort=&callback=jQuery1830032572212268669354_1468135281347&_=1468135282182";//全部专科专业目录

        String resultAllMajorBK=sendGet(urlAllMajorBK);//访问本科全部专业链接
        String resultAllMajorZK=sendGet(urlAllMajorZK);//访问专科全部专业链接



//{   "specialname": "贸易经济",   "code": "020106",   "specialurl": "/schoolhtm/specialty/10032/specialtyDetail50728.htm",   "clicks": "8251",   "monthclicks": "8251",   "weekclicks": "8251",   "zycengci": "本科",   "zytype": "经济学类",   "bnum": "39",   "znum": "4",   "zyid": "50728",   "ranking": "245",   "rankingType": "14"  }

// (\{   "specialname": ".*",   "code": ".*",   "specialurl": ".*",   "clicks": ".*",   "monthclicks": ".*",   "weekclicks": ".*",   "zycengci": ".*",   "zytype": ".*",   "bnum": ".*",   "znum": ".*",   "zyid": ".*",   "ranking": ".*",   "rankingType": ".*"  \});

        String imgSrc=RegexString(resultAllMajorBK,"\\\"specialname\\\": \\\"(.*)\\\",   \\\"code\\\": \\\"(.*)\\\",   \\\"specialurl\\\": \\\"(.*)\\\",   \\\"clicks\\\": \\\"(.*)\\\",   \\\"monthclicks\\\": \\\"(.*)\\\",   \\\"weekclicks\\\": \\\"(.*)\\\",   \\\"zycengci\\\": \\\"(.*)\\\",   \\\"zytype\\\": \\\"(.*)\\\",   \\\"bnum\\\": \\\"(.*)\\\",   \\\"znum\\\": \\\"(.*)\\\",   \\\"zyid\\\": \\\"(.*)\\\",   \\\"ranking\\\": \\\"(.*)\\\",   \\\"rankingType\\\": \\\"(.*)\\\"");

        System.out.println(resultAllMajorBK);

        System.out.println("-----------------------------------");

        System.out.println(imgSrc);


        int  isMatcher=IsMatcher(resultAllMajorBK,"\"specialname\": \"(.*)\",   \"code\": \"(.*)\",   \"specialurl\": \"(.*)\",   \"clicks\": \"(.*)\",   \"monthclicks\": \"(.*)\",   \"weekclicks\": \"(.*)\",   \"zycengci\": \"(.*)\",   \"zytype\": \"(.*)\",   \"bnum\": \"(.*)\",   \"znum\": \"(.*)\",   \"zyid\": \"(.*)\",   \"ranking\": \"(.*)\",   \"rankingType\": \"(.*)\"");

        System.out.println(isMatcher);

    }
}