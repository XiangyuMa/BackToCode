package com.example.byteDance;

public class Solution4 {
    public static void main(String[] args) {
        int[] nums = new int[]{2,3,-48,-1,45,1};
        Solution4 solution4 = new Solution4();
        System.out.println(solution4.maxSubArray2(nums));
    }
    public int maxSubArray(int[] nums) {
        // 动态规划：dp[i] 表示以 nums[i] 结尾的最大子数组和
        int dp = nums[0];      // 当前以 i 结尾的最大和
        int maxSum = nums[0];  // 全局最大和

        for (int i = 1; i < nums.length; i++) {
            // 要么自己单独成一段，要么加入前面的子数组
            dp = Math.max(nums[i], dp + nums[i]);
            maxSum = Math.max(maxSum, dp);
        }
        return maxSum;
    }
    public int maxSubArray2(int[] nums) {
        // 动态规划：dp[i] 表示以 nums[i] 结尾的最大子数组和
        int[] dp = new int[nums.length];
        if(nums.length >= 1){
            dp[0] = nums[0];
        }
        int maxSum = nums[0];  // 全局最大和
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i-1] + nums[i]);
        }
        for (int j : dp) {
            maxSum = Math.max(maxSum, j);
        }
        return maxSum;
    }
}
