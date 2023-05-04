package capstone_project.ReadingForum_Backend.Service.Impl;

import capstone_project.ReadingForum_Backend.Dao.GroupMemberDao;
import capstone_project.ReadingForum_Backend.Model.GroupMember;
import capstone_project.ReadingForum_Backend.Service.IGroupMemberService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class GroupMemberServiceImpl implements IGroupMemberService {
    @Override
    public GroupMember selectGroupMember(int groupId, int userId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            GroupMemberDao mapper = session.getMapper(GroupMemberDao.class);
            return mapper.selectGroupMember(groupId, userId);
        }
    }

    @Override
    public boolean joinGroup(int groupId, int userId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            GroupMemberDao mapper = session.getMapper(GroupMemberDao.class);
            mapper.joinGroup(groupId, userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean setGroupManager(int groupId, int userId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            GroupMemberDao mapper = session.getMapper(GroupMemberDao.class);
            mapper.setGroupManager(groupId, userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean dismissGroupManager(int groupId, int userId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            GroupMemberDao mapper = session.getMapper(GroupMemberDao.class);
            mapper.dismissGroupManager(groupId, userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean quitGroup(int groupId, int userId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            GroupMemberDao mapper = session.getMapper(GroupMemberDao.class);
            mapper.quitGroup(groupId, userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
