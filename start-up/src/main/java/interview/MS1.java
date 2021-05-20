package interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: kami
 * @Date: 2021/5/20 10:01
 * @Version: 1.0.0
 */
public class MS1 {
    static List<List<String>> result = new ArrayList<>();

    // 成语接龙
    private static List<List<String>> getAllPossibleWords(List<String> strs, String startWord) {
        if (strs == null || strs.size() == 0 || startWord == null) {
            return new ArrayList<>();
        }
        // key:首字，value:成语集合
        Map<Character, List<String>> wordMap = new HashMap<>();
        for (String word : strs) {
            Character firstWord = word.charAt(0);
            List<String> exist = wordMap.getOrDefault(firstWord, new ArrayList<>());
            exist.add(word);
            wordMap.put(firstWord, exist);
        }

        List<String> curRes = new ArrayList<>();
        curRes.add(startWord);
        dfWords(curRes, wordMap, startWord.charAt(3));
        return result;
    }

    private static void dfWords(List<String> curRes, Map<Character, List<String>> wordMap, Character lastWord) {
        List<String> exist = wordMap.getOrDefault(lastWord, null);
        if (exist != null) {
            boolean allUsed = true;
            for (String word : exist) {
                if (!curRes.contains(word)) {
                    allUsed = false;
                    curRes.add(word);
                    dfWords(curRes, wordMap, word.charAt(3));
                    curRes.remove(curRes.size() - 1);
                }
            }
            if (allUsed){
                result.add(new ArrayList<>(curRes));
            }
        }
    }

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("ABCD");
        words.add("ABCE");
        words.add("ABCG");
        words.add("DBCD");
        words.add("EBCD");
        words.add("DBCC");
        words.add("CBCD");
        words.add("CBCM");
        words.add("MBCD");
        List<List<String>> result = getAllPossibleWords(words, "ABCD");
        for (List<String> word : result) {
            for (String str : word) {
                System.out.println(str);
            }
            System.out.println("********************************");
        }
    }
}
