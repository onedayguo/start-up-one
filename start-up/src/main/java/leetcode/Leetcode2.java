package leetcode;

import java.util.*;

public class Leetcode2 {
    //leetcode 30. Substring with Concatenation of All Words
    // s = "barfoothefoobarman", words = ["foo","bar"]   Output: [0,9]
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s.isEmpty() || words.length ==0 ) return ans;
        //wordsLen单词个数，wLen单个单词长度，sumLen多个单词总长度，sLen源字符串长度
        int wordsLen = words.length, wLen= words[0].length(),sumLen = wLen*wordsLen;
        int sLen = s.length();

        if (sLen < wordsLen*wLen) return ans ; //源字符串长度小于单词总长度
        Map<Integer,String> map = new HashMap<>();
        int repeat = wordsLen;
        for (int i = 0; i < wordsLen ; i++) { //存储  下标，单词
            if (map.containsValue(words[i])) map.put(repeat++,words[i]);
            else map.put(i,words[i]);
        }
        //剩余字符数要大于单词总长度
        for (int i = 0; i < sLen - sumLen +1; i++) {
            Map<Integer,String> tempMap = new HashMap<>() ;
            tempMap.putAll(map);
            int tempI= i;
            String tempWord = s.substring(tempI,tempI+wLen);
            while (tempMap.containsValue(tempWord) ){
                for (int j = 0; j < repeat ; j++) {
                    String mm = tempMap.get(j);
                    if (tempWord.equals(tempMap.get(j)) ) {
                        tempMap.remove(j);
                        j = repeat;
                    }
                }

                tempI += wLen;
                if (tempMap.isEmpty()) ans.add(i);
                if (tempI + wLen-1 < sLen) tempWord = s.substring(tempI,tempI+wLen);
                else break;
            }
        }
        return ans;
    }

    //leetcode 31 Next Permutation
    public static void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len == 0 || len == 1) return;
        boolean isDesc = true;
        for (int i = 0; i < len -1 ; i++) {
            if (nums[i] < nums[i+1]) isDesc = false;
        }
        if (isDesc) {
            Arrays.sort(nums);return;
        }

        for (int i = len - 2; i >=0 ; i--) {
            boolean isBreak = false;
            for (int j = len - 1; j > i ; j--) {
                if (nums[j] > nums[i]){
                    isBreak = true;
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    Arrays.sort(nums,i+1,len);
                    break;
                }
            }
            if(isBreak) break;
        }
    }

    //leetcode 32. Longest Valid Parentheses
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        stack.push(-1);
        for (int i = 0; i < s.length() ; i++) {
            if (s.charAt(i) == '(') stack.push(i);
            else {
                stack.pop();
                if (stack.isEmpty()) stack.push(i);
                else max = Math.max(max,i - stack.peek());
            }

        }
        return max;
    }

    //33. Search in Rotated Sorted Array
    public int search(int[] A, int target) {
        int lo = 0;
        int hi = A.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (A[mid] == target) {
                return mid;
            }
            if (A[lo] < A[mid]) { // in this case, left side CANNOT have inflection point, and is increasing.
                if (A[lo] <= target && target < A[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else if (A[lo] > A[mid]) {
                if (A[mid] < target && target <= A[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            } else { // since no duplicates in input. Only happens when array is size <=2, so (lo == mid).
                lo = mid + 1; // search right, since A[lo] was already compared to 'target'.
            }
        }
        return -1;
    }

    //34. Find First and Last Position of Element in Sorted Array
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            }
            else {
                lo = mid+1;
            }
        }

        return lo;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;

        return targetRange;
    }

    //35. Search Insert Position  Input: [1,3,5,6], 5 Output: 2  Input: [1,3,5,6], 2  output 1
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) return i;
        }
        return nums.length;
    }

    //36. Valid Sudoku
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            Set<Character> row = new HashSet<>();
            Set<Character> col = new HashSet<>();
            Set<Character> cube = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' && !row.add(board[i][j])) return false;
                if (board[j][i] != '.' && !col.add(board[j][i])) return false;

                int rowIndex = 3 * (i / 3);
                int colIndex = 3 * (i % 3);
                if (board[rowIndex + j/3][colIndex + j%3] != '.' && !cube.add(board[rowIndex + j/3][colIndex + j%3]))
                    return false;
            }

        }
        return true;
    }

    public boolean isValiSudoku(char[][] board){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' && !valid(board,i,j)) return false;
            }
        }
        return true;
    }
    public boolean valid(char[][] board,int i,int j){
        for (int k = 0; k < 9; k++) {
            if (k == i || k == j) continue;
            if (board[i][k] == board[i][j]) return false;
            if (board[k][j] == board[i][j]) return false;
        }
        for (int row = (i/3)*3; row < (i/3 +1)*3 ; row++) {
            for (int col = (j/3)*3; col <(j/3 +1)*3 ; col++) {
                if (row == i && col == j) continue;
                if (board[row][col] == board[i][j]) return false;
            }
        }
        return true;

    }

    //37. Sudoku Solver
    public void solveSudoku(char[][] board) {
        dfs(board,0);
    }
    private boolean dfs(char[][] board, int d) {
            if (d==81) return true; //found solution
            int i=d/9, j=d%9;//根据数字序号----》行号，列号
            if (board[i][j]!='.') return dfs(board,d+1);//如果是数字，则递归到下一个数字；如果是空，则接下来判断可填入数字

            boolean[] flag=new boolean[10];//0-9，用于记录数字是否使用 flag[1]记录数字1是否已使用，true为未使用，false为已使用
            validate(board,i,j,flag);//验证board[i][j]可以填充数字哪些数字，flag中为false的为已使用的数字
            for (int k=1; k<=9; k++) {  //遍历数字1-9
                if (flag[k]) { //如果flag[k]为true代表，数字K与行列九宫格内数字不重复，即可填入
                    board[i][j]=(char)('0'+k); //尝试插入
                    if (dfs(board,d+1)) return true;  //判断下一个空格填入数字
                }
            }
            board[i][j]='.'; //下一个失败，则把当前的还原回 .
            return false;
        }
    private void validate(char[][] board, int i, int j, boolean[] flag) {
            Arrays.fill(flag,true); //将flag矩阵填充为true
            for (int k=0; k<9; k++) {
                if (board[i][k]!='.') flag[board[i][k]-'0']=false;  //判断行中已有数字
                if (board[k][j]!='.') flag[board[k][j]-'0']=false;  //判断列中已有数字
                int r=i/3*3+k/3;  //根据行号i和序号k映射到 九宫格的行号

                int c=j/3*3+k%3;  //根据列号j和序号k映射到 九宫格的列号
                if (board[r][c]!='.') flag[board[r][c]-'0']=false; //判断九宫格中已有数字
            }
        }

    //38. Count and Say  题意是n=1时输出字符串1；n=2时，数上次字符串中的数值个数，因为上次字符串有1个1，
    // 所以输出11；n=3时，由于上次字符是11，有2个1，所以输出21；n=4时，由于上次字符串是21，有1个2和1个1，所以输出1211。依次类推
    public static String countAndSay(int n) {
        if (n == 1) return "1";
        if (n == 2) return "11";
        else
        {
            String pre = countAndSay(n-1);
            int len = pre.length();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < len ; i++) {
                if (i == len-1 && pre.charAt(len-2) != pre.charAt(len-1)) stringBuilder.append(1).append(pre.charAt(len-1));
                for (int j = i+1; j < len; j++) {
                    if (pre.charAt(i) != pre.charAt(j)) {
                        stringBuilder.append(j-i).append(pre.charAt(i));
                        i = j;
                    }
                    if (pre.charAt(i) == pre.charAt(j) && j == len-1 ) stringBuilder.append(j-i+1).append(pre.charAt(i));
                }
            }
            return stringBuilder.toString();
        }

    }

    //39. Combination Sum Input: candidates = [2,3,6,7], target = 7, [ [7], [2,2,3] ]
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return new ArrayList<>();
        return helper(candidates, target, 0);
    }
    private List<List<Integer>> helper(int[] candidates, int target, int index) {
        List<List<Integer>> res = new ArrayList<>();
        if (target < 0) return res;
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] == target) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(candidates[i]);
                res.add(tmp);
            } else if (candidates[i] < target) {
                // Recursively find the combinations for target - candidate[i].
                // Also, restrict start index from i will help remove duplicates.
                List<List<Integer>> tmp = helper(candidates, target - candidates[i], i);
                if (tmp.size() > 0) { // 如果tem中有满足条件的 List<Integer>
                    for (List<Integer> vec : tmp) vec.add(candidates[i]);//值candidates[i]，target：target - candidates[i]
                    res.addAll(tmp);
                }
            }
        }
        return res;
    }

    //40.Combination Sum II
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);//排序，快速排序 O(nlogn)
        return helper2(candidates, target, 0);
    }
    List<List<Integer>> helper2(int []candidates, int target, int start){
        List<List<Integer>> ret = new ArrayList<>();//结果
        if(target == 0) ret.add(new ArrayList<Integer>());//如果目标值为0，则返回空
        for(int i=start; i<candidates.length && candidates[i] <= target; i++){//从传入下标开始，满足下标不越界，下标存的值小于等于目标值
            if(i != start && candidates[i] == candidates[i-1])//如果后值和前值一样没必要再进一步判断，直接跳过
                continue;
            List<List<Integer>> temp = helper(candidates, target - candidates[i], i+1);//递归，同时更新目标值为 原目标值减去当前下标值，缩小搜索返回
            for(List<Integer> lst: temp){
                lst.add(0, candidates[i]);//添加适合的数，每次往list的第一个位置插，其他后移
                ret.add(lst);
            }
        }
        return ret;
    }

    //41. First Missing Positive Input: [1,2,0] Output: 3  Input: [3,4,-1,1] Output: 2 Input: [7,8,9,11,12] Output: 1
    public int firstMissingPositive(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            while( nums[i] > 0 && nums[i] <= nums.length ) {
                int temp = nums[nums[i] - 1];
                if( temp == nums[i] ) // If nums[nums[i] - 1] equals nums[i], then we don't need to exchange them
                    break;
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for(int i = 0; i < nums.length; i++)
            if( nums[i] != i + 1 )
                return i + 1;
        return nums.length + 1;


    }

    //42. Trapping Rain Water  Input: [0,1,0,2,1,0,1,3,2,1,2,1]  Output: 6
    public int trap(int[] heights) {
        if(heights == null || heights.length == 0) return 0; //数组为空，返回0
        Deque<Integer> deque = new LinkedList<>(); //链表实现的队列，先进先出
        Deque<Integer> deque1 = new ArrayDeque<>(); //数组实现的队列，先进先出

        int i = 0, max = 0;
        while(i < heights.length) {
            if(deque.isEmpty() || deque.peekFirst() > heights[i]) { //队列为空 或者 队列中第一个数 大于 当前数值，则入队。即数组中后一个数小于起始点数值
                deque.addLast(heights[i++]); //入队
            } else {//当前数值 大于或等于 起始点数值
                int front = deque.pollFirst();//获取队列第一个值，并移除
                while(!deque.isEmpty()) {
                    max += front - deque.poll();//将起始点与两个大数之间的小数的差都加起来，表示可装的水量
                }
                deque.addLast(heights[i++]);//5,4,2,3,6，7,9,3   将6入队
            }
        }
        // Can make this loop cleaner.
        // Please suggest if we can do better here
        if(!deque.isEmpty()) {
            int rightMax = deque.pollLast();
            while(!deque.isEmpty()) {
                int curr = deque.pollLast();
                max += Math.max(0, rightMax - curr);
                rightMax = Math.max(rightMax, curr);
            }
        }

        return max;
    }



}



