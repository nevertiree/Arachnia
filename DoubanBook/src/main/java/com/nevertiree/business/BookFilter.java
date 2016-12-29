package com.nevertiree.business;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookFilter {

    public static BookVO getBookInfo(String webContent){

        BookVO book = new BookVO();

        book.setName(getBookName(webContent));
        book.setScore(Double.parseDouble(getBookScore(webContent)));
        book.setReaderNum(Integer.parseInt(getVoteNumber(webContent)));
        book.setScoreRank1(Double.parseDouble(getRank1Rate(webContent)));
        book.setScoreRank2(Double.parseDouble(getRank2Rate(webContent)));
        book.setScoreRank3(Double.parseDouble(getRank3Rate(webContent)));
        book.setScoreRank4(Double.parseDouble(getRank4Rate(webContent)));
        book.setScoreRank5(Double.parseDouble(getRank5Rate(webContent)));
        return book;
    }

    public static String getBookName(String webContent){

        Pattern namePattern = Pattern.compile("<span property=\"v:itemreviewed\">.*?</span>");
        Matcher nameMatcher = namePattern.matcher(webContent);

        if (nameMatcher .find()) {
            String name = nameMatcher.group().replace("<span property=\"v:itemreviewed\">","").replace("</span>","");
            System.out.println(name);
            return name;
        }
        System.out.println("Do not find yet !");
        return null;
    }

    public static String getBookScore(String webContent){
        Pattern scorePattern = Pattern.compile("<strong class=\"ll rating_num \" property=\"v:average\">.*?</strong>");
        Matcher scoreMatcher = scorePattern.matcher(webContent);

        if (scoreMatcher .find()) {
            String score = scoreMatcher.group().replace("<strong class=\"ll rating_num \" property=\"v:average\">","").replace("</strong>","").replaceAll("\\s","");
            System.out.println(score);
            return score;
        }
        System.out.println("Do not find yet !");
        return null;
    }

    public static String getVoteNumber(String webContent){

        Pattern voteNumberPattern = Pattern.compile("<span property=\"v:votes\">.*?</span>");
        Matcher voteNumberMatcher = voteNumberPattern.matcher(webContent);

        if (voteNumberMatcher .find()) {
            String voteNumber = voteNumberMatcher.group().replace("<span property=\"v:votes\">","").replace("</span>","").replaceAll("\\s","");
            System.out.println(voteNumber);
            return voteNumber;
        }
        System.out.println("Do not find yet !");
        return null;
    }

    public static String getRank5Rate(String webContent){
        Pattern rank5RatePattern = Pattern.compile("<span class=\"stars5 starstop\" title=\"力荐\">[\\s]*5星</span>[\\s]*<div class=\"power\" style=\"width:[\\d]*?px\"></div>[\\s]*<span class=\"rating_per\">.*?</span>");
        Matcher rank5RateMatcher = rank5RatePattern.matcher(webContent);

        if (rank5RateMatcher.find()) {
            String rank5rate = rank5RateMatcher.group().replaceAll("\\s","").replaceAll("<spanclass=\"stars5starstop\"title=\"力荐\">5星</span><divclass=\"power\"style=\"width:[\\d]*?px\"></div><spanclass=\"rating_per\">","").replaceAll("%</span>","");
            System.out.println(rank5rate);
            return rank5rate;
        }
        System.out.println("Do not find yet !");
        return null;
    }

    public static String getRank4Rate(String webContent){
        Pattern rank4RatePattern = Pattern.compile("<span class=\"stars4 starstop\" title=\"推荐\">[\\s]*4星</span>[\\s]*<div class=\"power\" style=\"width:[\\d]*?px\"></div>[\\s]*<span class=\"rating_per\">.*?</span>");
        Matcher rank4RateMatcher = rank4RatePattern.matcher(webContent);

        if (rank4RateMatcher .find()) {
            String rank4rate = rank4RateMatcher.group().replaceAll("\\s","").replaceAll("<spanclass=\"stars4starstop\"title=\"推荐\">4星</span><divclass=\"power\"style=\"width:[\\d]*?px\"></div><spanclass=\"rating_per\">","").replaceAll("%</span>","");
            System.out.println(rank4rate);
            return rank4rate;
        }
        System.out.println("Don not find yet !");
        return null;
    }

    public static String getRank3Rate(String webContent){
        // get rank 3 rate
        Pattern rank3RatePattern = Pattern.compile("<span class=\"stars3 starstop\" title=\"还行\">[\\s]*3星</span>[\\s]*<div class=\"power\" style=\"width:[\\d]*?px\"></div>[\\s]*<span class=\"rating_per\">.*?</span>");
        Matcher rank3RateMatcher = rank3RatePattern.matcher(webContent);

        if (rank3RateMatcher .find()) {
            String rank3rate = rank3RateMatcher.group().replaceAll("\\s","").replaceAll("<spanclass=\"stars3starstop\"title=\"还行\">3星</span><divclass=\"power\"style=\"width:[\\d]*?px\"></div><spanclass=\"rating_per\">","").replaceAll("%</span>","");
            System.out.println(rank3rate);
            return rank3rate;
        }
        System.out.println("Do not find yet !");
        return null;
    }

    public static String getRank2Rate(String webContent){
        // get rank 2 rate
        Pattern rank2RatePattern = Pattern.compile("<span class=\"stars2 starstop\" title=\"较差\">[\\s]*2星</span>[\\s]*<div class=\"power\" style=\"width:[\\d]*?px\"></div>[\\s]*<span class=\"rating_per\">.*?</span>");
        Matcher rank2RateMatcher = rank2RatePattern.matcher(webContent);

        if (rank2RateMatcher .find()) {
            String rank2rate = rank2RateMatcher.group().replaceAll("\\s","").replaceAll("<spanclass=\"stars2starstop\"title=\"较差\">2星</span><divclass=\"power\"style=\"width:[\\d]*?px\"></div><spanclass=\"rating_per\">","").replaceAll("%</span>","");
            System.out.println(rank2rate);
            return rank2rate;
        }
        System.out.println("Do not find yet !");
        return null;
    }

    public static String getRank1Rate(String webContent){
        // get rank 1 rate
        Pattern rank1RatePattern = Pattern.compile("<span class=\"stars1 starstop\" title=\"很差\">[\\s]*1星</span>[\\s]*<div class=\"power\" style=\"width:[\\d]*?px\"></div>[\\s]*<span class=\"rating_per\">.*?</span>");
        Matcher rank1RateMatcher = rank1RatePattern.matcher(webContent);

        if (rank1RateMatcher .find()) {
            String rank1rate = rank1RateMatcher.group().replaceAll("\\s","").replaceAll("<spanclass=\"stars1starstop\"title=\"很差\">1星</span><divclass=\"power\"style=\"width:[\\d]*?px\"></div><spanclass=\"rating_per\">","").replaceAll("%</span>","");
            System.out.println(rank1rate);
            return rank1rate;
        }
        System.out.println("Do not find yet !");
        return null;
    }

    public static Set<String> getRelativeBook(String webContent){

        Pattern relativePattern = Pattern.compile("(https://book.douban.com/subject/[\\d]+/)");
        Matcher relativeMatcher = relativePattern.matcher(webContent);

        Set<String> relativeWebSet = new HashSet<>();

        while (relativeMatcher.find()){
                String webLink = relativeMatcher.group();
                System.out.println(webLink);
                relativeWebSet.add(webLink);
        }

        return relativeWebSet;
    }
}