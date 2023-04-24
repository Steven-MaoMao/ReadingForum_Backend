package capstone_project.ReadingForum_Backend.Service.Impl;

import capstone_project.ReadingForum_Backend.Dao.GroupDao;
import capstone_project.ReadingForum_Backend.Model.Group;
import capstone_project.ReadingForum_Backend.Service.IGroupService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class GroupServiceImpl implements IGroupService {
    @Override
    public List<Group> selectAll() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            GroupDao mapper = session.getMapper(GroupDao.class);
            return mapper.selectAll();
        }
    }

    @Override
    public List<Group> selectAllByPage(int start) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            GroupDao mapper = session.getMapper(GroupDao.class);
            return mapper.selectAllByPage(start);
        }
    }

    @Override
    public int selectAllNum() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            GroupDao mapper = session.getMapper(GroupDao.class);
            return mapper.selectAllNum();
        }
    }

    @Override
    public Group selectById(int id) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            GroupDao mapper = session.getMapper(GroupDao.class);
            return mapper.selectById(id);
        }
    }

    @Override
    public Group selectByCreateUser(int userId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            GroupDao mapper = session.getMapper(GroupDao.class);
            return mapper.selectByCreateUser(userId);
        }
    }

    @Override
    public List<Group> searchGroup(String keyword, int start) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            GroupDao mapper = session.getMapper(GroupDao.class);
            return mapper.searchGroup(keyword, start);
        }
    }

    @Override
    public int searchGroupNum(String keyword) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            GroupDao mapper = session.getMapper(GroupDao.class);
            return mapper.searchGroupNum(keyword);
        }
    }

    @Override
    public List<Group> selectTopFiveGroup() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            GroupDao mapper = session.getMapper(GroupDao.class);
            return mapper.selectTopFiveGroup();
        }
    }

    @Override
    public boolean insert(String name, int createUser) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            GroupDao mapper = session.getMapper(GroupDao.class);
            mapper.insert(name, createUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Group group) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            GroupDao mapper = session.getMapper(GroupDao.class);
            mapper.update(group);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(int groupId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            GroupDao mapper = session.getMapper(GroupDao.class);
            mapper.delete(groupId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
