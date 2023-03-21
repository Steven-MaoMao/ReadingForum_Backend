package capstone_project.ReadingForum_Backend.Config;

import capstone_project.ReadingForum_Backend.Controller.JWT;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = request.getHeader("token");
            String username = JWT.parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
