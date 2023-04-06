package capstone_project.ReadingForum_Backend.Service.Impl;

import capstone_project.ReadingForum_Backend.Dao.TagDao;
import capstone_project.ReadingForum_Backend.Service.ITagService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements ITagService {
    @Override
    public List<Map> selectAll() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            TagDao mapper = session.getMapper(TagDao.class);
            return mapper.selectAll();
        }
    }

    @Override
    public List<Map> selectByBook(int bookId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            TagDao mapper = session.getMapper(TagDao.class);
            return mapper.selectByBook(bookId);
        }
    }

    @Override
    public List<Map> selectTopFive() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            TagDao mapper = session.getMapper(TagDao.class);
            return mapper.selectTopFive();
        }
    }

    @Override
    public List<Map> orderTopTenFavouriteTagById(int userId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            TagDao mapper = session.getMapper(TagDao.class);
            return mapper.orderTopTenFavouriteTagById(userId);
        }
    }
}
