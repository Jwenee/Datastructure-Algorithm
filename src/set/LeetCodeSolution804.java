/**
 * question: 804. 唯一摩尔斯密码词
 * linkedUrl: https://leetcode-cn.com/problems/unique-morse-code-words/
 */

package set;

import java.util.TreeSet;

public class LeetCodeSolution804 {

    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        TreeSet<String> treeSet = new TreeSet<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                sb.append(codes[word.charAt(i) - 'a']);
            }
            treeSet.add(sb.toString());
        }
        return treeSet.size();
    }
}
