package moveZeroes.problem;

class Solution2 {
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {

                /**
                 * 左指针左边均为非零数；
                 *
                 * 右指针左边直到左指针处均为零。
                 */
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }
    }

}

//作者：LeetCode-Solution
//        链接：https://leetcode.cn/problems/move-zeroes/solution/yi-dong-ling-by-leetcode-solution/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
