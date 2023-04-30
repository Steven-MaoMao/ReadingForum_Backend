package capstone_project.ReadingForum_Backend.Service;

import capstone_project.ReadingForum_Backend.Model.Book;

import java.io.IOException;
import java.util.List;

public interface IBookService {
    List<Book> selectAll() throws IOException;
    List<Book> selectAllByPage(int start) throws IOException;
    int selectAllNumByPage() throws IOException;
    Book selectById(int id) throws IOException;
    Book selectByNamePublisher(String name, String publisher) throws IOException;
    List<Book> selectFavouriteByPage(int id, int start) throws IOException;
    int selectFavouriteNum(int id) throws IOException;
    List<Book> selectGroupFavouriteByPage(int id, int start) throws IOException;
    int selectGroupFavouriteNum(int id) throws IOException;
    List<Book> selectGroupFavouriteTopTen(int groupId) throws IOException;
    List<Book> selectTopTen() throws IOException;
    List<Book> selectLatestFive() throws IOException;
    List<Book> selectTopFiveByTag(int tagId) throws IOException;
    List<Book> selectBookByTagPage(int tagId, int start) throws IOException;
    int selectBookNumByTagPage(int tagId) throws IOException;
    List<Book> searchBook(String keyword, int start) throws IOException;
    int searchBookNum(String keyword) throws IOException;
    boolean insert(Book book) throws IOException;
    boolean update(Book book) throws IOException;
}
