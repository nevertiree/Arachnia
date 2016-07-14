package spider;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import javax.xml.ws.http.HTTPException;
import java.io.*;

/**
    用来下载网页的工具类
 */

/*
public class DownloadTool
{
    //根据URL和网页类型生成需要保存的网页的文件名，去除URL中的非文件名字符
    public String getFileNameByUrl(String url,String contentType)
    {
        // 移除 "http://" 这七个字符
        url=url.substring(7);

        // 确认抓取到的页面为 text/html 类型
        if (contentType.indexOf("html")!=-1)
        {
            //把所有的url中的特殊符号转化成下划线
            url=url.replaceAll("[\\?/:*|<>\"]", "_")+".html";
        }
        else
        {
            url=url.replaceAll("[\\?/:*|<>\"]", "_")+"."+contentType.substring(contentType.lastIndexOf("/")+1);
        }

        return url;
    }

    *//*

*/
/*//*
*/
/*
/保存网页字节数组到本地文件，filePath为要保存的文件的相对地址
    public void save2Local(byte[] data,String filePath)
    {
        try
        {
            DataOutputStream output= new DataOutputStream(new FileOutputStream(new File(filePath)));
            for (int i=0;i<data.length;i++)
            {
                output.write(data[i]);
            }

            output.flush();
            output.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // 下载 URL 指向的网页
    *//*
*/
/*
*//*

*/
/*public String downloadFile(String url)
    {
        String filePath =null;

        //生成 HttpClinet对象并设置参数
        CloseableHttpClient closeableHttpClient= HttpClients.createDefault();

        *//*
*/
/*
*//*

*/
/**//*
*/
/*
*//*

*/
/*setConnectTimeout：设置连接超时时间，毫秒。
        setConnectionRequestTimeout：设置从connect Manager获取Connection超时毫秒。目前版本可以共享连接池。
        setSocketTimeout：请求获取数据的超时毫秒。如果访问一个接口，多少时间内无法返回就弃用。*//*
*/
/*
*//*

*/
/**//*
*/
/*
*//*

*/
/*
        RequestConfig requestConfig= RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000).setSocketTimeout(5000).build();

        //生成GetMethod对象并设置参数
        HttpGet httpGet= new HttpGet(url);

        //设置get请求超时5s-->由requestConfig传入
        httpGet.setConfig(requestConfig);

        //设置GET重试处理

        try
        {
            //执行GET请求
            HttpResponse httpResponse=closeableHttpClient.execute(httpGet);

            //判断访问的状态码
            int statusCode=httpResponse.getStatusLine().getStatusCode();

            if (statusCode!= HttpStatus.SC_OK)
            {
                System.err.println("Method failed"+httpResponse.getStatusLine());
                filePath=null;
            }

            try
            {
                //处理HTTP响应内容
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity!= null)
                {
                    //取得文件保存的名字
                    byte[] responseBody= httpEntity.getContent().toString().getBytes();

                    //保存位置
                    filePath="/home/Lance/Document/"+getFileNameByUrl(url,httpResponse.getHeaders("Content-Type").toString());

                    //进行保存
                    save2Local(responseBody,filePath);
                }
            }
            catch (HTTPException e)
            {
                // 发生致命的异常，可能是协议不对或者返回的内容有问题
                System.out.println("请检查你的http地址是否正确");
                e.printStackTrace();
            }


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        finally
        {
            //释放链接??
        }
        return filePath;
    }
}**/