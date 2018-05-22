package com.github.xiaour.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.dom4j.*;

import java.util.List;

/**
 * XML转成JSON
 *
 * @ClassName Xml2JsonUtil
 * @author xiaour@github.com
 * @Date 2017年6月13日 下午6:30:00
 * @version V2.0.0
 */
public class Xml2JsonUtil {

	    /**
	     * xml转json
	     * @param xmlStr
	     * @return
	     * @throws DocumentException
	     */
	    public static JsonObject xml2Json(String xmlStr) throws DocumentException{
	        Document doc= DocumentHelper.parseText(xmlStr);
	        JsonObject json=new JsonObject();
	        dom4j2Json(doc.getRootElement(), json);
	        return json;
	    }

	    /**
	     * xml转json
	     * @param element
	     * @param json
	     */
	    private static void dom4j2Json(Element element,JsonObject json){
	        //如果是属性
	        for(Object o:element.attributes()){
	            Attribute attr=(Attribute)o;
	            if(!isEmpty(attr.getValue())){
	                json.addProperty("@"+attr.getName(), attr.getValue());
	            }
	        }
	        List<Element> chdEl=element.elements();
	        if(chdEl.isEmpty()&&!isEmpty(element.getText())){//如果没有子元素,只有一个值
	            json.addProperty(element.getName(), element.getText());
	        }

	        for(Element e:chdEl){//有子元素
	            if(!e.elements().isEmpty()){//子元素也有子元素
                    JsonObject chdjson=new JsonObject();
	                dom4j2Json(e,chdjson);
	                Object o=json.get(e.getName());
	                if(o!=null){
	                    JsonArray jsona=null;
	                    if(o instanceof JsonObject){//如果此元素已存在,则转为jsonArray
                            JsonObject jsono=(JsonObject)o;
	                        json.remove(e.getName());
	                        jsona=new JsonArray();
	                        jsona.add(jsono);
	                        jsona.add(chdjson);
	                    }
	                    if(o instanceof JsonArray){
	                        jsona=(JsonArray)o;
	                        jsona.add(chdjson);
	                    }
	                    json.add(e.getName(), jsona);
	                }else{
	                    if(!chdjson.isJsonNull()){
	                        json.add(e.getName(), chdjson);
	                    }
	                }


	            }else{//子元素没有子元素
	                for(Object o:element.attributes()){
	                    Attribute attr=(Attribute)o;
	                    if(!isEmpty(attr.getValue())){
	                        json.addProperty("@"+attr.getName(), attr.getValue());
	                    }
	                }
	                if(!e.getText().isEmpty()){
	                    json.addProperty(e.getName(), e.getText());
	                }
	            }
	        }
	    }

	    private static boolean isEmpty(String str) {

	        if (str == null || str.trim().isEmpty() || "null".equals(str)) {
	            return true;
	        }
	        return false;
	    }

}
