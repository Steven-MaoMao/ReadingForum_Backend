package capstone_project.ReadingForum_Backend.Dao;

import capstone_project.ReadingForum_Backend.Model.Module;

import java.util.List;

public interface SubgroupModuleDao {
    List<Module> selectBySubgroupId(int subgroupId);
    List<Module> selectAllModule();
    void insert(int subgroupId, int moduleId, String name);
    void update(String name, int id);
    void delete(String name);
}
