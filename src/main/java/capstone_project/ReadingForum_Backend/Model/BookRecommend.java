package capstone_project.ReadingForum_Backend.Model;

import lombok.Data;

@Data
public class BookRecommend {
    private int id = 0;
    private int bookId = 0;
    private String recommendReason = null;
    private int userId = 0;
    private String time = null;
    private int subgroupId = 0;
    private User user = null;
    private Book book = null;
}
