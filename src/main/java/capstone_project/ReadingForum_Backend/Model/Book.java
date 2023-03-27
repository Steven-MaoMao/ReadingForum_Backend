package capstone_project.ReadingForum_Backend.Model;

import lombok.Data;

@Data
public class Book {
    private int id;
    private String name;
    private String author;
    private String publisher;
    private String publish_time;
    private String ISBN;
    private String author_introduction;
    private String book_introduction;
    private boolean ban;
    private boolean deleted;
}
