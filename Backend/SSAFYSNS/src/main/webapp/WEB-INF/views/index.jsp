<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
<!-- 	구글 플랫폼 라이브러리-->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=61371210ed3f2e84bea6f3de4869f97f&redirect_uri=http://localhost:8080/KakaoLogin&response_type=code">
            <img src="/img/kakao_account_login_btn_medium_narrow.png">
        </a>
        <br>
        <a href="https://nid.naver.com/oauth2.0/authorize?client_id=MyOzYfN5jsCLdO3clqvX&redirect_uri=http://localhost:8080/NaverLogin&response_type=code">
			<img width="223" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/>
		</a>
		<br>
        <a href="https://github.com/login/oauth/authorize?client_id=1f5e75a219bc30381489&redirect_uri=http://localhost:8080/GithubLogin&response_type=code">
			<button>깃허브 로그인</button>
		</a>
		
		<br>
		<a href="https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&access_type=offline&include_granted_scopes=true&state=state_parameter_passthrough_value&redirect_uri=http://localhost:8080/GoogleLogin&response_type=code&client_id=434295514268-ei101dmmrffg0sm44srmoffpgej6ruat.apps.googleusercontent.com">
		로그인</a>
		
		
  

    
 


</body>                                                                                                                                                                                                                ㅠㅠ
</html>
