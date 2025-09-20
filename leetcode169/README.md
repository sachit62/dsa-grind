# LeetCode 169: Majority Element

## Problem Statement

Given an array `nums` of size `n`, return the **majority element**.  

The **majority element** is the element that appears **more than ⌊n / 2⌋ times**. You may assume that the majority element always exists in the array.  

### Example 1:
Input: nums = [3,2,3]
Output: 3

shell
Copy code

### Example 2:
Input: nums = [2,2,1,1,1,2,2]
Output: 2

markdown
Copy code

### Constraints:
- `1 <= nums.length <= 5 * 10^4`
- `-10^9 <= nums[i] <= 10^9`
- The majority element always exists in the array

---

## Solution: Boyer-Moore Voting Algorithm

**Time Complexity:** O(n)  
**Space Complexity:** O(1)

### Intuition
We need to find the element that appears more than half the time in the array.  
The **Boyer-Moore Voting Algorithm** efficiently finds this element by maintaining a **candidate** and a **count**:
- When `count` is 0, choose the current element as the new candidate
- If the current element equals the candidate, increment `count`
- Otherwise, decrement `count`

By the end of the iteration, the candidate will be the majority element.

### Approach
1. Initialize `count = 0` and `element = nums[0]` (candidate)  
2. Iterate through each number in the array:  
   - If `count == 0`, set the current number as the new candidate  
   - If current number == candidate, increment `count`  
   - Else, decrement `count`  
3. Return the candidate element after the loop

### Code
```java
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int element = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                element = nums[i];
            }
            if (nums[i] == element) {
                count++;
            } else {
                count--;
            }
        }
        return element;
    }
}
```
### Comparison Summary
Approach	Time Complexity	Space Complexity	Notes
HashMap / Counting	O(n)	O(n)	Simple but uses extra space
Boyer-Moore Voting	O(n)	O(1)	Optimal - single pass, constant space


### Key Insights
The Boyer-Moore algorithm works because a majority element appears more than half the time.
By "cancelling out" different elements with the count, the majority element survives.
This approach is both time and space optimal.
It is preferable over using extra space (like HashMap) for large arrays.
