package capstone_project.ReadingForum_Backend.Service;

import capstone_project.ReadingForum_Backend.Model.BookComment;

import java.io.IOException;
import java.util.List;

public interface IBookCommentService {
    List<BookComment> selectCommentByPage(int bookId, int start) throws IOException;
    int selectCommentNum(int bookId) throws IOException;
    boolean insert(int bookId, int userId, String comment) throws IOException;
}
