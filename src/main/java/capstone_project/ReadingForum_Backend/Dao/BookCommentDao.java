package capstone_project.ReadingForum_Backend.Dao;

import capstone_project.ReadingForum_Backend.Model.BookComment;

import java.util.List;

public interface BookCommentDao {
    List<BookComment> selectCommentByPage(int bookId, int start);
    int selectCommentNum(int bookId);
    void insert(int bookId, int userId, String comment);
}
