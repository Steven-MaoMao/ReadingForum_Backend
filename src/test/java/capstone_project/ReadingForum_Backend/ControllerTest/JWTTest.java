package capstone_project.ReadingForum_Backend.ControllerTest;

import capstone_project.ReadingForum_Backend.Controller.JWT;
import org.junit.jupiter.api.Test;

public class JWTTest {
    @Test
    public void createTokenTest() {
        System.out.println(JWT.createToken("test"));
    }

    @Test
    public void parseTokenTest() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRlc3QiLCJleHAiOjE2NzkzOTk4Nzl9.jeO8lJc5VIuA9Z3kDbqTaWYcO72SxuDHDiASlO1hPlk";
        System.out.println(JWT.parseToken(token));
    }
}
