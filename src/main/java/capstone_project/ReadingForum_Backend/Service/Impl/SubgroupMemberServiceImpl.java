package capstone_project.ReadingForum_Backend.Service.Impl;

import capstone_project.ReadingForum_Backend.Dao.SubgroupMemberDao;
import capstone_project.ReadingForum_Backend.Service.ISubgroupMemberService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class SubgroupMemberServiceImpl implements ISubgroupMemberService {
    @Override
    public boolean insert(int userId, int subgroupId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupMemberDao mapper = session.getMapper(SubgroupMemberDao.class);
            mapper.insert(userId, subgroupId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(int subgroupMemberId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupMemberDao mapper = session.getMapper(SubgroupMemberDao.class);
            mapper.delete(subgroupMemberId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
