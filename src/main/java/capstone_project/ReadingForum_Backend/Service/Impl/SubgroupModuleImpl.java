package capstone_project.ReadingForum_Backend.Service.Impl;

import capstone_project.ReadingForum_Backend.Dao.SubgroupModuleDao;
import capstone_project.ReadingForum_Backend.Model.Module;
import capstone_project.ReadingForum_Backend.Service.ISubgroupModuleService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class SubgroupModuleImpl implements ISubgroupModuleService {
    @Override
    public List<Module> selectBySubgroupId(int subgroupId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupModuleDao mapper = session.getMapper(SubgroupModuleDao.class);
            return mapper.selectBySubgroupId(subgroupId);
        }
    }

    @Override
    public List<Module> selectAllModule() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupModuleDao mapper = session.getMapper(SubgroupModuleDao.class);
            return mapper.selectAllModule();
        }
    }

    @Override
    public boolean insert(int subgroupId, int moduleId, String name) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupModuleDao mapper = session.getMapper(SubgroupModuleDao.class);
            mapper.insert(subgroupId, moduleId, name);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(String name, int id) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupModuleDao mapper = session.getMapper(SubgroupModuleDao.class);
            mapper.update(name, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String name) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupModuleDao mapper = session.getMapper(SubgroupModuleDao.class);
            mapper.delete(name);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
