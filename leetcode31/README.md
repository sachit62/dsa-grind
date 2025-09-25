# Next Permutation - Leetcode 31

## Problem Statement

A permutation of an array of integers is an arrangement of its members into a sequence or linear order. For example, for `arr = [1,2,3]`, the following are all the permutations of arr: `[1,2,3]`, `[1,3,2]`, `[2,1,3]`, `[2,3,1]`, `[3,1,2]`, `[3,2,1]`.

The **next permutation** of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container.

If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

**Examples:**
- The next permutation of `arr = [1,2,3]` is `[1,3,2]`
- The next permutation of `arr = [2,3,1]` is `[3,1,2]`
- The next permutation of `arr = [3,2,1]` is `[1,2,3]` because `[3,2,1]` does not have a lexicographical larger rearrangement

**Task:** Given an array of integers `nums`, find the next permutation of `nums`. The replacement must be **in place** and use only **constant extra memory**.

## Examples

**Example 1:**
```
Input: nums = [1,2,3]
Output: [1,3,2]
```

**Example 2:**
```
Input: nums = [3,2,1]
Output: [1,2,3]
```

**Example 3:**
```
Input: nums = [1,1,5]
Output: [1,5,1]
```

## Constraints

- `1 <= nums.length <= 100`
- `0 <= nums[i] <= 100`

## Intuition

To find the next permutation, we need to understand the pattern of how permutations are ordered lexicographically:

1. **Lexicographical Order:** Permutations are ordered like words in a dictionary
2. **Next Greater:** We need to find the smallest permutation that is greater than the current one
3. **Pattern Recognition:** The next permutation is obtained by:
   - Finding the rightmost element that can be increased
   - Swapping it with the smallest element on its right that is greater than it
   - Reversing the suffix to get the smallest possible arrangement

## Approach

The algorithm follows these key steps:

1. **Find the Break Point:** Start from the right end and find the first element `nums[i]` such that `nums[i] < nums[i+1]`. This is the position where we can make a change to get a larger permutation.

2. **Find the Next Greater Element:** If a break point exists, find the smallest element on the right side of the break point that is greater than `nums[ind]`.

3. **Swap Elements:** Swap the break point element with the next greater element.

4. **Reverse the Suffix:** Reverse all elements after the break point to get the smallest possible arrangement of the remaining elements.

5. **Handle Edge Case:** If no break point is found (array is in descending order), reverse the entire array to get the smallest permutation.

## Algorithm

```
Algorithm: Next Permutation
Input: Array nums of length n
Output: Modified array nums (next permutation)

1. Initialize n = length of nums
2. Find break point:
   - Start from index n-2, go left
   - Find first i where nums[i] < nums[i+1]
   - Store this index as 'ind'
3. If no break point found (ind == -1):
   - Reverse entire array from 0 to n-1
   - Return
4. Find next greater element:
   - Start from index n-1, go left
   - Find first element > nums[ind]
   - Swap nums[ind] with this element
5. Reverse the suffix:
   - Reverse array from index ind+1 to n-1
6. Return modified array
```

## Code Solution

```java
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        
        // Step 1: Find the break point
        int ind = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                ind = i;
                break;
            }
        }
        
        // Step 2: If no break point, reverse the whole array
        if (ind == -1) {
            reverse(nums, 0, n - 1);
            return;
        }
        
        // Step 3: Find the next greater element to swap with nums[ind]
        for (int i = n - 1; i > ind; i--) {
            if (nums[i] > nums[ind]) {
                swap(nums, i, ind);
                break;
            }
        }
        
        // Step 4: Reverse the right half after the break point
        reverse(nums, ind + 1, n - 1);
    }
    
    // Helper function to reverse array from left to right
    private void reverse(int[] arr, int left, int right) {
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }
    
    // Helper function to swap two elements
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
```

## Time and Space Complexity

- **Time Complexity:** O(n) - We traverse the array at most twice
- **Space Complexity:** O(1) - We use only constant extra space as required

## Key Insights

1. **Break Point Concept:** The break point is where the array stops being in descending order from right to left
2. **Lexicographical Order:** Understanding that permutations follow dictionary-like ordering
3. **In-Place Modification:** The solution modifies the array without using extra space
4. **Edge Case Handling:** When the array is in descending order, the next permutation is the reverse (ascending order)

