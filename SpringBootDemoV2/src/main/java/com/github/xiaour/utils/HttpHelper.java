package com.github.xiaour.utils;



import com.github.xiaour.constants.Api;
import com.github.xiaour.exception.OApiException;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;


public class HttpHelper {


    private static RestTemplate restTemplate = new RestTemplate();

    private static JsonObject jsonObject = null;

    static{
        jsonObject  = new JsonObject();
    }

    public static synchronized JsonObject instance(){
        if(jsonObject == null){
            jsonObject =  new JsonObject();
        }
        return jsonObject;
    }
	

	public static JsonObject httpGet(String url) throws OApiException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().
        		setSocketTimeout(10000).setConnectTimeout(10000).build();
        httpGet.setConfig(requestConfig);

        try {
            response = httpClient.execute(httpGet, new BasicHttpContext());

            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String resultStr = EntityUtils.toString(entity, "utf-8");
                return jsonObject.getAsJsonObject(resultStr);

            }
        } catch (IOException e) {
        	throw new OApiException(e);
        } finally {
            if (response != null) try {
                response.close();
            } catch (IOException e) {
            	throw new OApiException(e);
            }
        }

        return null;
    }
	
	
	public static String postXmlStr(String url,String xmlStr) throws OApiException {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().
        		setSocketTimeout(10000).setConnectTimeout(10000).build();
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "application/xml");
        try {
        	StringEntity requestEntity = new StringEntity(xmlStr, "utf-8");
            httpPost.setEntity(requestEntity);
            
            response = httpClient.execute(httpPost, new BasicHttpContext());

            if (response.getStatusLine().getStatusCode() != 200) {

                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String resultStr = EntityUtils.toString(entity, "utf-8");
                return resultStr;
            }
        } catch (IOException e) {
        	throw new OApiException(e);
        } finally {
            if (response != null) try {
                response.close();
            } catch (IOException e) {
            	throw new OApiException(e);
            }
        }

        return null;
    }
	
	
	public static JsonObject httpPost(String url, Object data) throws OApiException {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().
        		setSocketTimeout(10000).setConnectTimeout(10000).build();
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "application/json");
        try {
        	String dataStr=JsonUtil.getJsonString(data);
        	StringEntity requestEntity = new StringEntity(dataStr, "utf-8");
            httpPost.setEntity(requestEntity);
            response = httpClient.execute(httpPost, new BasicHttpContext());

            if (response.getStatusLine().getStatusCode() != 200) {

                return null;
            }
            
            HttpEntity entity = response.getEntity();
            
            if (entity != null) {
                String resultStr = EntityUtils.toString(entity, "utf-8");
                return jsonObject.getAsJsonObject(resultStr);
            }
        } catch (IOException e) {
        	throw new OApiException(e);
        } finally {
            if (response != null) try {
                response.close();
            } catch (IOException e) {
            	throw new OApiException(e);
            }
        }

        return null;
    }



    /**
     * Get方法
     *
     * @param url:地址
     * @param returnClassName:返回对象类型,如:String.class
     * @param parameters:parameter参数
     * @return
     */
    public static <T> T getByRest(String url, Class<T> returnClassName, Map<String, Object> parameters){
        if (parameters == null) {
            return restTemplate.getForObject(url, returnClassName);
        }
        return restTemplate.getForObject(url, returnClassName, parameters);
    }

    /**
     * post请求,包含了路径,返回类型,Header,Parameter
     *
     * @param url:地址
     * @param returnClassName:返回对象类型,如:String.class
     * @param inputParameter
     * @param jsonBody
     * @return
     */
    public static <T> T postByRest(String url,Class<T> returnClassName,Map<String,Object> inputParameter,String jsonBody){

        org.springframework.http.HttpEntity<String> formEntity = new org.springframework.http.HttpEntity<>(jsonBody);
        if (inputParameter==null) {
            return restTemplate.postForObject(url, formEntity, returnClassName);
        }
        return restTemplate.postForObject(url, formEntity, returnClassName, inputParameter);
    }

	
/*
	  public static String postWithKey(String appid,String keyPath, String url, WxRefundDto dto) throws OApiException {
	      SSLContext sslContext = Pksc12KeyStore.initSSLContext(appid,dto.getMch_id(),keyPath);
	      HttpRequest request = HttpRequest.post(url).withConnectionProvider(new SSLSocketHttpConnectionProvider(sslContext));
	      request.bodyText(dto.toXml());
	      HttpResponse response = request.send();
	      return  response.bodyText();
	}*/


    public static void main(String[] args) {

      /*  String jsonStr="{\"component_appid\":\"wxa797588149020de4\",\"component_verify_ticket\":\"ticket@@@6PEMgS7QZ2xzxJn9bdrOd6DB2xzm5I3liChyE8l4MdkruB10OzJl0FNsirqBXWoiB631xmw2fCHf84wCOSt9ZA\",\"component_appsecret\":\"a38dd974a9e6490dfbf5b21cd38f9996\"}\n";
        String json= HttpHelper.postByRest(Api.component_token,String.class,null,jsonStr);
        Map<String,Object> jsonMap= JsonUtil.json2Obj(json,Map.class);




        System.out.println(jsonMap);*/


        try {
            JsonObject jo= HttpHelper.httpPost("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=7_LptTbnbGrlk8OrF3fSreUPozwvDXm_lE1LB-lqVXxD5VYW2XLcRCQ3G4FTwhe1LR670PkaItKHZm7TY4Ms-ECf8oDslqhyE3rUMo3GrqKLOFs8owbj9SSkF3Y6kYqd0GiYtzbgNLopPeP8AiDNYdABAXHS",null);
            System.out.println(jo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	  
	
}
