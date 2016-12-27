package com.nevertiree.business;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lance Wang on 2016/12/27.
 */
public class BookFilter {

    public static BookVO getBookInfo(String webContent){

        BookVO book = new BookVO();

        // get book name
        Pattern namePattern = Pattern.compile("<span property=\"v:itemreviewed\">.*?</span>");
        Matcher nameMatcher = namePattern.matcher(webContent);

        if (nameMatcher .find()) {
            String name = nameMatcher.group().replace("<span property=\"v:itemreviewed\">","").replace("</span>","");
            book.setName(name);
        }

        webContent = webContent.replace(".*?<span property=\"v:itemreviewed\">.*?</span>","");


        // get book score
        Pattern scorePattern = Pattern.compile("<strong class=\"ll rating_num \" property=\"v:average\">.*?</strong>");
        Matcher scoreMatcher = scorePattern.matcher(webContent);

        if (scoreMatcher .find()) {
            String score = scoreMatcher.group().replace("<strong class=\"ll rating_num \" property=\"v:average\">","").replace("</strong>","").replaceAll("\\s","");
            book.setScore(Double.parseDouble(score));
        }

        webContent = webContent.replace(".*?<strong class=\"ll rating_num \" property=\"v:average\">","");

        // get reading number
        Pattern readerNumberPattern = Pattern.compile("<span property=\"v:votes\">.*?</span>");
        Matcher readerNumberMatcher = readerNumberPattern.matcher(webContent);

        if (readerNumberMatcher .find()) {
            String readerNumber = readerNumberMatcher.group().replace("<span property=\"v:votes\">","").replace("</span>","").replaceAll("\\s","");
            book.setReaderNum(Integer.parseInt(readerNumber));
         }

        webContent = webContent.replace(".*?<span property=\"v:votes\">","");

        // get rank 5 rate
        Pattern rank5RatePattern = Pattern.compile("<span class=\"stars5 starstop\" title=\"力荐\">    5星</span>            <div class=\"power\" style=\"width:[\\d]*px\"></div>            <span class=\"rating_per\">.*?</span>");
        Matcher rank5RateMatcher = rank5RatePattern.matcher(webContent);

        if (rank5RateMatcher.find()) {
            String rank5rate = rank5RateMatcher.group().replace("<span class=\"stars5 starstop\" title=\"力荐\">    5星</span>            <div class=\"power\" style=\"width:[\\d]*px\"></div>            <span class=\"rating_per\">","").replace("%[\\s]*</span>","").replaceAll("\\s","");
            book.setScoreRank5(Double.parseDouble(rank5rate));
        }
        webContent = webContent.replace(".*?<span class=\"stars5 starstop\" title=\"力荐\">    5星</span>            <div class=\"power\" style=\"width:[\\d]*px\"></div>            <span class=\"rating_per\">","");


        // get rank 4 rate
        Pattern rank4RatePattern = Pattern.compile("<span class=\"stars4 starstop\" title=\"推荐\">    4星</span>            <div class=\"power\" style=\"width:8px\"></div>            <span class=\"rating_per\">.*?</span>");
        Matcher rank4RateMatcher = rank4RatePattern.matcher(webContent);

        if (rank4RateMatcher .find()) {
            String rank4rate = rank4RateMatcher.group().replace("<span class=\"stars4 starstop\" title=\"推荐\">    4星</span>            <div class=\"power\" style=\"width:8px\"></div>            <span class=\"rating_per\">","").replace("%</span>","").replaceAll("\\s","");
            book.setScoreRank4(Double.parseDouble(rank4rate));
        }

        webContent = webContent.replace(".*?<span class=\"stars4 starstop\" title=\"推荐\">    4星</span>            <div class=\"power\" style=\"width:8px\"></div>            <span class=\"rating_per\">","");


        // get rank 3 rate
        Pattern rank3RatePattern = Pattern.compile("<span class=\"stars3 starstop\" title=\"还行\">    3星</span>            <div class=\"power\" style=\"width:0px\"></div>            <span class=\"rating_per\">.*?</span>");
        Matcher rank3RateMatcher = rank3RatePattern.matcher(webContent);

        if (rank3RateMatcher .find()) {
            String rank3rate = rank3RateMatcher.group().replace("<span class=\"stars3 starstop\" title=\"还行\">    3星</span>            <div class=\"power\" style=\"width:0px\"></div>            <span class=\"rating_per\">","").replace("%</span>","").replaceAll("\\s","");
            book.setScoreRank3(Double.parseDouble(rank3rate));
        }

        webContent = webContent.replace(".*?<span class=\"stars3 starstop\" title=\"还行\">    3星</span>            <div class=\"power\" style=\"width:0px\"></div>            <span class=\"rating_per\">","");


        // get rank 2 rate
        Pattern rank2RatePattern = Pattern.compile("<span class=\"stars2 starstop\" title=\"较差\">    2星</span>            <div class=\"power\" style=\"width:0px\"></div>            <span class=\"rating_per\">.*?</span>");
        Matcher rank2RateMatcher = rank2RatePattern.matcher(webContent);

        if (rank2RateMatcher .find()) {
            String rank2rate = rank2RateMatcher.group().replace("<span class=\"stars2 starstop\" title=\"较差\">    2星</span>            <div class=\"power\" style=\"width:0px\"></div>            <span class=\"rating_per\">","").replace("%</span>","").replaceAll("\\s","");
            book.setScoreRank2(Double.parseDouble(rank2rate));
        }

        webContent = webContent.replace(".*?<span class=\"stars2 starstop\" title=\"较差\">    2星</span>            <div class=\"power\" style=\"width:0px\"></div>            <span class=\"rating_per\">","");


        // get rank 1 rate
        Pattern rank1RatePattern = Pattern.compile("<span class=\"stars1 starstop\" title=\"很差\">    1星</span>            <div class=\"power\" style=\"width:0px\"></div>            <span class=\"rating_per\">.*?</span>");
        Matcher rank1RateMatcher = rank1RatePattern.matcher(webContent);

        if (rank1RateMatcher .find()) {
            String rank1rate = rank1RateMatcher.group().replace("<span class=\"stars1 starstop\" title=\"很差\">    1星</span>            <div class=\"power\" style=\"width:0px\"></div>            <span class=\"rating_per\">","").replace("%</span>","").replaceAll("\\s","");
            book.setScoreRank1(Double.parseDouble(rank1rate));
        }

        webContent = webContent.replace(".*?<span class=\"stars1 starstop\" title=\"很差\">    1星</span>            <div class=\"power\" style=\"width:0px\"></div>            <span class=\"rating_per\">","");


        // get relative movie

        Pattern relativePattern = Pattern.compile("https:\\/\\/book.douban.com\\/subject\\/[\\d]*\\/");
        Matcher relativeMatcher = relativePattern.matcher(webContent);

        if (relativeMatcher .find()) {
            System.out.println(relativeMatcher.group());
        }

        return book;
    }
}
