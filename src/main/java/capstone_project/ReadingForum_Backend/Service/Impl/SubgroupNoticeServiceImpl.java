package capstone_project.ReadingForum_Backend.Service.Impl;

import capstone_project.ReadingForum_Backend.Dao.SubgroupNoticeDao;
import capstone_project.ReadingForum_Backend.Model.SubgroupNotice;
import capstone_project.ReadingForum_Backend.Service.ISubgroupNoticeService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class SubgroupNoticeServiceImpl implements ISubgroupNoticeService {
    @Override
    public List<SubgroupNotice> selectBySubgroupId(int subgroupId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupNoticeDao mapper = session.getMapper(SubgroupNoticeDao.class);
            return mapper.selectBySubgroupId(subgroupId);
        }
    }

    @Override
    public List<SubgroupNotice> selectByName(String name) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupNoticeDao mapper = session.getMapper(SubgroupNoticeDao.class);
            return mapper.selectByName(name);
        }
    }

    @Override
    public boolean insert(String title, String text, int userId, int subgroupModelId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupNoticeDao mapper = session.getMapper(SubgroupNoticeDao.class);
            mapper.insert(title, text, userId, subgroupModelId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(int id, String title, String text) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupNoticeDao mapper = session.getMapper(SubgroupNoticeDao.class);
            mapper.update(id, title, text);
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
            SubgroupNoticeDao mapper = session.getMapper(SubgroupNoticeDao.class);
            mapper.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
