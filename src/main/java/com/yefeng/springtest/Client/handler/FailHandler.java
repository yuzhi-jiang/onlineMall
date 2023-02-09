package com.yefeng.springtest.Client.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import static com.yefeng.springtest.util.Constant.*;


@Component
public class FailHandler implements AuthenticationFailureHandler {


    @Autowired
    ObjectMapper objectMapper;



    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        HashMap<String, Object> map = new HashMap<>();
        map.put(ERROR_CODE,Code_LOGIN_FAIL);
        map.put(ERROR_MSSAGE,"用户登录失败");
        String json = objectMapper.writeValueAsString(map);
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(json);
        writer.flush();
        writer.close();
    }
}
