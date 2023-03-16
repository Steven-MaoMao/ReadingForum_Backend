package capstone_project.ReadingForum_Backend.DaoTests;

import capstone_project.ReadingForum_Backend.Dao.UserDao;
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
    public void updateByIdTest() throws IOException {
        int id = 1;
        String password = "123456";
        String nickname = "帽帽";
        String avatar = null;
        String gender = "男";
        String birthday = "2000-10-18";
        String phone = "18116468332";
        String email = "mjdfx120312@163.com";
        String location = "上海";
        String bio = "(^_^)";
        String state = null;
        boolean deleted = false;

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            mapper.updateById(id, password, nickname, avatar, gender, birthday, phone, email, location, bio, state, deleted);
        }
    }

    @Test
    public void updateByUsernameTest() throws IOException {
        String username = "user01";
        String password = "123456";
        String nickname = "帽帽";
        String avatar = null;
        String gender = "男";
        String birthday = "2000-10-18";
        String phone = "18116468332";
        String email = "mjdfx120312@163.com";
        String location = "上海";
        String bio = "\\(^_^)/";
        String state = null;
        boolean deleted = false;

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            mapper.updateByUsername(username, password, nickname, avatar, gender, birthday, phone, email, location, bio, state, deleted);
        }
    }
}
