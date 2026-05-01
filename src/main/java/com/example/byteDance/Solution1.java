package com.example.byteDance;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution1 {
    public static void main(String[] args) {
       int[] nums = new int[]{2,7,54,23,45,8,9,59};
       int target = 17;
        int[] index = index(nums, target);
        Arrays.stream(index).forEach(System.out::println);
    }

    /**
     *  两束之和等于给定数
     * @param nums
     * @param target
     * @return
     */
    public static int[] index(int[] nums,int target){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i < nums.length;i++){
            int other = target - nums[i];
            if(map.containsKey(other)){
                return new int[]{i,map.get(other)};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
