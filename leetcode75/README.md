# LeetCode 75: Sort Colors

## Problem Statement

Given an array `nums` with `n` objects colored red, white, or blue, sort them **in-place** so that objects of the same color are adjacent, with the colors in the order red, white, and blue.  

We will use the integers `0`, `1`, and `2` to represent the color red, white, and blue respectively.  

You must solve this problem **without using the library's sort function**.  

### Example 1:
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

shell
Copy code

### Example 2:
Input: nums = [2,0,1]
Output: [0,1,2]

shell
Copy code

### Example 3:
Input: nums = [0]
Output: [0]

shell
Copy code

### Example 4:
Input: nums = [1]
Output: [1]

markdown
Copy code

### Constraints:
- `n == nums.length`
- `1 <= n <= 300`
- `nums[i]` is either `0`, `1`, or `2`

---

## Solution: Dutch National Flag Algorithm

**Time Complexity:** O(n)  
**Space Complexity:** O(1)

### Intuition
We need to sort an array of `0`s, `1`s, and `2`s without extra space and in a single pass.  
The **Dutch National Flag algorithm** solves this by dividing the array into three sections:  
- Left section: all `0`s  
- Middle section: all `1`s  
- Right section: all `2`s  

We use three pointers:
- `low`: boundary for `0`s  
- `mid`: current element under consideration  
- `high`: boundary for `2`s  

### Approach
1. Initialize three pointers:  
   - `low = 0`  
   - `mid = 0`  
   - `high = n - 1`  
2. Traverse the array while `mid <= high`:  
   - If `nums[mid] == 0`: swap with `low`, increment both `low` and `mid`  
   - If `nums[mid] == 1`: move `mid` forward  
   - If `nums[mid] == 2`: swap with `high`, decrement `high` (don’t increment `mid` because the swapped element needs checking)  
3. Continue until `mid > high`.  

This ensures all `0`s are at the beginning, `1`s in the middle, and `2`s at the end.

### Code
```java
class Solution {
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high);
                high--;
            }
        }
    }
}
```