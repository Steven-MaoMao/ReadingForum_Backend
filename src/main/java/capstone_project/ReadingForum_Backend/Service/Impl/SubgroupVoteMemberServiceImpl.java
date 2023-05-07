package capstone_project.ReadingForum_Backend.Service.Impl;

import capstone_project.ReadingForum_Backend.Dao.SubgroupVoteMemberDao;
import capstone_project.ReadingForum_Backend.Model.SubgroupVoteMember;
import capstone_project.ReadingForum_Backend.Service.ISubgroupVoteMemberService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class SubgroupVoteMemberServiceImpl implements ISubgroupVoteMemberService {
    @Override
    public List<SubgroupVoteMember> selectBySubgroupVoteId(int subgroupVoteId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupVoteMemberDao mapper = session.getMapper(SubgroupVoteMemberDao.class);
            return mapper.selectBySubgroupVoteId(subgroupVoteId);
        }
    }

    @Override
    public boolean insert(int userId, int subgroupVoteId, String state) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupVoteMemberDao mapper = session.getMapper(SubgroupVoteMemberDao.class);
            mapper.insert(userId, subgroupVoteId, state);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean voteYes(int userId, int subgroupVoteId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupVoteMemberDao mapper = session.getMapper(SubgroupVoteMemberDao.class);
            mapper.voteYes(userId, subgroupVoteId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean voteNo(int userId, int subgroupVoteId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SubgroupVoteMemberDao mapper = session.getMapper(SubgroupVoteMemberDao.class);
            mapper.voteNo(userId, subgroupVoteId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
