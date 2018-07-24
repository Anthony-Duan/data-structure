package SegmentTree;

/**
 * @ Description:LeetCode303 非线段树实现方法
 * @ Date: Created in 22:30 21/07/2018
 * @ Author: Anthony_Duan
 */
public class NumArray2 {
    private int[] sum; // sum[i]存储前i个元素和, sum[0] = 0

    // 即sum[i]存储nums[0...i-1]的和
    // sum(i, j) = sum[j + 1] - sum[i]
    public NumArray2(int[] nums) {

        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }
}
