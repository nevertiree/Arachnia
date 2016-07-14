package Spider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lance on 7/14/16.
 */
public class AnalysisContent {

    //analysis whether that page have valued infomation (targetContent)
    public boolean valuedContent(String wholeContent,String targetContent){

        Pattern pattern = Pattern.compile(targetContent);
        Matcher matcher=pattern.matcher(wholeContent);

        if (matcher.find()){
            return true;
        }
        else return false;
    }

    //intergrate the JSON phrase into a single line
    public String integrateContent(String wholeContent,String targetContent){

        Pattern pattern = Pattern.compile(targetContent);
        Matcher matcher=pattern.matcher(wholeContent);

        StringBuilder intergrateResult= new StringBuilder();

        while(matcher.find()){
            intergrateResult.append(matcher.group());
        }
        return intergrateResult.toString();
    }

    //convert json to the string or the DAO
}
