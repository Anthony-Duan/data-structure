package SetBasicsAndBSTSet;

import java.util.TreeSet;

/**
 * @ Description: Leetcode 804 问题
 * @ Date: Created in 21:25 20/07/2018
 * @ Author: Anthony_Duan
 */
public class Solution {

    public int uniqueMorseRepresentations(String[] words) {

        String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        TreeSet<String> set = new TreeSet<>();

        for (String word :
                words) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                res.append(codes[word.charAt(i) - 'a']);
            }
            set.add(res.toString());
        }
        return set.size();
    }
}
