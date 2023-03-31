package capstone_project.ReadingForum_Backend.Controller;

import lombok.Data;

import java.util.Map;

@Data
public class Result {
    private int code = 0;
    /*
    * 0   失败
    * 1   成功
    * 100 token失效
    * */
    private String message = null;
    private Map<String, Object> data = null;
}
