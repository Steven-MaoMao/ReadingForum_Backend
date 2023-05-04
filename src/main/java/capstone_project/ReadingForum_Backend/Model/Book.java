package capstone_project.ReadingForum_Backend.Model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Book {
    private int id = 0;
    private String name = null;
    private String bookCover = null;
    private String author = null;
    private String publisher = null;
    private String publishTime = null;
    private String translator = null;
    private String isbn = null;
    private String authorIntroduction = null;
    private String bookIntroduction = null;
    private int uploadUser = 0;
    private boolean deleted = false;
    private int stars = -1;
    private List<Map> tags = null;
    private User user = null;
}
