package com.project.chawchaw.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.chawchaw.config.jwt.JwtTokenProvider;
import com.project.chawchaw.config.response.DefaultResponseVo;
import com.project.chawchaw.config.response.ResponseMessage;
import com.project.chawchaw.entity.User;
import com.project.chawchaw.exception.UserNotFoundException;
import com.project.chawchaw.repository.user.UserRepository;
import com.project.chawchaw.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


public class LogoutHandler implements LogoutSuccessHandler {
    private UserService userService;
    private JwtTokenProvider jwtTokenProvider;

    public LogoutHandler(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();

        if (token == null || !token.startsWith("Bearer")) {
            writer.print(mapper.writeValueAsString(DefaultResponseVo.res(ResponseMessage.LOGOUT_FAIL, false)));
            return;
        }
        token = token.replace("Bearer ", "");

        try {

            if (token != null && jwtTokenProvider.validateToken(token)) {


                Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
                userService.changeLastLogOut(userId);
                writer.print(mapper.writeValueAsString(DefaultResponseVo.res(ResponseMessage.LOGOUT_SUCCESS, true)));


            }
//

        }catch (ExpiredJwtException  e){
            writer.print(mapper.writeValueAsString(DefaultResponseVo.res(ResponseMessage.EXPIRED_TOKEN, false)));
            return;
    }    catch (Exception e){
            writer.print(mapper.writeValueAsString(DefaultResponseVo.res(ResponseMessage.LOGOUT_FAIL, false)));
            return;
        }

    }
}
