package com.study.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpClientUtil {
	
	public static String doGet(String url){
		try {
			HttpClient client = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = client.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity(),"UTF-8");
                return strResult;
            }

		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return null;
	}
	public static String doPost(String url,Map<String ,String> map){
		try {
			HttpClient client = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
			HttpPost httpPost = new HttpPost(url);
			Header header=new BasicHeader("Accept-Encoding",null);
            httpPost.setHeader(header);
            if (null != map && map.size() >0){
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> entry : map.entrySet()) {
					params.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
				}
				HttpEntity entity = new UrlEncodedFormEntity(params,"UTF-8");
				httpPost.setEntity(entity);
			}
			HttpResponse response = client.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/**读取服务器返回过来的json字符串数据**/
				String strResult = EntityUtils.toString(response.getEntity(),"UTF-8");
				return strResult;
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return null;
	}
}
