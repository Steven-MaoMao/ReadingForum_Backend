package capstone_project.ReadingForum_Backend.Service.Impl;

import capstone_project.ReadingForum_Backend.Dao.SubgroupVoteDao;
import capstone_project.ReadingForum_Backend.Model.SubgroupVote;
import capstone_project.ReadingForum_Backend.Service.ISubgroupVoteService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class SubgroupVoteServiceImpl implements ISubgroupVoteService {
    @Override
    public List<SubgroupVote> selectBySubgroupModuleId(int subgroupModuleId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupVoteDao mapper = session.getMapper(SubgroupVoteDao.class);
            return mapper.selectBySubgroupModuleId(subgroupModuleId);
        }
    }

    @Override
    public SubgroupVote selectByName(String name) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupVoteDao mapper = session.getMapper(SubgroupVoteDao.class);
            return mapper.selectByName(name);
        }
    }

    @Override
    public boolean insert(String name, String description, int subgroupModuleId, int userId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupVoteDao mapper = session.getMapper(SubgroupVoteDao.class);
            mapper.insert(name, description, subgroupModuleId, userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(SubgroupVote subgroupVote) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupVoteDao mapper = session.getMapper(SubgroupVoteDao.class);
            mapper.update(subgroupVote);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(int id) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupVoteDao mapper = session.getMapper(SubgroupVoteDao.class);
            mapper.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
