package capstone_project.ReadingForum_Backend.Service;

import capstone_project.ReadingForum_Backend.Model.SubgroupNotice;

import java.io.IOException;
import java.util.List;

public interface ISubgroupNoticeService {
    List<SubgroupNotice> selectBySubgroupId(int subgroupId) throws IOException;
    List<SubgroupNotice> selectByName(String name) throws IOException;
    boolean insert(String title, String text, int userId, int subgroupModelId) throws IOException;
    boolean update(int id, String title, String text) throws IOException;
    boolean delete(int id) throws IOException;
}
