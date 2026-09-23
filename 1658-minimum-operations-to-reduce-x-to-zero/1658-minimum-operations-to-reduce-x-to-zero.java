class Solution {
    public int minOperations(int[] nums, int x) {

        int n = nums.length;

        
        int total = 0;

        for (int num : nums) {
            total += num;
        }

    
        int target = total - x;

    
        if (target == 0) {
            return n;
        }

        int left = 0;
        int sum = 0;
        int maxLen = -1;

        
        for (int right = 0; right < n; right++) {

            sum += nums[right];

    
            while (left <= right && sum > target) {
                sum -= nums[left];
                left++;
            }

            
            if (sum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

    
        if (maxLen == -1) {
            return -1;
        }


        return n - maxLen;
    }
}