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

    public List<User> selectFollowingByPage(int followerId, int start) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            return mapper.selectFollowingByPage(followerId, start);
        }
    }

    public int selectFollowingNum(int followerId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            return mapper.selectFollowingNum(followerId);
        }
    }

    public List<User> selectFollowerByPage(int followingId, int start) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            return mapper.selectFollowerByPage(followingId, start);
        }
    }

    public int selectFollowerNum(int followingId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            return mapper.selectFollowerNum(followingId);
        }
    }

    public List<User> searchUser(String keyword, int start) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            return mapper.searchUser(keyword, start);
        }
    }

    public int searchUserNum(String keyword) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            return mapper.searchUserNum(keyword);
        }
    }

    public List<User> selectGroupMember(int groupId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            return mapper.selectGroupMember(groupId);
        }
    }

    @Override
    public List<User> selectSubgroupMember(int subgroupId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            return mapper.selectSubgroupMember(subgroupId);
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

    public boolean update(User user) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            mapper.update(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean joinGroup(int groupId, int id) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            mapper.joinGroup(groupId, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean setGroupManager(int id) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            mapper.setGroupManager(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean dismissGroupManager(int id) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            mapper.dismissGroupManager(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean quitGroup(int id) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserDao mapper = session.getMapper(UserDao.class);
            mapper.quitGroup(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
