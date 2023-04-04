package capstone_project.ReadingForum_Backend.Dao;

public interface BookRateDao {
    int selectRate(int bookId, int userId);
    int selectFive(int bookId);
    int selectFour(int bookId);
    int selectThree(int bookId);
    int selectTwo(int bookId);
    int selectOne(int bookId);
    void insert(int bookId, int userId, int rate);
    void update(int bookId, int userId, int rate);
}
