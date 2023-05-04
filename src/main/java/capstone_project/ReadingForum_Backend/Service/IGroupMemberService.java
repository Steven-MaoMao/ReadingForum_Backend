package capstone_project.ReadingForum_Backend.Service;

import capstone_project.ReadingForum_Backend.Model.GroupMember;

import java.io.IOException;

public interface IGroupMemberService {
    GroupMember selectGroupMember(int groupId, int userId) throws IOException;
    boolean joinGroup(int groupId, int userId) throws IOException;
    boolean setGroupManager(int groupId, int userId) throws IOException;
    boolean dismissGroupManager(int groupId, int userId) throws IOException;
    boolean quitGroup(int groupId, int userId) throws IOException;
}
