package capstone_project.ReadingForum_Backend.DaoTests;

import capstone_project.ReadingForum_Backend.Dao.BookRecommendDao;
import capstone_project.ReadingForum_Backend.Dao.SubgroupDao;
import capstone_project.ReadingForum_Backend.Dao.UserDao;
import capstone_project.ReadingForum_Backend.Model.BookRecommend;
import capstone_project.ReadingForum_Backend.Model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserDaoTest {
    @Test
    public void selectAllTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            List<User> dataList = mapper.selectAll();
            System.out.println(dataList);
        }
    }

    @Test
    public void selectByIdTest() throws IOException {
        int id = 1;

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            User dataList = mapper.selectById(id);
            System.out.println(dataList);
        }
    }

    @Test
    public void selectByUsernameTest() throws IOException {
        String username = "user01";

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            User dataList = mapper.selectByUsername(username);
            System.out.println(dataList);
        }
    }

    @Test
    public void insertTest() throws IOException {
        String username = "user02";
        String password = "123456";

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            mapper.insert(username, password);
        }
    }

    @Test
    public void updateTest() throws IOException {
        User user = new User();
        user.setId(7);
        user.setUsername("user01");
        user.setPassword("123456");
        user.setAvatar("D:\\上海大学\\计算机学院\\毕设\\BackendData\\Avatar");

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            mapper.update(user);
        }
    }

    @Test
    public void insertSubgroupTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupDao mapper = session.getMapper(SubgroupDao.class);
            mapper.insert("111", 1);
        }
    }

    @Test
    public void insertBookRecommendTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookRecommendDao mapper = session.getMapper(BookRecommendDao.class);
            mapper.insert(1, "test", 9, 1);
        }
    }
}
