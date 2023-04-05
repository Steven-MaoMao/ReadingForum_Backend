package capstone_project.ReadingForum_Backend.Service.Impl;

import capstone_project.ReadingForum_Backend.Dao.BookCommentDao;
import capstone_project.ReadingForum_Backend.Model.BookComment;
import capstone_project.ReadingForum_Backend.Service.IBookCommentService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class BookCommentServiceImpl implements IBookCommentService {
    @Override
    public List<BookComment> selectCommentByPage(int bookId, int start) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookCommentDao mapper = session.getMapper(BookCommentDao.class);
            return mapper.selectCommentByPage(bookId, start);
        }
    }

    @Override
    public int selectCommentNum(int bookId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookCommentDao mapper = session.getMapper(BookCommentDao.class);
            return mapper.selectCommentNum(bookId);
        }
    }

    @Override
    public boolean insert(int bookId, int userId, String comment) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookCommentDao mapper = session.getMapper(BookCommentDao.class);
            mapper.insert(bookId, userId, comment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
