package cn.auroralab.devtrack.interceptor;

import cn.auroralab.devtrack.annotation.SkipTokenVerification;
import cn.auroralab.devtrack.exception.ResponseException;
import cn.auroralab.devtrack.exception.token.ExpiredTokenException;
import cn.auroralab.devtrack.exception.token.InvalidTokenException;
import cn.auroralab.devtrack.exception.token.TokenIsEmptyException;
import cn.auroralab.devtrack.util.JwtUtils;
import cn.auroralab.devtrack.vo.ResponseVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;


public class JwtInterceptor implements HandlerInterceptor {
    private void responseData(HttpServletResponse response, Object data) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(new ObjectMapper().writeValueAsString(data));
        }
    }

    private void jwtVerify(String jwt) throws ResponseException {
        if (jwt == null || jwt.equals(""))
            throw new TokenIsEmptyException();

        String username;
        try {
            username = JwtUtils.getAudience(jwt);
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (SignatureException e) {
            throw new InvalidTokenException();
        }
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String jwt = request.getHeader("Authorization");

        // 如果注解不是映射到方法的，直接通过
        if (!(handler instanceof HandlerMethod handlerMethod)) return true;

        Method method = handlerMethod.getMethod();

        // 通过被SkipTokenVerification注解的请求
        if (method.isAnnotationPresent(SkipTokenVerification.class)) {
            SkipTokenVerification skipTokenVerification = method.getAnnotation(SkipTokenVerification.class);
            if (skipTokenVerification.required())
                return true;
        }

        try {
            jwtVerify(jwt);
        } catch (ResponseException e) {
            responseData(response, new ResponseVO<>(e.statusCode, e.getMessage()));
            return false;
        }

        return true;
    }
}
