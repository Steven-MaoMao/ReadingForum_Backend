package capstone_project.ReadingForum_Backend.Service;

import capstone_project.ReadingForum_Backend.Model.Book;

import java.io.IOException;
import java.util.List;

public interface IBookService {
    List<Book> selectAll() throws IOException;
    Book selectById(int id) throws IOException;
    Book selectByNamePublisher(String name, String publisher) throws IOException;
    List<Book> selectFavouriteByPage(int id, int start) throws IOException;
    boolean insert(Book book) throws IOException;
    boolean update(Book book) throws IOException;
}
