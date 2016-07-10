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
        //定义存储结果的字符串
        StringBuilder result =new StringBuilder();

        String filePath =null;

        //定义缓冲字符输入流
        BufferedReader input =null;

        try {
            //将string转成URL对象
            URL realURL=new URL(url);
            //初始化链接
            URLConnection connection=realURL.openConnection();
            //开始链接
            connection.connect();
            //初始化输入缓冲流
            input=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //临时存储抓到的每一行数据
            String aLine;
            while ((aLine=input.readLine())!=null) {
                //遍历抓取到的每一行数据并将其存储到result里
                result.append(aLine);
            }
        }
        catch (Exception e) {
            System.out.println("发送GET请求出现异常"+e);
            e.printStackTrace();
        }finally {
            try{
                if (input!=null){
                    input.close();
                }
            }catch (Exception e2){e2.printStackTrace();}
        }
        return result.toString();

    }

    public static String[] RegexString(String targetStr,String patternStr){
        //定义一个样式模板，在此使用regex。在targetStr中寻找与patternStr匹配的文字内容

        //定义要挑出的字段
        Pattern  pattern = Pattern.compile(patternStr);

        //把要大段目标文字进行匹配
        Matcher matcher= pattern.matcher(targetStr);

        String[] matcheredString= new String[11];

        if (matcher.find()){
            for (int i=0;i<matcher.groupCount();i++){
                matcheredString[i]=matcher.group(i);
            }
        }

        return matcheredString;
    }

    public static int IsMatcher(String targetStr,String patternStr){

        Pattern  pattern = Pattern.compile(patternStr);

        //把要大段目标文字进行匹配
        Matcher matcher= pattern.matcher(targetStr);

        String[] matcheredString= new String[11];

        //如果找到匹配的部分
        if (matcher.find()){
            return 1;
        }
        else return 0;
    }

    public static void main(String[] args){
        //定义即将访问的链接
        String url ="http://data.api.gkcx.eol.cn/soudaxue/queryspecialty.html?messtype=jsonp&zycengci=本科&zytype=&page=1&size=10&keyWord2=&schoolsort=&callback=jQuery1830032572212268669354_1468135281347&_=1468135282182";

        //访问链接并获取页面内容
        String result=sendGet(url);

        /*
        <tr><td><a href="/schoolhtm/schoolTemple/school31.htm" title="北京大学">.</a></td><td title="中国语言文学类">中国语言文学类</td><td width="10%&quot;">教育部直属</td><td width="9%&quot;">985大学</td><td width="9%&quot;">211大学</td><td><a onclick="addCompare('31','北京大学')" class="query_db"></a></td></tr>
         */

        //String regexString="<tr><td><a href=\\\"(.+?)\\\" title=\\\"(.+?)\\\">.</a></td><td title=\\\"(.+?)\\\">(.+?)</td><td (.+?)>(.+?)</td><td (.+?)>(.+?)</td><td (.+?)>(.+?)</td><td><a onclick=\"addCompare(.+?)\\\" class=\\\"query_db\\\"></a></td></tr>";

//        String imgSrc=RegexString(result,"src=\\\"(.+?)\\\"");

        String[] imgSrc=RegexString(result,"<tr><td><a href=\\\"/schoolhtm/schoolTemple/school31.htm\\\" title=\\\"北京大学\\\">.</a></td><td title=\\\"中国语言文学类\\\">中国语言文学类</td><td width=\\\"10%&quot;\\\">教育部直属</td><td width=\\\"9%&quot;\\\">985大学</td><td width=\"9%&quot;\\\">211大学</td><td><a onclick=\\\"addCompare('31','北京大学')\\\" class=\\\"query_db\\\"></a></td></tr>");


        System.out.println(result);

        for (int i=0;i<imgSrc.length;i++)
        {
            System.out.println(imgSrc[i]);
        }

        int  isMatcher=IsMatcher(result,"<tr><td><a href=\\\"/schoolhtm/schoolTemple/school31.htm\\\" title=\\\"北京大学\\\">.</a></td><td title=\\\"中国语言文学类\\\">中国语言文学类</td><td width=\\\"10%&quot;\\\">教育部直属</td><td width=\\\"9%&quot;\\\">985大学</td><td width=\"9%&quot;\\\">211大学</td><td><a onclick=\\\"addCompare('31','北京大学')\\\" class=\\\"query_db\\\"></a></td></tr>");

        System.out.println(isMatcher);

    }
}