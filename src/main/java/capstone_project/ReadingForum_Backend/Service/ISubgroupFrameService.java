package capstone_project.ReadingForum_Backend.Service;

import capstone_project.ReadingForum_Backend.Model.SubgroupFrame;

import java.io.IOException;
import java.util.List;

public interface ISubgroupFrameService {
    List<SubgroupFrame> selectBySubgroupId(int subgroupId) throws IOException;
    int insert(SubgroupFrame subgroupFrame) throws IOException;
    boolean delete(int subgroupFrameId) throws IOException;
}
