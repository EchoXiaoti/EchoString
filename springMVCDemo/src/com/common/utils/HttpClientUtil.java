package com.common.utils;


import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * HttpClientUtil工具类方法
 * @author xiaoti
 *
 */
public class HttpClientUtil {
	
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	//utf-8字符编码
	private static final String CHRASET_UTF_8 = "utf-8";
	//HTTP内容类型
	private static final String CONTENT_TYPE_TEXT_HTML = "text/html";
	//HTTP内容类型，相当于from表单的形式，提交数据
	private static final String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlcoded";
	//HTTP内容类型，相当于Json格式的形式，提交数据
	private static final String CONTENT_TYPE_JSON_URL = "application/json;charset=utf-8";
	//连接管理器
	private static PoolingHttpClientConnectionManager pool;
	//请求配置
	private static RequestConfig requestConfig;
	
	static{
		try{
			logger.info("初始化HttpClientUtil~~~开始");
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
			//同时配置http和https
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
					.<ConnectionSocketFactory>create()
					.register("http", PlainConnectionSocketFactory.getSocketFactory())
					.register("https", sslsf).build();
			//初始化连接管理器
			pool = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			//将最大连接数增加到200，实际项目最好从配置文件中读取这个值
			pool.setMaxTotal(200);
			//设置最大路由
			pool.setDefaultMaxPerRoute(2);
			//根据默认超时限制初始化requestConfig
			int socketTimeout = 10000;
			int connectTimeout = 10000;
			int connectionRequestTimeout = 10000;
			requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
			logger.info("初始化HttpClientUtil~~~~结束");
			
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}catch (KeyStoreException e) {
			e.printStackTrace();
		}catch (KeyManagementException e) {
			e.printStackTrace();
		}
		//设置请求超时时间
		requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).setConnectionRequestTimeout(50000).build();
	}
	
	public static CloseableHttpClient getHttpClient() {
		CloseableHttpClient httpClient = HttpClients.custom()
				//设置连接池管理
				.setConnectionManager(pool)
				//设置请求配置
				.setDefaultRequestConfig(requestConfig)
				//设置重试次数
				.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
				.build();
		return httpClient;
	}
	
	/**
	 * 发送Post请求
	 * @param httpPost
	 * @return
	 */
	private static String sendHttpPost(HttpPost httpPost){
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		//响应内容
		String responseContent = null;
		try {
			//创建默认的httpClient实例
			httpClient = getHttpClient();
			//配置请求信息
			httpPost.setConfig(requestConfig);
			//执行请求
			response = httpClient.execute(httpPost);
			//得到响应实体
			HttpEntity entity = response.getEntity();
			//可以获得响应头
			Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);
			for (Header header : headers) {
				logger.info(header.getName());
			}
			//得到响应类型
			logger.info(ContentType.getOrDefault(response.getEntity()).getMimeType());
			//判断响应状态
			if(response.getStatusLine().getStatusCode() >= 300){
				throw new Exception("HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
			}
			if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()){
				responseContent = EntityUtils.toString(entity, CHRASET_UTF_8);
				EntityUtils.consume(entity);
				logger.info("httpSendMsg() executed, result response :" + responseContent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				//释放资源
				if(response != null){
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}
	
	private static String sendHttpGet(HttpGet httpGet){
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		
		//响应内容
		String responseContent = null;
		try {
			//创建默认的httpClient实例
			httpClient = getHttpClient();
			//配置请求信息
			httpGet.setConfig(requestConfig);
			//执行请求
			response = httpClient.execute(httpGet);
			//得到响应实体
			HttpEntity entity = response.getEntity();
			
			//判断响应状态
			if(response.getStatusLine().getStatusCode() >= 300){
				throw new Exception("HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
			}
			if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()){
				responseContent = EntityUtils.toString(entity, CHRASET_UTF_8);
				EntityUtils.consume(entity);
				logger.info("httpSendMsg() executed, result response :" + responseContent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				//释放资源
				if(response != null){
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}
	
	/**
	 * 发送post请求
	 * @param httpUrl	请求地址
	 * @return
	 */
	public static String sendHttpPost(String httpUrl){
		HttpPost httpPost = new HttpPost(httpUrl);
		return sendHttpPost(httpPost);
	}
	
	/**
	 * 发送get请求
	 * @param httpUrl	请求地址
	 * @return
	 */
	public static String sendHttpGet(String httpUrl){
		HttpGet httpGet = new HttpGet(httpUrl);
		return sendHttpGet(httpGet);
	}
	
	/**
	 * 发送post请求，带文件
	 * @param httpUrl
	 * @param maps
	 * @param fileLists
	 * @return
	 */
	public static String sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists){
		HttpPost httpPost = new HttpPost(httpUrl);	//创建post请求
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		if(maps != null){
			for (String key : maps.keySet()) {
				meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
			}
		}
		if(fileLists != null){
			for (File file : fileLists) {
				FileBody fileBody = new FileBody(file);
				meBuilder.addPart("files", fileBody);
			}
		}
		HttpEntity reqEntity = meBuilder.build();
		httpPost.setEntity(reqEntity);
		return sendHttpPost(httpPost);
	}
	
	/**
	 * 发送post请求，参数为Json
	 * @param httpUrl
	 * @param paramsJson
	 * @return
	 */
	public static String sendHttpPost(String httpUrl, String paramsJson){
		HttpPost httpPost = new HttpPost(httpUrl);
		try {
			//设置参数
			if(paramsJson != null && paramsJson.trim().length() > 0){
				StringEntity stringEntity = new StringEntity(paramsJson, "UTF-8");
				stringEntity.setContentType(CONTENT_TYPE_JSON_URL);
				httpPost.setEntity(stringEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}
	
	/**
	 * 发送post请求，map参数
	 * @param httpUrl
	 * @param maps
	 * @return
	 */
	public static String sendHttpPost(String httpUrl, Map<String, Object> maps)	{
		String parem = convertStringParamter(maps);
		return sendHttpPost(httpUrl, parem);
	}
	
	/**
	 * 发送post请求，参数格式为xml
	 * @param httpUrl
	 * @param paramsXml
	 * @return
	 */
	public static String sendHttpPostXml(String httpUrl, String paramsXml){
		HttpPost httpPost = new HttpPost(httpUrl);
		try{
			if(paramsXml != null && paramsXml.trim().length() > 0){
				StringEntity stringEntity = new StringEntity(paramsXml, "UTF-8");
				stringEntity.setContentType(CONTENT_TYPE_TEXT_HTML);
				httpPost.setEntity(stringEntity);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}
	
	/**
	 * 将map集合的键值对转化成：key1 = value & key2 = value2 的形式
	 * @param parameterMap
	 * @return
	 */
	public static String convertStringParamter(Map parameterMap){
		StringBuffer parameterBuffer = new StringBuffer();
		if(parameterMap != null){
			Iterator iterator = parameterMap.keySet().iterator();
			String key = null;
			String value = null;
			while(iterator.hasNext()){
				key = (String)iterator.next();
				if(parameterMap.get(key) != null){
					value = String.valueOf(parameterMap.get(key));
				}else{
					value = "";
				}
				parameterBuffer.append(key).append("=").append(value);
				if(iterator.hasNext()){
					parameterBuffer.append("&");
				}
			}
		}
		return parameterBuffer.toString();
	}
	
	public static void main(String args[]){
		String httpUrl = "http://www.baidu.com";
		String httpRespond = sendHttpGet(httpUrl);
		System.out.println(httpRespond);
	}
}
