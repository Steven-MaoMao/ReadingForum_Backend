package capstone_project.ReadingForum_Backend.Service.Impl;

import capstone_project.ReadingForum_Backend.Dao.FollowDao;
import capstone_project.ReadingForum_Backend.Service.IFollowService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FollowServiceImpl implements IFollowService {
    @Override
    public List<Integer> selectFollowing(int followerId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            FollowDao mapper = session.getMapper(FollowDao.class);
            return mapper.selectFollowing(followerId);
        }
    }

    @Override
    public boolean insert(int followerId, int followingId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            FollowDao mapper = session.getMapper(FollowDao.class);
            mapper.insert(followerId, followingId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(int followerId, int followingId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            FollowDao mapper = session.getMapper(FollowDao.class);
            mapper.delete(followerId, followingId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
