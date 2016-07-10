package spider;

import java.util.Set;
import java.util.HashSet;

/**
 * Created by Lance on 7/9/16.
 */
public class SpiderHash
{
    //已访问的url集合，即Visited表
    private static Set<Object> visitedUrl=new HashSet<>();

    //添加访问过的URL到队列中
    public static void addVisitedUrl(String url)
    {
        visitedUrl.add(url);
    }

    //移除访问过的 URL
    public static void removeVisitedUrl(String url)
    {
        visitedUrl.remove(url);
    }

    public static int getVisitedUrlNum()
    {
        return visitedUrl.size();
    }

    //待访问的url集合，即unVisited表
    private static Queue unVisitedUrl= new Queue();

    //获得UnVisited队列
    public static Queue getUnVisitedUrl() {
        return unVisitedUrl;
    }

    //删除unVisitedUrl中的元素
    public static Object deUnVisitedUrl()
    {
        return unVisitedUrl.deQueue();
    }

    //添加unVisitedUrl中的元素，并保证不能重复
    public static void addUnVisitedUrl(String url)
    {
        if (url!=null && url.trim().equals("") && !visitedUrl.contains(url) && !unVisitedUrl.isContain(url))
        {
            unVisitedUrl.enQueue(url);
        }
    }

    //判断未访问的URL队列是否为空
    public static boolean isUnVisitedUrlEmpty()
    {
        return unVisitedUrl.isQueueEmpty();
    }

}
