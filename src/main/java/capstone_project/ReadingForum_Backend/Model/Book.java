package capstone_project.ReadingForum_Backend.Model;

import lombok.Data;

@Data
public class Book {
    private int id = 0;
    private String name = null;
    private String bookCover = null;
    private String author = null;
    private String publisher = null;
    private String publishTime = null;
    private String ISBN = null;
    private String authorIntroduction = null;
    private String bookIntroduction = null;
    private boolean deleted = false;
}
