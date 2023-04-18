package capstone_project.ReadingForum_Backend.Service.Impl;

import capstone_project.ReadingForum_Backend.Dao.BookDao;
import capstone_project.ReadingForum_Backend.Model.Book;
import capstone_project.ReadingForum_Backend.Service.IBookService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class BookServiceImpl implements IBookService {
    public List<Book> selectAll() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookDao mapper = session.getMapper(BookDao.class);
            return mapper.selectAll();
        }
    }

    public List<Book> selectAllByPage(int start) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookDao mapper = session.getMapper(BookDao.class);
            return mapper.selectAllByPage(start);
        }
    }

    public int selectAllNumByPage() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookDao mapper = session.getMapper(BookDao.class);
            return mapper.selectAllNumByPage();
        }
    }

    public Book selectById(int id) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookDao mapper = session.getMapper(BookDao.class);
            return mapper.selectById(id);
        }
    }

    public Book selectByNamePublisher(String name, String publisher) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookDao mapper = session.getMapper(BookDao.class);
            return mapper.selectByNamePublisher(name, publisher);
        }
    }

    public List<Book> selectFavouriteByPage(int id, int start) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookDao mapper = session.getMapper(BookDao.class);
            return mapper.selectFavouriteByPage(id, start);
        }
    }

    public int selectFavouriteNum(int id) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookDao mapper = session.getMapper(BookDao.class);
            return mapper.selectFavouriteNum(id);
        }
    }

    public List<Book> selectTopTen() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookDao mapper = session.getMapper(BookDao.class);
            return mapper.selectTopTen();
        }
    }

    public List<Book> selectLatestFive() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookDao mapper = session.getMapper(BookDao.class);
            return mapper.selectLatestFive();
        }
    }

    public List<Book> selectTopFiveByTag(int tagId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookDao mapper = session.getMapper(BookDao.class);
            return mapper.selectTopFiveByTag(tagId);
        }
    }

    public List<Book> selectBookByTagPage(int tagId, int start) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookDao mapper = session.getMapper(BookDao.class);
            return mapper.selectBookByTagPage(tagId, start);
        }
    }

    public int selectBookNumByTagPage(int tagId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookDao mapper = session.getMapper(BookDao.class);
            return mapper.selectBookNumByTagPage(tagId);
        }
    }

    public List<Book> searchBook(String keyword, int start) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookDao mapper = session.getMapper(BookDao.class);
            return mapper.searchBook(keyword, start);
        }
    }

    public int searchBookNum(String keyword) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookDao mapper = session.getMapper(BookDao.class);
            return mapper.searchBookNum(keyword);
        }
    }

    public boolean insert(Book book) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookDao mapper = session.getMapper(BookDao.class);
            mapper.insert(book);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(Book book) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookDao mapper = session.getMapper(BookDao.class);
            mapper.update(book);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
