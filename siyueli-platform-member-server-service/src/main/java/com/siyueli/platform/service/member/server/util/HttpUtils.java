package com.siyueli.platform.service.member.server.util;

import com.siyueli.platform.service.member.server.constant.MemberServiceConstant;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * 用于http请求的工具类
 */
public final class HttpUtils {

	// 禁止实例化和继承
	private HttpUtils() {}
 
	private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	public static String httpPostRequest(String url, String params) {
		return httpPostRequest(url, params, null);
	}

	public static String httpPostRequest(String url, String params, Map<String, String> headers) {
		String result = null;
		try {
			result = httpRequest(false, "POST", params, url, 0, 0, headers);
			return result;
		} finally {
			logger.info(LogInfoUtil.getRequestLogInfo(url, params, result));
		}
	}

	public static String httpsPostRequest(String url, String params) {
		String result = null;
		try {
			result = httpRequest(true, "POST", params, url, 0, 0);
			return result;
		} finally {
			logger.info(LogInfoUtil.getRequestLogInfo(url, params, result));
		}
	}

	public static String httpPostNotJsonRequest(String url, String params) {
		String result = null;
		try {
			result = httpRequest(false, "POSTNOTJSON", params, url, 0, 0);
			return result;
		} finally {
			logger.info(LogInfoUtil.getRequestLogInfo(url, params, result));
		}
	}

	public static String httpsGetRequest(String url) {
		String result = null;
		try {
			result = httpRequest(true, "GET", null, url, 0, 0);
			return result;
		} finally {
			logger.info(LogInfoUtil.getRequestLogInfo(url, null, result));
		}
	}

	private static String httpRequest(Boolean isHttps, String method,
									  String params, String strUrl, int socketTimeout, int connectTimeout) {

		return httpRequest(isHttps, method,
				params, strUrl, socketTimeout, connectTimeout, null);
	}

	private static void setHeaders(HttpRequestBase httpRequest, Map<String, String> headers) {
		if (headers != null && !headers.isEmpty()) {
			Set<Map.Entry<String, String>> set = headers.entrySet();
			for (Map.Entry<String, String> entry : set) {
				httpRequest.setHeader(entry.getKey(), entry.getValue());
			}
		}
	}
	
	private static String httpRequest(Boolean isHttps, String method,
			String params, String strUrl, int socketTimeout, int connectTimeout, Map<String, String> headers) {
		
		Long startDate = new Date().getTime();
		
		try {
			if (socketTimeout == 0) {
				socketTimeout = MemberServiceConstant.CONNECT_SOKET_TIME_OUT_LONG;
			}
			if (connectTimeout == 0) {
				connectTimeout = MemberServiceConstant.CONNECT_TIME_OUT_LONG;
			}
			CloseableHttpClient client = getClient(isHttps);
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(socketTimeout)
					.setConnectTimeout(connectTimeout).build();
			CloseableHttpResponse response = null;
			if ("GET".equals(method)) {
				HttpGet get = null;
				get = new HttpGet(strUrl);
				
				get.setConfig(requestConfig);
				get.setHeader("Accept", "application/json");
                get.setHeader("Content-type","application/json;charset=utf-8");
				response = client.execute(get);
				//测试环境下，打印url
				if(MemberServiceConstant.VERIFICATION_CODE_DEBUG == 1){
					logger.info("提交getUrl:\n"+strUrl);
				}

				// 设置头部信息
				setHeaders(get, headers);
				
			 }else {
				HttpPost post = new HttpPost(strUrl);
				post.setConfig(requestConfig);
				HttpEntity postEntity = null;
				
				if("POSTNOTJSON".equals(method)) {//非Json格式的参数
					List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
					String[] paramArray = params.split("&");
					for(String param : paramArray){
						String[] temp = param.split("=");
						nvps.add(new BasicNameValuePair(temp[0], temp[1]));  
					}
			        postEntity = new UrlEncodedFormEntity(nvps, "utf-8");  
				}else{
					post.setHeader("Accept", "application/json");
					if("POSTNOCHARSET".equals(method)){
						post.setHeader("Content-type","application/json");
					}else{
							post.setHeader("Content-type","application/json;charset=utf-8");
					}
					postEntity = new StringEntity(params,ContentType.APPLICATION_JSON);
				}
				//测试环境下，打印url
				if(MemberServiceConstant.VERIFICATION_CODE_DEBUG == 1){
					byte[] b = new byte[(int)postEntity.getContentLength()];
					postEntity.getContent().read(b);
					logger.info("postUrl:\n"+strUrl+"\n" 
							+ new String(b,0,(int)postEntity.getContentLength()));
				}
				post.setEntity(postEntity);
				// 设置头部信息
				setHeaders(post, headers);
				response = client.execute(post);
			}
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
                    String result = EntityUtils.toString(entity, "UTF-8");
                    if(MemberServiceConstant.VERIFICATION_CODE_DEBUG == 1){
                    	logger.info("请求接口结果:\n"+result);
                    }
                 
                    return result;
				} else {
					
					
				}
			} finally {
				response.close();
				client.close();
			}
		} catch (Exception e) {

				logger.error(
						"Method:" + method + " isHttps: " + isHttps + " params: "
								+ params + " url: " + strUrl + " request failed", e);
		}
		return null;
	}
	
	private static CloseableHttpClient getClient(boolean isHttps){
		if (isHttps) {
			return Objects.requireNonNull(createSSLInsecureClient());
		}
		return HttpClients.createDefault();
	}
	
	private static CloseableHttpClient createSSLInsecureClient() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
					null, new TrustStrategy() {
						// 信任所有证书
						@Override
						public boolean isTrusted(X509Certificate[] chain,
								String authType) throws CertificateException {
							return true;
						}
					}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (Exception e) {
			logger.error("get ssl client failed", e);
		}
		return null;
	}
	
}
