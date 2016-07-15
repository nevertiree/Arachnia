package com.nostudy.spider;

import jdk.internal.dynalink.support.DefaultPrelinkFilter;
import net.htmlparser.jericho.nodoc.*;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.LinkStringFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lance on 7/9/16.
 */
public class HtmlParserTool
{
    // 获取一个网站上的链接，filter 用来过滤链接
    public static Set<String> extraLinks(String url, LinkStringFilter filter)
    {
        Set<String> links= new HashSet<>();

        try
        {
            Parser parser=new Parser();
            parser.setEncoding("gb2312");

            //过滤<frame>标签的filter，用来提取frame标签里的src属性
            NodeFilter frameFilter=new NodeFilter() {
                @Override
                public boolean accept(Node node) {
                    if (node.getText().startsWith("frame src="))
                    {
                        return true;
                    }
                    else
                        return false;
                }
            };

            //OrFilter来设置过滤<a>标签和<frame>标签
            OrFilter linkFilter = new OrFilter(new NodeClassFilter(LinkTag.class),frameFilter);

            //得到所有经过过滤的标签
            NodeList list = parser.extractAllNodesThatMatch(linkFilter);

            for (int i=0;i<list.size();i++)
            {
                Node tag=list.elementAt(i);
                if (tag instanceof LinkTag)//<a> 标签
                {
                    LinkTag link =(LinkTag) tag;
                    String linkUrl=link.getLink();//URL

                    /*if (filter.accept(linkUrl))
                        links.add(linkUrl);*/
                }
                else
                {
                    String frame= tag.getText();
                    int start =frame.indexOf("src=");
                    frame =frame.substring(start);
                    int end =frame.indexOf(" ");
                    if(end==-1)
                    {
                        end=frame.indexOf(">");
                    }
                    String frameUrl=frame.substring(5,end-1);
                    /*if (filter.accept(frameUrl))
                    {
                        links.add(frameUrl);
                    }*/
                }
            }
        }
        catch (ParserException e)
        {
            e.printStackTrace();
        }

        return links;
    }
}
