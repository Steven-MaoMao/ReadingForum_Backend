package capstone_project.ReadingForum_Backend.Service;

import capstone_project.ReadingForum_Backend.Model.Module;

import java.io.IOException;
import java.util.List;

public interface ISubgroupModuleService {
    List<Module> selectBySubgroupId(int subgroupId) throws IOException;
    List<Module> selectAllModule() throws IOException;
    boolean insert(int subgroupId, int moduleId) throws IOException;
    boolean delete(int subgroupId, int moduleId) throws IOException;
}
