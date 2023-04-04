package capstone_project.ReadingForum_Backend.Service.Impl;

import capstone_project.ReadingForum_Backend.Dao.BookRateDao;
import capstone_project.ReadingForum_Backend.Service.IBookRateService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class BookRateServiceImpl implements IBookRateService {
    @Override
    public int selectRate(int bookId, int userId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookRateDao mapper = session.getMapper(BookRateDao.class);
            return mapper.selectRate(bookId, userId);
        }
    }

    @Override
    public int selectFive(int bookId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookRateDao mapper = session.getMapper(BookRateDao.class);
            return mapper.selectFive(bookId);
        }
    }

    @Override
    public int selectFour(int bookId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookRateDao mapper = session.getMapper(BookRateDao.class);
            return mapper.selectFour(bookId);
        }
    }

    @Override
    public int selectThree(int bookId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookRateDao mapper = session.getMapper(BookRateDao.class);
            return mapper.selectThree(bookId);
        }
    }

    @Override
    public int selectTwo(int bookId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookRateDao mapper = session.getMapper(BookRateDao.class);
            return mapper.selectTwo(bookId);
        }
    }

    @Override
    public int selectOne(int bookId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookRateDao mapper = session.getMapper(BookRateDao.class);
            return mapper.selectOne(bookId);
        }
    }

    @Override
    public boolean insert(int bookId, int userId, int rate) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookRateDao mapper = session.getMapper(BookRateDao.class);
            mapper.insert(bookId, userId, rate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(int bookId, int userId, int rate) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookRateDao mapper = session.getMapper(BookRateDao.class);
            mapper.update(bookId, userId, rate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
