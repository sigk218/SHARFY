package com.ssafysns.snsapi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ssafysns.model.service.JwtService;
 
@Service
public class GoogleAPI {
	 
	 
//	 static final String redirectUrl="http://70.12.246.84:8080/login/google";
	 static final String redirectUrl="http://i02a305.p.ssafy.io/login/google";
//	 static final String redirectUrl="http://70.12.246.84:8080/login/google";

    public String getAccessToken (String authorize_code) {
        String id_token = "";
        String reqURL = "https://accounts.google.com/o/oauth2/token";
        

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=434295514268-ei101dmmrffg0sm44srmoffpgej6ruat");
            //vi2Dtr5QUP
            sb.append("&client_secret=ktdrKY1-7vtqHkGPZy_WLeqX");
            sb.append("&redirect_uri=");
            sb.append(redirectUrl);
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();
            
            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
 
            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            System.out.println("result: "+result);
            System.out.println("element: "+element);
            System.out.println("id_Token: "+element.getAsJsonObject().get("id_token").getAsString());
            id_token = element.getAsJsonObject().get("id_token").getAsString();
            System.out.println("access_Token:  "+id_token);
           

            
            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        return id_token;
    }

    public HashMap<String, Object> getUserInfo (String access_Token) {
        //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        HashMap<String, Object> userInfo = new HashMap<>();
        String[] base64EncodedSegments = access_Token.split("\\.");

        System.out.println(Arrays.toString(base64EncodedSegments));
        String base64EncodedHeader = base64EncodedSegments[0];
        String base64EncodedClaims = base64EncodedSegments[1];
        byte[] claims = new byte[1000000];
        claims = DatatypeConverter.parseBase64Binary(base64EncodedClaims);
        String result = new String(claims);
        result+="}";
        System.out.println("result: "+result);
        
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(result);
        
        String email = element.getAsJsonObject().get("email").getAsString();
        System.out.println("email: "+email);
        
        userInfo.put("snsid",email);
        
        return userInfo;
    }

}