package capstone_project.ReadingForum_Backend.Service.Impl;

import capstone_project.ReadingForum_Backend.Dao.BookRecommendDao;
import capstone_project.ReadingForum_Backend.Model.BookRecommend;
import capstone_project.ReadingForum_Backend.Service.IBookRecommendService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class BookRecommendServiceImpl implements IBookRecommendService {
    @Override
    public List<BookRecommend> selectBySubgroupId(int subgroupId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookRecommendDao mapper = session.getMapper(BookRecommendDao.class);
            return mapper.selectBySubgroupId(subgroupId);
        }
    }

    @Override
    public List<BookRecommend> selectByName(int id) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookRecommendDao mapper = session.getMapper(BookRecommendDao.class);
            return mapper.selectByName(id);
        }
    }

    @Override
    public boolean insert(int bookId, String recommendReason, int userId, int subgroupModelId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookRecommendDao mapper = session.getMapper(BookRecommendDao.class);
            mapper.insert(bookId, recommendReason, userId, subgroupModelId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(int bookRecommendId, String recommendReason) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookRecommendDao mapper = session.getMapper(BookRecommendDao.class);
            mapper.update(bookRecommendId, recommendReason);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(int bookRecommendId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookRecommendDao mapper = session.getMapper(BookRecommendDao.class);
            mapper.delete(bookRecommendId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
