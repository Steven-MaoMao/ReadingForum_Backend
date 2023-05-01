package capstone_project.ReadingForum_Backend.Dao;

import capstone_project.ReadingForum_Backend.Model.Module;

import java.util.List;

public interface SubgroupModuleDao {
    List<Module> selectBySubgroupId(int subgroupId);
    List<Module> selectAllModule();
    void insert(int subgroupId, int moduleId);
    void delete(int subgroupId, int moduleId);
}
