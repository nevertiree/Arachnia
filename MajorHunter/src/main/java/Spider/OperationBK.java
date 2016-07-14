package Spider;

/**
 * Created by Lance on 7/14/16.
 */
public class OperationBK {

    public void operateBK(){

        //get the origin content from data.api
        GrabContent grabContent =new GrabContent();
        String urlAllMajorBK ="http://data.api.gkcx.eol.cn/soudaxue/queryspecialty.html?messtype=jsonp&zycengci=本科&zytype=&page=1&size=10&keyWord2=&schoolsort=&callback=jQuery1830032572212268669354_1468135281347&_=1468135282182";//全部本科专业目录
        String resultAllMajorBK= grabContent.grabWithJavaNet(urlAllMajorBK);//访问本科全部专业链接

        String regexBK = "";

        //prepare JSON-store method
        StringBuilder jsonResult =new StringBuilder();

        //get the single-line JSON format content
        AnalysisContent analysisContent =new AnalysisContent();
        while (analysisContent.valuedContent(resultAllMajorBK,"school"))
        {
            analysisContent.integrateContent(resultAllMajorBK,regexBK);
        }



    }
}
