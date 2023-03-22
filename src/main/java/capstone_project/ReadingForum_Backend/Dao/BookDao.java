package capstone_project.ReadingForum_Backend.Dao;

import capstone_project.ReadingForum_Backend.Model.Book;

import java.util.List;

public interface BookDao {
    List<Book> selectAll();
    Book selectById(int id);
    Book selectByNamePublisher(String name, String publisher);
    void insert(Book book);
    void update(Book book);
}
