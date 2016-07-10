package spider;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lance on 7/9/16.
 */
public class ZhihuRecommendations
{
    public String question;
    public String questionDescription;
    public String zhihuUrl;
    public ArrayList<String> answers;

    public ZhihuRecommendations()
    {
        question="";
        questionDescription="";
        zhihuUrl="";
        answers=new ArrayList<String>();
    }

    @Override
    public String toString()
    {
        return "问题:"+question+"\n"+"描述:"+questionDescription+"\n"+"链接:"+zhihuUrl+"\n回答"+answers+"\n";
    }

    static ArrayList<ZhihuRecommendations> GetZhihu(String content)
    {
        //预定义一个ArrayList来存储结果
        ArrayList<ZhihuRecommendations> results=new ArrayList<ZhihuRecommendations>();

        // 用来匹配url，也就是问题的链接
        Pattern urlPattern = Pattern.compile("<h2>.+?question_link.+?href=\"(.+?)\".+?</h2>");
        Matcher urlMatcher = urlPattern.matcher(content);
        // 是否存在匹配成功的对象
        boolean isFind = urlMatcher.find();
        /*while (isFind) {
            // 定义一个知乎对象来存储抓取到的信息
            ZhihuRecommendations zhihuTemp = new ZhihuRecommendations(urlMatcher.group(1));
            // 添加成功匹配的结果
            results.add(urlMatcher.group(1));
            // 继续查找下一个匹配对象
            isFind = urlMatcher.find();
        }*/
        return results;
    }


}
