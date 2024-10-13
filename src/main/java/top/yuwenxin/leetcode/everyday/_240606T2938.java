package top.yuwenxin.leetcode.everyday;

import java.util.ArrayList;
import java.util.List;

public class _240606T2938 {
    public long minimumSteps(String s) {
        List<Integer> blackPoses = new ArrayList<>();
        List<Integer> whitePoses = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                blackPoses.add(i);
            }else {
                whitePoses.add(i);
            }
        }

        int whiteNum = whitePoses.size();

        int needTransNum = 0;
        for (int i = 0; i < whiteNum; i++) {
            if (s.charAt(i) != '0') {
                needTransNum++;
            }
        }

        long res = 0;
        int whiteRgt = whiteNum - 1;
        int blackLft = 0;
        for (int i = 0; i < needTransNum; i++) {
            int dist = whitePoses.get(whiteRgt--) - blackPoses.get(blackLft++);
            res += dist;
        }
        return res;
    }
}
