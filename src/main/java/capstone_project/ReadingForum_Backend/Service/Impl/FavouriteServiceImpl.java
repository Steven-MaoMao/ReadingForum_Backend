package capstone_project.ReadingForum_Backend.Service.Impl;

import capstone_project.ReadingForum_Backend.Dao.FavouriteDao;
import capstone_project.ReadingForum_Backend.Service.IFavouriteService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FavouriteServiceImpl implements IFavouriteService {
    @Override
    public List<Integer> selectFavourite(int userId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            FavouriteDao mapper = session.getMapper(FavouriteDao.class);
            return mapper.selectFavourite(userId);
        }
    }

    @Override
    public boolean insert(int userId, int bookId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            FavouriteDao mapper = session.getMapper(FavouriteDao.class);
            mapper.insert(userId, bookId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(int userId, int bookId) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            FavouriteDao mapper = session.getMapper(FavouriteDao.class);
            mapper.delete(userId, bookId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
