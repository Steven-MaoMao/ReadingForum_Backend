package capstone_project.ReadingForum_Backend.Service.Impl;

import capstone_project.ReadingForum_Backend.Dao.SubgroupDao;
import capstone_project.ReadingForum_Backend.Model.Subgroup;
import capstone_project.ReadingForum_Backend.Service.ISubgroupService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class SubgroupServiceImpl implements ISubgroupService {
    @Override
    public Subgroup selectByName(String subgroupName) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupDao mapper = session.getMapper(SubgroupDao.class);
            return mapper.selectByName(subgroupName);
        }
    }

    @Override
    public List<Subgroup> selectByGroupId(int groupId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupDao mapper = session.getMapper(SubgroupDao.class);
            return mapper.selectByGroupId(groupId);
        }
    }

    @Override
    public boolean insert(String subgroupName, int groupId, int frameId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupDao mapper = session.getMapper(SubgroupDao.class);
            mapper.insert(subgroupName, groupId, frameId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean insertWithText(String subgroupName, int groupId, int frameId, String text) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupDao mapper = session.getMapper(SubgroupDao.class);
            mapper.insertWithText(subgroupName, groupId, frameId, text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(int subgroupId, String subgroupName) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupDao mapper = session.getMapper(SubgroupDao.class);
            mapper.update(subgroupId, subgroupName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(int subgroupId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupDao mapper = session.getMapper(SubgroupDao.class);
            mapper.delete(subgroupId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
