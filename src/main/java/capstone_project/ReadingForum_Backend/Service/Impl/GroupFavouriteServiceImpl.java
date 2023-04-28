package capstone_project.ReadingForum_Backend.Service.Impl;

import capstone_project.ReadingForum_Backend.Dao.GroupFavouriteDao;
import capstone_project.ReadingForum_Backend.Service.IGroupFavouriteService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class GroupFavouriteServiceImpl implements IGroupFavouriteService {
    @Override
    public List<Integer> selectGroupFavourite(int groupId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            GroupFavouriteDao mapper = session.getMapper(GroupFavouriteDao.class);
            return mapper.selectGroupFavourite(groupId);
        }
    }

    @Override
    public boolean insert(int groupId, int bookId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            GroupFavouriteDao mapper = session.getMapper(GroupFavouriteDao.class);
            mapper.insert(groupId, bookId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(int groupId, int bookId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            GroupFavouriteDao mapper = session.getMapper(GroupFavouriteDao.class);
            mapper.delete(groupId, bookId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
