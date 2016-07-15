package com.nostudy.spider;

import jdk.internal.util.xml.impl.Input;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpEntity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.HttpURLConnection;


public class advanceSpider
{

    public static boolean downloadPage(String path)
    {
        //HttpClient and DefaultHttpClient Class are both deprecated,so we use the HttpClientBuilder Class to replace it
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient =httpClientBuilder.build();

        //URL地址为path

        //定义输入和输出流
        InputStream input=null;
        OutputStream output=null;

        //get方法
        //GetMethod类已经过期，现在用HttpGet代替前者
        HttpGet getMethod=new HttpGet(path);

        try
        {
            //执行Get请求
            CloseableHttpResponse httpResponse= closeableHttpClient.execute(getMethod);

            /*
            返回状态码
            response.HTTP响应第一行是协议版本，之后是数字状态码和相关联的文本段。

            eg:
            getProtocolVersion-->HTTP/1.1
            getStatusLine().getStatusCode()-->200
            getStatusLine().getReasonPhrase()-->OK
            getStatusLine().toString()-->HTTP/1.1 200 OK

            */
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            //针对状态码处理，只处理放回值为200的值
            if (statusCode== HttpStatus.SC_OK)
            {
                //input=httpResponse.getEntity().getContent();
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity!=null)
                {
                    input=httpEntity.getContent();

                    try
                    {
                        String filename=path.substring(path.lastIndexOf('/')+1)+".html";

                        //获得文件输出流
                        output=new FileOutputStream(filename);

                        //输出到文件
                        int tempByte=-1;
                        while ((tempByte=input.read())>0)
                        {
                            output.write(tempByte);
                        }

                        //关闭入出流
                        if (input!=null) input.close();

                        //关闭输出流
                        if (output!=null) output.close();

                        return true;
                    }

                    finally
                    {
                        input.close();
                        return false;
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //关闭流并释放资源
                closeableHttpClient.close();
                return false;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return false;

    }

   /* public static void main(String[] args)
    {
        try
        {

            //抓取百度首页
            advanceSpider.downloadPage("http://www.baidu.com");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }*/
}
