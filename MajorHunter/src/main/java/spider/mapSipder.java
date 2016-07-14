package spider;

import java.util.Set;

import org.htmlparser.filters.LinkStringFilter;
import spider.HtmlParserTool;
import spider.SpiderHash;


public class mapSipder
{
    //使用种子初始化URL队列
    private void initCrawlerWithSeeds(String[] seeds)
    {
        for(int i=0;i<seeds.length;i++)
        {
            SpiderHash.addUnVisitedUrl(seeds[i]);
        }
    }

    //定义过滤器，提取以 http://www.xxxx.com开头的链接
    public void crawling(String[] seeds)
    {
        /*LinkStringFilter filter = new LinkStringFilter();*/
    }
}
