package com.github.xiaour.controller;

import com.github.xiaour.constants.Api;
import com.github.xiaour.exception.OApiException;
import com.github.xiaour.utils.HttpHelper;
import com.github.xiaour.utils.JsonUtil;
import com.github.xiaour.utils.WXBizMsgCrypt;
import com.github.xiaour.utils.Xml2JsonUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 授权事件接收
 * @Author: Xiaour
 * @Description:
 * @Date: 2018/3/12 下午5:27
 */
@RestController
public class AuthNotify {


    /**
     * 这里以后肯定要维护到配置文件或缓存的
     * @param requestBody
     * @return
     */

    private static String aesKey="4a5665bf9fbe0618b7154cc9d93342b6a7b2abcf4e8";

    private static String token="ois3rd";

    private static String appsecret="a38dd974a9e6490dfbf5b21cd38f9996";

    private static String component_access_token="";


    @RequestMapping("auth_notify")
    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String payNotify(@RequestBody String requestBody, @RequestParam("timestamp")String timestamp, @RequestParam("nonce")String nonce,
                            @RequestParam("msg_signature")String msgSignature){
        String appid=null;
        Map<String,String> data= new HashMap<>();
        data.put("timestamp",timestamp);
        data.put("nonce",nonce);
        data.put("msg_signature",msgSignature);
        data.put("requestBody",requestBody);

        System.out.println(data);

        try {
            JsonObject jsonData= Xml2JsonUtil.xml2Json(requestBody);

            appid=jsonData.get("AppId").getAsString();

            WXBizMsgCrypt pc = new WXBizMsgCrypt(token, aesKey,appid);

            String decode=pc.decryptMsg(msgSignature,timestamp,nonce,jsonData.get("Encrypt").getAsString());

            System.out.println(decode);

            JsonObject decodeJson=Xml2JsonUtil.xml2Json(decode);

            getComponentTtoken(appid,appsecret,decodeJson.get("ComponentVerifyTicket").getAsString());

            getPreAuthCode(appid);

        } catch (Exception e) {

            e.printStackTrace();

        }
        return "success";
    }

    private String getPreAuthCode(String appid) {
        String preAuthCode=null;

        Map<String,String> postData=new HashMap<>();

        postData.put("component_appid",appid);
        try {

            String jsonStr= HttpHelper.postByRest(Api.create_preauthcode+component_access_token,String.class,null, JsonUtil.getJsonString(postData));
            Map<String,Object> jsonMap= JsonUtil.json2Obj(jsonStr,Map.class);

            preAuthCode=jsonMap.get("pre_auth_code").toString();

            String guideUrl="https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid="+appid+"&pre_auth_code="+preAuthCode+"&redirect_uri=http://sp.chifaner.com/wx/callback/auth";

            System.out.println(guideUrl);

            return preAuthCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    private void getComponentTtoken(String appid, String appsecret, String componentVerifyTicket){

        Map<String,String> postData=new HashMap<>();

        postData.put("component_appid",appid);

        postData.put("component_appsecret",appsecret);

        postData.put("component_verify_ticket",componentVerifyTicket);

        try {

            String jsonStr= HttpHelper.postByRest(Api.component_token,String.class,null, JsonUtil.getJsonString(postData));
            Map<String,Object> jsonMap= JsonUtil.json2Obj(jsonStr,Map.class);
            component_access_token=jsonMap.get("component_access_token").toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String xml="<xml>" + "<AppId><![CDATA[wxa797588149020de4]]></AppId>\n" +
                "<Encrypt><![CDATA[fPth6l5monkFzktjF5beOBCMA9uLN53BB1Ll+A+1M/f+SqjwW7taxD1F26wkKAFC1vUU+oZoleCJ3RwEimX9hdD9LgSGtCuTmo9AmyImAXvUEOsGSarnjUb4x73rfj8uPKsTS+PSwdalSBozF3kGhjEZ+lEzvwmYTwLrOJq804MX0ZDIWAm9+K3sBuFlHIFsNSm/Mxt7VoLgXTH6pN7qKHxwu/Fj2wYkJLq33Uy8gw+KSDEOqZBzaVdJXV0gJOiG5qYsM6ahzaIsQP5Z4jUTCVmM0EwlWOzwEAbk/gk/upxMb9cXyNoXd3HFsBwXpndKEnCGZhA629iMGHiiyVgV7zlkY67M1pWjhqFhDll79pXCnSeH0BJIbUjAbPnxW3VK68Mnd/umeliG+cXgoI0jBywQS36xf8DYEJn+kHMXuPtTE3HcQM5jxKof1A3oYJ+j4rKuEI+X2D6NuXyJKbq+4w==]]></Encrypt>\n" + "</xml>";
        try {
            JsonObject jsonData= Xml2JsonUtil.xml2Json(xml);

            WXBizMsgCrypt pc = null;

            pc = new WXBizMsgCrypt(token, aesKey,"wxa797588149020de4");

            System.out.println(jsonData.get("Encrypt").getAsString());

            String decode=pc.decryptMsg("d5b4bcef832c27f6f2804d0055cec64864c82a54","1520922592","941622427",jsonData.get("Encrypt").getAsString());

            JsonObject decodeJson=Xml2JsonUtil.xml2Json(decode);

            System.out.println(decodeJson.get("ComponentVerifyTicket").getAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
