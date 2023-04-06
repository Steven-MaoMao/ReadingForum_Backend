package capstone_project.ReadingForum_Backend.Dao;

import capstone_project.ReadingForum_Backend.Model.Book;

import java.util.List;

public interface BookDao {
    List<Book> selectAll();
    Book selectById(int id);
    Book selectByNamePublisher(String name, String publisher);
    int selectFavouriteNum(int id);
    List<Book> selectFavouriteByPage(int id, int start);
    List<Book> selectTopTen();
    List<Book> selectLatestFive();
    List<Book> selectTopFiveByTag(int tagId);
    List<Book> searchBook(String keyword, int start);
    int searchBookNum(String keyword);
    void insert(Book book);
    void update(Book book);
}
