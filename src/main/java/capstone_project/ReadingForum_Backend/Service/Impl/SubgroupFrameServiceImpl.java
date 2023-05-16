package capstone_project.ReadingForum_Backend.Service.Impl;

import capstone_project.ReadingForum_Backend.Dao.SubgroupFrameDao;
import capstone_project.ReadingForum_Backend.Model.SubgroupFrame;
import capstone_project.ReadingForum_Backend.Service.ISubgroupFrameService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class SubgroupFrameServiceImpl implements ISubgroupFrameService {
    @Override
    public List<SubgroupFrame> selectBySubgroupId(int subgroupId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupFrameDao mapper = session.getMapper(SubgroupFrameDao.class);
            return mapper.selectBySubgroupId(subgroupId);
        }
    }

    @Override
    public int insert(SubgroupFrame subgroupFrame) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupFrameDao mapper = session.getMapper(SubgroupFrameDao.class);
            return mapper.insert(subgroupFrame);
        }
    }

    @Override
    public int insertWithText(SubgroupFrame subgroupFrame) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupFrameDao mapper = session.getMapper(SubgroupFrameDao.class);
            return mapper.insertWithText(subgroupFrame);
        }
    }

    @Override
    public boolean delete(int subgroupFrameId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupFrameDao mapper = session.getMapper(SubgroupFrameDao.class);
            mapper.delete(subgroupFrameId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
