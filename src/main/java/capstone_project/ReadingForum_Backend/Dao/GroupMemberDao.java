package capstone_project.ReadingForum_Backend.Dao;

import capstone_project.ReadingForum_Backend.Model.GroupMember;

public interface GroupMemberDao {
    GroupMember selectGroupMember(int groupId, int userId);
    void joinGroup(int groupId, int userId);
    void setGroupManager(int groupId, int userId);
    void dismissGroupManager(int groupId, int userId);
    void quitGroup(int groupId, int userId);
}
