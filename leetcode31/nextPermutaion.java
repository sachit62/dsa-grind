class Solution {
    public void nextPermutation(int[] nums) {
        int idx = -1;
        int n = nums.length;
        for(int i=n-2;i>=0;i--){
            if(nums[i]<nums[i+1]){
                idx = i;
                break;
            }
        }
        if(idx==-1){
            reverse(nums,0,n-1);
            return;
        }
        for(int i=n-1;i>idx;i--){
            if(nums[i]>nums[idx]){
                swap(nums,idx,i);
                break;
            }
        }
        reverse(nums,idx+1,n-1);
    }

    public void reverse(int arr[], int left, int right){
        while(left<right){
            swap(arr,left,right);
            left++;
            right--;
        }
    }

    public void swap(int arr[], int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}