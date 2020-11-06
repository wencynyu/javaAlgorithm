package top.yuwenxin.leetcode.backtrace.string;

import java.util.ArrayList;
import java.util.List;

public class WordSplit {

    /**
     * 无法通过
     */
    List<String> res = new ArrayList<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        backtrace(s, 0, wordDict, new StringBuilder(), new StringBuilder());
        return res;
    }

    private void backtrace(String s, int pos, List<String> dict, StringBuilder tmp, StringBuilder word) {
        if (dict.contains(word.toString())){
            tmp.append(word);
            tmp.append(' ');
        }

        if (pos == s.length()){
            int spaceNum = 0;
            for (int i = 0; i < tmp.length(); i++) {
                if (tmp.charAt(i) == ' ') spaceNum++;
            }

            if (tmp.length() - spaceNum == s.length()){
                res.add(new String(tmp));
            }
            tmp.delete(0, tmp.length() - 1);
            return;
        }

        for (int i = pos; i < s.length(); i++) {
            word.append(s.charAt(i));
            backtrace(s, i + 1, dict, tmp, word);
            word.deleteCharAt(tmp.length() - 1);
        }
    }
}
