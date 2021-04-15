package leetcode.interest;

import java.util.List;

/**
 * @Description: TODO
 * @Author: kami
 * @Date: 2021/4/15 17:03
 * @Version: 1.0.0
 */
public interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    List<NestedInteger> getList();
}
