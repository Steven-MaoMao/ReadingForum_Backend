package capstone_project.ReadingForum_Backend.Config;

import capstone_project.ReadingForum_Backend.Controller.JWT;
import capstone_project.ReadingForum_Backend.Controller.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
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
            Result result = new Result();
            result.setCode(100);
            ObjectMapper objectMapper = new ObjectMapper();
            String resultInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
            response.getWriter().write(resultInString);
            return false;
        }
    }
}
