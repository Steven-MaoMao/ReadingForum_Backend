package capstone_project.ReadingForum_Backend.Service.Impl;

import capstone_project.ReadingForum_Backend.Dao.UserDao;
import capstone_project.ReadingForum_Backend.Model.User;
import capstone_project.ReadingForum_Backend.Service.IUserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    public List<User> selectAll() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            return mapper.selectAll();
        }
    }

    public User selectById(int id) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            return mapper.selectById(id);
        }
    }

    public User selectByUsername(String username) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            return mapper.selectByUsername(username);
        }
    }

    public boolean insert(String username, String password) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            mapper.insert(username, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateById(int id, String password, String nickname, String avatar, String gender, String birthday, String phone, String email, String location, String bio, String state, boolean deleted) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            mapper.updateById(id, password, nickname, avatar, gender, birthday, phone, email, location, bio, state, deleted);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateByUsername(String username, String password, String nickname, String avatar, String gender, String birthday, String phone, String email, String location, String bio, String state, boolean deleted) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            mapper.updateByUsername(username, password, nickname, avatar, gender, birthday, phone, email, location, bio, state, deleted);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
