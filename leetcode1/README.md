# LeetCode 1: Two Sum

## Problem Statement

Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

You may assume that each input would have **exactly one solution**, and you may not use the same element twice.

You can return the answer in any order.

### Example 1:
```
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
```

### Example 2:
```
Input: nums = [3,2,4], target = 6
Output: [1,2]
```

### Example 3:
```
Input: nums = [3,3], target = 6
Output: [0,1]
```

### Constraints:
- `2 <= nums.length <= 10^4`
- `-10^9 <= nums[i] <= 10^9`
- `-10^9 <= target <= 10^9`
- Only one valid answer exists.

---

## Solutions

### Solution 1: Brute Force Approach

**Time Complexity:** O(n²)  
**Space Complexity:** O(1)

#### Intuition
The most straightforward approach is to check every possible pair of numbers in the array to see if they sum up to the target. We can do this by using two nested loops.

#### Approach
1. Use two nested loops where the outer loop runs from index 0 to n-2
2. The inner loop runs from the next index (i+1) to n-1
3. For each pair (i, j), check if `nums[i] + nums[j] == target`
4. If found, return the indices `[i, j]`
5. If no solution is found after checking all pairs, return an empty array

#### Code
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{}; // No solution found
    }
}
```

---

### Solution 2: Two-Pass Hash Table

**Time Complexity:** O(n)  
**Space Complexity:** O(n)

#### Intuition
Instead of checking every pair, we can use a hash table to store the numbers and their indices. For each number, we can quickly look up if its complement (target - current number) exists in the hash table.

#### Approach
1. **First Pass:** Build a hash table that maps each number to its index
2. **Second Pass:** For each number, calculate its complement (target - current number)
3. Check if the complement exists in the hash table and ensure it's not the same element
4. If found, return the current index and the complement's index
5. If no solution is found, return an empty array

#### Code
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        int n = nums.length;

        // Build the hash table
        for (int i = 0; i < n; i++) {
            numMap.put(nums[i], i);
        }

        // Find the complement
        for (int i = 0; i < n; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement) && numMap.get(complement) != i) {
                return new int[]{i, numMap.get(complement)};
            }
        }

        return new int[]{}; // No solution found
    }
}
```

---

### Solution 3: One-Pass Hash Table (Optimal)

**Time Complexity:** O(n)  
**Space Complexity:** O(n)

#### Intuition
We can optimize the two-pass approach by doing everything in a single pass. As we iterate through the array, we can check if the complement of the current number exists in the hash table. If not, we add the current number to the hash table for future lookups.

#### Approach
1. Initialize an empty hash table
2. For each number in the array:
   - Calculate its complement (target - current number)
   - Check if the complement exists in the hash table
   - If found, return the complement's index and current index
   - If not found, add the current number and its index to the hash table
3. If no solution is found after the loop, return an empty array

#### Code
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement)) {
                return new int[]{numMap.get(complement), i};
            }
            numMap.put(nums[i], i);
        }

        return new int[]{}; // No solution found
    }
}
```

---

## Comparison Summary

| Approach | Time Complexity | Space Complexity | Notes |
|----------|----------------|------------------|-------|
| Brute Force | O(n²) | O(1) | Simple but inefficient for large inputs |
| Two-Pass Hash Table | O(n) | O(n) | Good balance of time and space |
| One-Pass Hash Table | O(n) | O(n) | **Optimal** - best time complexity with single pass |

## Key Insights

1. **Trade-off between time and space:** The brute force approach uses no extra space but has quadratic time complexity
2. **Hash table optimization:** Using a hash table reduces lookup time from O(n) to O(1) on average
3. **Single pass optimization:** We can build the hash table and search simultaneously, eliminating the need for a second pass
4. **Edge case handling:** All solutions handle the case where no valid pair exists by returning an empty array

The **one-pass hash table approach** is generally preferred as it provides the best time complexity while maintaining reasonable space usage.
