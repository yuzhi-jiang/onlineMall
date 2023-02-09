package com.yefeng.springtest.Client.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yefeng.springtest.Client.entity.UserDetail2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static com.yefeng.springtest.util.Constant.*;
@Component
public class SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        HashMap<String, Object> map = new HashMap<>();
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        UserDetail2 user = (UserDetail2) authentication.getPrincipal();

        String[] rids = parameterMap.get("role");

        if (rids != null && rids.length == 1 && user.hasRoleByParam("name",rids[0])) {
            user.setLoginRid(rids[0]);
            map.put(ERROR_CODE,CODE_OK);
            map.put(ERROR_MSSAGE,rids[0]+":用户登录成功");
        }else{
            map.put(ERROR_CODE,CODE_USER_ERROR);
            map.put(ERROR_MSSAGE,"用户没有角色登录权限");
        }


        String json = objectMapper.writeValueAsString(map);



        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(json);
        writer.flush();
        writer.close();
    }
}
