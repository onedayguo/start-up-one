package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
public class CharGrroup {
    private String groupName;

    public CharGrroup(String groupName, int groupId, ArrayList<User> menmbers) {
        this.groupName = groupName;
        this.groupId = groupId;
        this.menmbers = menmbers;
        Collections.sort(menmbers);
    }

    private int groupId;
    private ArrayList<User> menmbers;

    public String getGroupName() {
        return groupName;
    }

    public int getGroupId() {
        return groupId;
    }
    //get user
    public User getUser(String tel){
        if (tel == null) return null;
        if (menmbers == null) return null;
        int len = menmbers.size();

        int left=0,right = len-1,mid = (left+right) >> 2;
        while (left <= right){
//            if (menmbers.get(mid) > )
        }
//        Iterator<User> iterator = menmbers.listIterator();
//        while (iterator.hasNext()){
//            User temp = iterator.next();
//            if (temp.getTel().intern() == tel.intern()){
//                return temp;
//            }
//        }
        return null;
    }
    public void addUser(User u){
        if (u == null || User.class != u.getClass()) {
            return;
        }
        menmbers.add(u);
    }

    //传入多个chatGroups, 每个和一个号码（代表一个人）
    public static List<User> getFriends(List<CharGrroup> groups,String tel){
        return null;
    }
}
