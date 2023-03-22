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
