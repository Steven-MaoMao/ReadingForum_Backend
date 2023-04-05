package capstone_project.ReadingForum_Backend.Model;

import lombok.Data;

@Data
public class BookComment {
    private int id = 0;
    private int bookId = 0;
    private int userId = 0;
    private String comment = null;
    private String time = null;
    private String username = null;
    private String nickname = null;
    private String avatar = null;
}
