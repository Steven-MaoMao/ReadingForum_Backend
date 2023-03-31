package capstone_project.ReadingForum_Backend.Model;

import lombok.Data;

@Data
public class Book {
    private int id = 0;
    private String name = null;
    private String author = null;
    private String publisher = null;
    private String publish_time = null;
    private String ISBN = null;
    private String author_introduction = null;
    private String book_introduction = null;
    private boolean deleted = false;
}
