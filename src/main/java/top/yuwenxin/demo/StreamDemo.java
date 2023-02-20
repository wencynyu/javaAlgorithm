package top.yuwenxin.demo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        String[] ids = "216579,213087,216489,212770,216437,216521,216583,216457,212762,212486,212935,212757,212933,212760,212500,212792,212769,212487,212803,216505,212763,212985,212742,212768,213061,212494,212801,213093,212782,212467,216537,212772,212473,212779,216491,216397,216467,212761,216519,213065,212478,212941,216533,212749,216501,212753,216551,216561,212943,212522,213063,212784,212493,213047,216477,216543,213091,216415,212472,212977,212751,212512,216413,212759,216439,216399,212750,216417,213053,212479,216529,212791,216523,216465,216555,212937,216443,203955,203920,203900,203981,203871,203964,203884,203960,203902,203941,203979,203852,203901,203978,203946".split(",");
        List<String> stringList = Arrays.asList(ids);
        // groupingBy方法：传入分组方法，后续还可以对downStream继续处理，这里传入count方法做数量的统计
        stringList.stream()
                .filter(s -> s.equalsIgnoreCase("216579"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).forEach((k, v) -> {
                    System.out.println("key = " + k + ", v = " + v);
                });
    }
}
