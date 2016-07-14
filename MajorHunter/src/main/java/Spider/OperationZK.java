package Spider;

/**
 * Created by Lance on 7/14/16.
 */
public class OperationZK {

    public void operateZK(){

        //get the origin content from data.api
        GrabContent grabContent =new GrabContent();

        String urlAllMajorZK ="http://data.api.gkcx.eol.cn/soudaxue/queryspecialty.html?messtype=jsonp&zycengci=专科&zytype=&page=1&size=10&keyWord2=&schoolsort=&callback=jQuery1830032572212268669354_1468135281347&_=1468135282182";//全部专科专业目录

        String resultAllMajorZK= grabContent.grabWithJavaNet(urlAllMajorZK);//访问本科全部专业链接


    }
}
