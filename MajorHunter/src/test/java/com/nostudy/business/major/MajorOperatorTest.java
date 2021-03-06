package com.nostudy.business.major;

import com.google.gson.Gson;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Lance on 7/15/16.
 */
public class MajorOperatorTest {

    private String rowResult = "jQuery1830032572212268669354_1468135281347({ \"totalRecord\": {\"num\": \"547\"}, \"school\":  [    {   \"specialname\": \"哲学\",   \"code\": \"010101\",   \"specialurl\": \"/schoolhtm/specialty/10032/specialtyDetail50719.htm\",   \"clicks\": \"12115\",   \"monthclicks\": \"12115\",   \"weekclicks\": \"12115\",   \"zycengci\": \"本科\",   \"zytype\": \"哲学类\",   \"bnum\": \"90\",   \"znum\": \"2\",   \"zyid\": \"50719\",   \"ranking\": \"204\",   \"rankingType\": \"1\"  },    {   \"specialname\": \"逻辑学\",   \"code\": \"010102\",   \"specialurl\": \"/schoolhtm/specialty/10032/specialtyDetail50720.htm\",   \"clicks\": \"10257\",   \"monthclicks\": \"10257\",   \"weekclicks\": \"10257\",   \"zycengci\": \"本科\",   \"zytype\": \"哲学类\",   \"bnum\": \"4\",   \"znum\": \"0\",   \"zyid\": \"50720\",   \"ranking\": \"306\",   \"rankingType\": \"2\"  },    {   \"specialname\": \"宗教学\",   \"code\": \"010103\",   \"specialurl\": \"/schoolhtm/specialty/10032/specialtyDetail50721.htm\",   \"clicks\": \"6369\",   \"monthclicks\": \"6369\",   \"weekclicks\": \"6369\",   \"zycengci\": \"本科\",   \"zytype\": \"哲学类\",   \"bnum\": \"6\",   \"znum\": \"0\",   \"zyid\": \"50721\",   \"ranking\": \"462\",   \"rankingType\": \"4\"  },    {   \"specialname\": \"伦理学\",   \"code\": \"010104\",   \"specialurl\": \"/schoolhtm/specialty/10032/specialtyDetail50722.htm\",   \"clicks\": \"7805\",   \"monthclicks\": \"7805\",   \"weekclicks\": \"7805\",   \"zycengci\": \"本科\",   \"zytype\": \"哲学类\",   \"bnum\": \"2\",   \"znum\": \"0\",   \"zyid\": \"50722\",   \"ranking\": \"412\",   \"rankingType\": \"3\"  },    {   \"specialname\": \"经济学\",   \"code\": \"020101\",   \"specialurl\": \"/schoolhtm/specialty/10032/specialtyDetail50723.htm\",   \"clicks\": \"73480\",   \"monthclicks\": \"73466\",   \"weekclicks\": \"73466\",   \"zycengci\": \"本科\",   \"zytype\": \"经济学类\",   \"bnum\": \"552\",   \"znum\": \"36\",   \"zyid\": \"50723\",   \"ranking\": \"15\",   \"rankingType\": \"4\"  },    {   \"specialname\": \"国际经济与贸易\",   \"code\": \"020102\",   \"specialurl\": \"/schoolhtm/specialty/10032/specialtyDetail50724.htm\",   \"clicks\": \"52343\",   \"monthclicks\": \"52342\",   \"weekclicks\": \"52342\",   \"zycengci\": \"本科\",   \"zytype\": \"经济学类\",   \"bnum\": \"784\",   \"znum\": \"226\",   \"zyid\": \"50724\",   \"ranking\": \"21\",   \"rankingType\": \"5\"  },    {   \"specialname\": \"财政学\",   \"code\": \"020103\",   \"specialurl\": \"/schoolhtm/specialty/10032/specialtyDetail50725.htm\",   \"clicks\": \"30765\",   \"monthclicks\": \"30764\",   \"weekclicks\": \"30764\",   \"zycengci\": \"本科\",   \"zytype\": \"经济学类\",   \"bnum\": \"116\",   \"znum\": \"1\",   \"zyid\": \"50725\",   \"ranking\": \"55\",   \"rankingType\": \"6\"  },    {   \"specialname\": \"金融学\",   \"code\": \"020104\",   \"specialurl\": \"/schoolhtm/specialty/10032/specialtyDetail50726.htm\",   \"clicks\": \"93985\",   \"monthclicks\": \"93978\",   \"weekclicks\": \"93978\",   \"zycengci\": \"本科\",   \"zytype\": \"经济学类\",   \"bnum\": \"489\",   \"znum\": \"7\",   \"zyid\": \"50726\",   \"ranking\": \"13\",   \"rankingType\": \"3\"  },    {   \"specialname\": \"国民经济管理\",   \"code\": \"020105\",   \"specialurl\": \"/schoolhtm/specialty/10032/specialtyDetail50727.htm\",   \"clicks\": \"11451\",   \"monthclicks\": \"11444\",   \"weekclicks\": \"11444\",   \"zycengci\": \"本科\",   \"zytype\": \"经济学类\",   \"bnum\": \"10\",   \"znum\": \"0\",   \"zyid\": \"50727\",   \"ranking\": \"218\",   \"rankingType\": \"10\"  },    {   \"specialname\": \"贸易经济\",   \"code\": \"020106\",   \"specialurl\": \"/schoolhtm/specialty/10032/specialtyDetail50728.htm\",   \"clicks\": \"8953\",   \"monthclicks\": \"8953\",   \"weekclicks\": \"8953\",   \"zycengci\": \"本科\",   \"zytype\": \"经济学类\",   \"bnum\": \"39\",   \"znum\": \"4\",   \"zyid\": \"50728\",   \"ranking\": \"245\",   \"rankingType\": \"14\"  } ]});";

    private String exceptResult="{\"totalRecord\":{\"num\":547},\"school\":[{\"specialname\":\"哲学\",\"code\":\"010101\",\"specialurl\":\"/schoolhtm/specialty/10032/specialtyDetail50719.htm\",\"clicks\":\"12115\",\"monthclicks\":\"12115\",\"weekclicks\":\"12115\",\"zycengci\":\"本科\",\"zytype\":\"哲学类\",\"bnum\":\"90\",\"znum\":\"2\",\"zyid\":\"50719\",\"ranking\":\"204\",\"rankingType\":1},{\"specialname\":\"逻辑学\",\"code\":\"010102\",\"specialurl\":\"/schoolhtm/specialty/10032/specialtyDetail50720.htm\",\"clicks\":\"10257\",\"monthclicks\":\"10257\",\"weekclicks\":\"10257\",\"zycengci\":\"本科\",\"zytype\":\"哲学类\",\"bnum\":\"4\",\"znum\":\"0\",\"zyid\":\"50720\",\"ranking\":\"306\",\"rankingType\":2},{\"specialname\":\"宗教学\",\"code\":\"010103\",\"specialurl\":\"/schoolhtm/specialty/10032/specialtyDetail50721.htm\",\"clicks\":\"6369\",\"monthclicks\":\"6369\",\"weekclicks\":\"6369\",\"zycengci\":\"本科\",\"zytype\":\"哲学类\",\"bnum\":\"6\",\"znum\":\"0\",\"zyid\":\"50721\",\"ranking\":\"462\",\"rankingType\":4},{\"specialname\":\"伦理学\",\"code\":\"010104\",\"specialurl\":\"/schoolhtm/specialty/10032/specialtyDetail50722.htm\",\"clicks\":\"7805\",\"monthclicks\":\"7805\",\"weekclicks\":\"7805\",\"zycengci\":\"本科\",\"zytype\":\"哲学类\",\"bnum\":\"2\",\"znum\":\"0\",\"zyid\":\"50722\",\"ranking\":\"412\",\"rankingType\":3},{\"specialname\":\"经济学\",\"code\":\"020101\",\"specialurl\":\"/schoolhtm/specialty/10032/specialtyDetail50723.htm\",\"clicks\":\"73480\",\"monthclicks\":\"73466\",\"weekclicks\":\"73466\",\"zycengci\":\"本科\",\"zytype\":\"经济学类\",\"bnum\":\"552\",\"znum\":\"36\",\"zyid\":\"50723\",\"ranking\":\"15\",\"rankingType\":4},{\"specialname\":\"国际经济与贸易\",\"code\":\"020102\",\"specialurl\":\"/schoolhtm/specialty/10032/specialtyDetail50724.htm\",\"clicks\":\"52343\",\"monthclicks\":\"52342\",\"weekclicks\":\"52342\",\"zycengci\":\"本科\",\"zytype\":\"经济学类\",\"bnum\":\"784\",\"znum\":\"226\",\"zyid\":\"50724\",\"ranking\":\"21\",\"rankingType\":5},{\"specialname\":\"财政学\",\"code\":\"020103\",\"specialurl\":\"/schoolhtm/specialty/10032/specialtyDetail50725.htm\",\"clicks\":\"30765\",\"monthclicks\":\"30764\",\"weekclicks\":\"30764\",\"zycengci\":\"本科\",\"zytype\":\"经济学类\",\"bnum\":\"116\",\"znum\":\"1\",\"zyid\":\"50725\",\"ranking\":\"55\",\"rankingType\":6},{\"specialname\":\"金融学\",\"code\":\"020104\",\"specialurl\":\"/schoolhtm/specialty/10032/specialtyDetail50726.htm\",\"clicks\":\"93985\",\"monthclicks\":\"93978\",\"weekclicks\":\"93978\",\"zycengci\":\"本科\",\"zytype\":\"经济学类\",\"bnum\":\"489\",\"znum\":\"7\",\"zyid\":\"50726\",\"ranking\":\"13\",\"rankingType\":3},{\"specialname\":\"国民经济管理\",\"code\":\"020105\",\"specialurl\":\"/schoolhtm/specialty/10032/specialtyDetail50727.htm\",\"clicks\":\"11451\",\"monthclicks\":\"11444\",\"weekclicks\":\"11444\",\"zycengci\":\"本科\",\"zytype\":\"经济学类\",\"bnum\":\"10\",\"znum\":\"0\",\"zyid\":\"50727\",\"ranking\":\"218\",\"rankingType\":10},{\"specialname\":\"贸易经济\",\"code\":\"020106\",\"specialurl\":\"/schoolhtm/specialty/10032/specialtyDetail50728.htm\",\"clicks\":\"8953\",\"monthclicks\":\"8953\",\"weekclicks\":\"8953\",\"zycengci\":\"本科\",\"zytype\":\"经济学类\",\"bnum\":\"39\",\"znum\":\"4\",\"zyid\":\"50728\",\"ranking\":\"245\",\"rankingType\":14}]}";

    MajorOperator majorOperator;

    @Before
    public void setup(){
        majorOperator=new MajorOperator();
    }

    @Test
    public void testParseJSON(){
        Gson gson =new Gson();
        String functionResult=gson.toJson(majorOperator.parseJSON(rowResult));
        Assert.assertEquals(exceptResult,functionResult);
    }


    @Test
    public void testParseMajorRespVO(){
        MajorRespVO majorOperator;
    }


    @After
    public void tearDown(){
        majorOperator=null;
    }
}
