package com.tmwj.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;

/**
 * description:
 * 腾讯会议相关工具类
 * @author: Victor
 * @date 2021/1/10 10:18
 **/
@Slf4j
public class MeetingUtil {

    private static String base_url = "*";
    private static String APP_ID = "*";
    private static String SDKID = "*";
    private static String SECRET_KEY = "*";
    private static String SECRET_ID = "*";
    private static String HMAC_ALGORITHM = "HmacSHA256";
    private static char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static String TOKEN="*";

    public static String bytesToHex(byte[] bytes) {
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        for (byte b : bytes) {
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }
        return new String(buf);
    }

    /**
     * 生成签名，开发版本oracle jdk 1.8.0_221
     * @param requestBody 请求体，没有的设为空串
     * @return 签名，需要设置在请求头X-TC-Signature中
     * @throws NoSuchAlgorithmException e
     * @throws InvalidKeyException e
     */
    public static String sign(String method,String requestBody,String uri,String timestamp,String nonce) {
        String sig="";
        try{
            String Headers ="X-TC-Key=" + SECRET_ID + "&X-TC-Nonce=" + nonce + "&X-TC-Timestamp=" + timestamp;
            String tobeSig=method + "\n" +
                    Headers + "\n" +
                    uri + "\n" +
                    requestBody ;
            Mac mac = Mac.getInstance(HMAC_ALGORITHM);
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), mac.getAlgorithm());
            mac.init(secretKeySpec);
            byte[] hash = mac.doFinal(tobeSig.getBytes(StandardCharsets.UTF_8));
            String hexHash = bytesToHex(hash);
            return new String(Base64.getEncoder().encode(hexHash.getBytes(StandardCharsets.UTF_8)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return sig;
    }

    public static String requestPost(String req_body,String uri){
        String url = base_url+uri;
        System.out.println("the http post url ---" + url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        String timestamp=String.valueOf(System.currentTimeMillis()/1000);
        String nonce=String.valueOf(new Random().nextInt(Integer.MAX_VALUE));
        //先获取签名
        String sign = sign("POST",req_body,uri,timestamp, nonce);
        String jsonStr = "";
        try {
            httpPost.setHeader("X-TC-Key",SECRET_ID);
            httpPost.setHeader("AppId", APP_ID);
            httpPost.setHeader("SdkId", SDKID);//有就加,没有可以不加
            httpPost.setHeader("X-TC-Nonce",nonce);
            httpPost.setHeader("X-TC-Timestamp",timestamp);
            httpPost.setHeader("X-TC-Signature", sign);
            httpPost.setHeader("content-type", "application/json;charset=utf-8");
            httpPost.setEntity(new StringEntity(req_body,StandardCharsets.UTF_8));

            CloseableHttpResponse httpResponse = null;
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                jsonStr = EntityUtils.toString(httpEntity,"UTF-8");
            }
            System.out.println("返回信息"+jsonStr);
        } catch (IOException e) {
        }finally{
            httpPost.releaseConnection();
            try {
                httpClient.close();
            } catch (IOException e) {
            }
        }
        return jsonStr;
    }

    public static String requestGet(String req_body,String uri) {
        String url = base_url+uri;
        log.info("queryMeetingInfo the http get url ---" + url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        String timestamp=String.valueOf(System.currentTimeMillis()/1000);
        String nonce=String.valueOf(new Random().nextInt(Integer.MAX_VALUE));
        //获取签名
        String sign = sign("GET",req_body,uri,timestamp, nonce);
        String jsonStr = "";
        try {
            httpGet.setHeader("X-TC-Key",SECRET_ID);
            httpGet.setHeader("AppId", APP_ID);
            httpGet.setHeader("SdkId", SDKID);
            httpGet.setHeader("X-TC-Nonce",nonce);
            httpGet.setHeader("X-TC-Timestamp",timestamp);
            httpGet.setHeader("X-TC-Signature", sign);
            httpGet.setHeader("content-type", "application/json");

            CloseableHttpResponse httpResponse = null;
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                jsonStr = EntityUtils.toString(httpEntity,"UTF-8");
            }
            System.out.println("返回信息"+jsonStr);
        } catch (IOException e) {
        }finally{
            httpGet.releaseConnection();
            try {
                httpClient.close();
            } catch (IOException e) {
            }
        }
        return jsonStr;
    }

    public static String requestPut(String req_body,String uri){
        String url = base_url+uri;
        System.out.println("queryMeetingInfo the http PUT url ---" + url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPost = new HttpPut(url);
        String timestamp=String.valueOf(System.currentTimeMillis()/1000);
        String nonce=String.valueOf(new Random().nextInt(Integer.MAX_VALUE));
        //先获取签名
        String sign = sign("PUT",req_body,uri,timestamp, nonce);
        String jsonStr = "";
        try {
            httpPost.setHeader("X-TC-Key",SECRET_ID);
            httpPost.setHeader("AppId", APP_ID);
            httpPost.setHeader("SdkId", SDKID);//有就加,没有可以不加
            httpPost.setHeader("X-TC-Nonce",nonce);
            httpPost.setHeader("X-TC-Timestamp",timestamp);
            httpPost.setHeader("X-TC-Signature", sign);
            httpPost.setHeader("content-type", "application/json");
            httpPost.setEntity(new StringEntity(req_body,StandardCharsets.UTF_8));

            CloseableHttpResponse httpResponse = null;
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                jsonStr = EntityUtils.toString(httpEntity,"UTF-8");
            }
            System.out.println("返回信息"+jsonStr);
        } catch (IOException e) {
        }finally{
            httpPost.releaseConnection();
            try {
                httpClient.close();
            } catch (IOException e) {
            }
        }
        return jsonStr;
    }

    public static String requestDelete(String req_body,String uri){
        String url = base_url+uri;
        System.out.println("queryMeetingInfo the http get url ---" + url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);
        String timestamp=String.valueOf(System.currentTimeMillis()/1000);
        String nonce=String.valueOf(new Random().nextInt(Integer.MAX_VALUE));
        //先获取签名
        String sign = sign("PUT",req_body,uri,timestamp, nonce);
        String jsonStr = "";
        try {
            httpDelete.setHeader("X-TC-Key",SECRET_ID);
            httpDelete.setHeader("AppId", APP_ID);
            httpDelete.setHeader("SdkId", SDKID);//有就加,没有可以不加
            httpDelete.setHeader("X-TC-Nonce",nonce);
            httpDelete.setHeader("X-TC-Timestamp",timestamp);
            httpDelete.setHeader("X-TC-Signature", sign);
            httpDelete.setHeader("content-type", "application/json");

            CloseableHttpResponse httpResponse = null;
            httpResponse = httpClient.execute(httpDelete);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                jsonStr = EntityUtils.toString(httpEntity,"UTF-8");
            }
            System.out.println("返回信息"+jsonStr);
        } catch (IOException e) {
        }finally{
            httpDelete.releaseConnection();
            try {
                httpClient.close();
            } catch (IOException e) {
            }
        }
        return jsonStr;
    }

    /**
     * 对腾讯会议回调的请求头验签
     *
     * @author icy
     * @since 2021-02-09 13:05
     */
    public static boolean validateSign(HttpServletRequest request,String data)
    {
        String timestamp=request.getHeader("timestamp");
        String nonce=request.getHeader("nonce");
        String signature=request.getHeader("signature");

        List<String> strs=new ArrayList<String>(){};
        strs.add(timestamp);
        strs.add(nonce);
        strs.add(TOKEN);
        strs.add(data);
        strs.sort((o1, o2) -> {
            if (o1.equals(o2)) {
                return 0;
            } else if (o1.compareTo(o2)>=0) {
                //这里理解为：左边比右边大，则不交换位置,等价从大到小排序
                return 1;
            } else {
                return -1;
            }
        });
        StringBuilder res= new StringBuilder();
        for (String s : strs) {
            res.append(s);
        }
        return signature.equals(sha1Hex(res.toString()));
    }
}
