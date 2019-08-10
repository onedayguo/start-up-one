package leetcode;

import java.util.*;

public class LeetCode4 {
    //51.N-Queens The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
    private Set<Integer> col = new HashSet<>();
    private Set<Integer> diag1 = new HashSet<>();
    private Set<Integer> diag2 = new HashSet<>();

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<List<String>>();
        dfs(res,new ArrayList<String>(), 0, n);
        return res;
        //return res.size();
    }
    private void dfs(List<List<String>> res, List<String> list, int row, int n){
        if (row == n){
            res.add(new ArrayList<String>(list));
            return;
        }
        for (int i = 0; i < n; i++){
            if (col.contains(i) || diag1.contains(row + i) || diag2.contains(row - i)) continue;

            char[] charArray = new char[n];
            Arrays.fill(charArray, '.');
            charArray[i] = 'Q';
            String rowString = new String(charArray);

            list.add(rowString);
            col.add(i);
            diag1.add(row + i);
            diag2.add(row - i);

            dfs(res, list, row + 1, n);

            list.remove(list.size() - 1);
            col.remove(i);
            diag1.remove(row + i);
            diag2.remove(row - i);
        }
    }

    //52. N-Queens II
    public int solveNQueens1(int n) {
        List<List<String>> res = new ArrayList<>();
        dfs(res,new ArrayList<>(), 0, n);
        return res.size();
    }

    //53. Maximum Subarray Given an integer array nums, find the contiguous subarray (containing at least one number)
    // which has the largest sum and return its sum.
    public int maxSubArray(int[] nums) {
            int maxSum = nums[0], length = nums.length;
            for(int i = 1; i<length; i++) {
                if(nums[i-1]>0) nums[i] = nums[i-1]+nums[i];
                if(maxSum<nums[i]) maxSum = nums[i];
            }
            return maxSum;
    }

    //54. Spiral Matrix  Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if(matrix.length == 0 || matrix[0].length == 0) return res;

        int top = 0;
        int bottom = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;

        while(true){
            for(int i = left; i <= right; i++) res.add(matrix[top][i]);
            top++;
            if(left > right || top > bottom) break;

            for(int i = top; i <= bottom; i++) res.add(matrix[i][right]);
            right--;
            if(left > right || top > bottom) break;

            for(int i = right; i >= left; i--) res.add(matrix[bottom][i]);
            bottom--;
            if(left > right || top > bottom) break;

            for(int i = bottom; i >= top; i--) res.add(matrix[i][left]);
            left++;
            if(left > right || top > bottom) break;
        }

        return res;
    }

    //55. Jump Game   Given an array of non-negative integers, you are initially positioned at the first index of the array.
    //Each element in the array represents your maximum jump length at that position.
    //Determine if you are able to reach the last index.
    public boolean canJump(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            nums[i] = Math.max(nums[i],nums[i-1]-1);
        }
        int jump = 0;
        int index = 0;
        while (index < len-1){
            jump++;//步数+1
            index+=nums[index];//更新索引
            if (nums[index] == 0) return false;
        }
        return true;
    }

    //56. Merge Intervals  Given a collection of intervals, merge all overlapping intervals.
    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[0][0];
        List<List<Integer>> res = new ArrayList<>();
        int len = intervals.length;
        sortIntervals(intervals,len);
        List<Integer> fisrt = new ArrayList<>();
        fisrt.add(intervals[0][0]);
        fisrt.add(intervals[0][1]);
        res.add(fisrt);
        for (int i = 1; i < len; i++) {  //[2,2] [2,3]
            int lastIndex = res.size() - 1;
            //[2,5],[2,7] 或者[2,7] ,[2,5]
            if (intervals[i][0] == res.get(lastIndex).get(0)) {
                res.get(lastIndex).set(1,Math.max(intervals[i][1],res.get(lastIndex).get(1)));
                continue;
            }
            //[2,5],  [3,9]或者[3,4]或者[5,9]
            if (intervals[i][0] > res.get(lastIndex).get(0) && intervals[i][0] <= res.get(lastIndex).get(1)){
                res.get(lastIndex).set(1,Math.max(intervals[i][1],res.get(lastIndex).get(1)));
                continue;
            }

            //res [2,5]   interval [6,10]
            if (intervals[i][0] > res.get(lastIndex).get(1)){
                List<Integer> temp = new ArrayList<>();
                temp.add(intervals[i][0]);
                temp.add(intervals[i][1]);
                res.add(temp);
                continue;
            }
        }
        int[][] ans = new int[res.size()][2];
        Iterator<List<Integer>> iterator = res.listIterator();
        int i = 0;
        while (iterator.hasNext()){
            List<Integer> current = iterator.next();
            ans[i][0] = current.get(0);
            ans[i][1] = current.get(1);
            i++;
        }
        return ans;
    }
    //插入排序，给二维数组排序，按照每个维度的第一个值
    public static void sortIntervals(int[][] intervals,int len){
        for (int i = 1; i < len; i++) {
            // 记录要插入的数据
            int tmp0 = intervals[i][0];
            int tmp1 = intervals[i][1];
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp0 < intervals[j - 1][0]) {
                intervals[j][0] = intervals[j - 1][0];
                intervals[j][1] = intervals[j - 1][1];
                j--;
            }
            // 存在比其小的数，插入
            if (j != i) {
                intervals[j][0] = tmp0;
                intervals[j][1] = tmp1;
            }
        }
    }

    //57. Insert Interval  Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (newInterval.length == 0) {
            return intervals;
        }
        List<int[]> results = new ArrayList<>();
        int index = 0;
        for (int[] interval : intervals) {
            int[] result = new int[2];
            if (interval[1] < newInterval[0]) {
                results.add(interval);
                index++;
            } else if (interval[0] > newInterval[1]) {
                results.add(interval);
            } else {
                newInterval[0] = interval[0] < newInterval[0] ? interval[0] : newInterval[0];
                newInterval[1] = interval[1] > newInterval[1] ? interval[1] : newInterval[1];
            }
        }
        results.add(index, newInterval);
        int[][] finalResults = new int[results.size()][2];
        int i = 0;
        for (int[] result : results) {
            finalResults[i++] = result;
        }
        return finalResults;
    }

    //58. Length of Last Word Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
    // return the length of last word in the string. If the last word does not exist, return 0.
    public static int lengthOfLastWord(String s) {
        String newTrim = s.trim();
        if (newTrim == null || newTrim.length() == 0) return 0;
        int lastIndex = newTrim.length() - 1;
        int count = 0;
        while (lastIndex >=0 &&newTrim.charAt(lastIndex) != ' '){
            count++;
            lastIndex--;
        }
        return count;
    }

    //59. Spiral Matrix II Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        if (n == 0) return res;
        if (n == 1) {
            res[0][0] = 1;
            return res;
        }
        int top = 0;
        int bottom = n-1;
        int left = 0;
        int right = n-1;
        int value = 1;
        while(true){
            for(int i = left; i <= right; i++) res[top][i] = value++;
            top++;
            if(left > right || top > bottom) break;

            for(int i = top; i <= bottom; i++) res[i][right] = value++;
            right--;
            if(left > right || top > bottom) break;

            for(int i = right; i >= left; i--) res[bottom][i] = value++;
            bottom--;
            if(left > right || top > bottom) break;

            for(int i = bottom; i >= top; i--) res[i][left] = value++;
            left++;
            if(left > right || top > bottom) break;
        }

        return res;

    }

    //60. Permutation Sequence
    public static String getPermutation(int n, int k) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i+1;
        }
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list,new ArrayList<>(),nums);
        Iterator<Integer> iterator = list.get(k-1).listIterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()){
            stringBuilder.append(iterator.next().toString());
        }
        return stringBuilder.toString();
    }
    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    //61. Rotate List
    public ListNode rotateRight(ListNode head, int k) {
        if (head==null||head.next==null) return head;
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode fast=dummy,slow=dummy;

        int len;
        for (len=0;fast.next!=null;len++)//Get the total length
            fast=fast.next;

        for (int j=len-k%len;j>0;j--) //Get the i-n%i th node
            slow=slow.next;

        fast.next=dummy.next; //Do the rotation
        dummy.next=slow.next;
        slow.next=null;

        return dummy.next;

    }

    //62. Unique Paths
    public int uniquePaths(int m, int n) {
        if(m == 1 || n == 1)
            return 1;
        m--;
        n--;
        if(m < n) {              // Swap, so that m is the bigger number
            int temp = m;
            m = n;
            n = temp;
        }
        long res = 1;
        int j = 1;
        for(int i = m+1; i <= m+n; i++, j++){       // Instead of taking factorial, keep on multiply & divide
            res *= i;
            res /= j;
        }

        return (int)res;
    }

    //63. Unique Paths II
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;//行数
        int n = obstacleGrid[0].length;//列数
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                //如果遇到障碍，就把障碍清除，这样最终的结果加和0值就相当于没有加
                if(obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                }
                else {
                    //左上角起始点，设置值为1
                    if(i == 0 && j == 0) obstacleGrid[i][j] = 1;
                    //如果是第一行，后值设置与前值相同
                    else if(i == 0 && j > 0) obstacleGrid[i][j] = obstacleGrid[i][j-1];
                    //如果是第一列，后值设置与前值相同
                    else if(i > 0 && j == 0) obstacleGrid[i][j] = obstacleGrid[i-1][j];
                    //如果其他地方的值，则把其同行前值 与 同列前值 相加 赋给此处的值
                    else obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
                }
            }
        }
        //返回最后一个位置的值，其值 为 之前特定值的加和
        return obstacleGrid[m-1][n-1];
    }

}
