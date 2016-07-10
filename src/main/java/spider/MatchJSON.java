package spider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lance on 7/10/16.
 */
public class MatchJSON{

    public boolean isMatched(String subject,String regex){
        Pattern pattern = Pattern.compile(regex);//定义要挑出的字段
        Matcher matcher=pattern.matcher(subject);//把要大段目标文字进行匹配

        if (matcher.find()){
            return true;
        }else return false;
    }

    public String returnMatched(String subject,String regex){
        Pattern pattern = Pattern.compile(regex);//定义要挑出的字段
        Matcher matcher=pattern.matcher(subject);//把要大段目标文字进行匹配

        StringBuilder stringBuilder =new StringBuilder();

        if (matcher.find()){
            //stringBuilder.append();
        }

        return stringBuilder.toString();
    }
}
