package com.ibrahimayhan.jwt;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	/*   // 1️⃣ HTTP durum kodu ve içerik tipi ayarı
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("application/json;charset=UTF-8");

    // 2️⃣ RootEntity nesnesini manuel oluşturuyoruz (error metodu 500 döndürmesin diye)
    RootEntity<Object> body = new RootEntity<>();
    body.setStatus(401);
    body.setPayload(null);
    body.setErrorMessage("Token süresi dolmuş veya geçersiz: " + authException.getMessage());

    // 3️⃣ JSON’a çevirip yazdır
    ObjectMapper mapper = new ObjectMapper();
    response.getWriter().write(mapper.writeValueAsString(body));*/
}
