# LeetCode 53: Maximum Subarray

## Problem Statement

Given an integer array `nums`, find the **contiguous subarray** (containing at least one number) which has the **largest sum**, and return its sum.  

### Example 1:
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The contiguous subarray [4,-1,2,1] has the largest sum = 6.

shell
Copy code

### Example 2:
Input: nums = [1]
Output: 1

shell
Copy code

### Example 3:
Input: nums = [5,4,-1,7,8]
Output: 23

markdown
Copy code

### Constraints:
- `1 <= nums.length <= 10^5`
- `-10^4 <= nums[i] <= 10^4`

---

## Solution: Kadane's Algorithm

**Time Complexity:** O(n)  
**Space Complexity:** O(1)

### Intuition
We want to find the maximum sum of a contiguous subarray.  
The **Kadane's Algorithm** solves this by keeping track of:
- `currentSum`: sum of the current subarray
- `maxSum`: maximum sum found so far  

If `currentSum` becomes negative, it cannot contribute positively to a future subarray, so we reset it to 0.

### Approach
1. Initialize `currentSum = 0` and `maxSum = Integer.MIN_VALUE`  
2. Iterate through each element in the array:  
   - Add the current element to `currentSum`  
   - Update `maxSum = max(maxSum, currentSum)`  
   - If `currentSum < 0`, reset `currentSum = 0`  
3. Return `maxSum` after the loop

### Code
```java
class Solution {
    public int maxSubArray(int[] nums) {
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            maxSum = Math.max(currentSum, maxSum);
            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        return maxSum;
    }
}
```