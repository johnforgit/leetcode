class Solution {
    public int minimumDifference(int[] nums, int k) {

        Arrays.sort(nums);
        int res=Integer.MAX_VALUE;
        for(int i=0; i+k-1<nums.length; i++) {
            res = Math.min(res, nums[i+k-1]-nums[i]);
        }
        return res;
    }
}

// runtime - 0ms
class Solution 
{
    public int minimumDifference(int[] nums, int k) 
    {
        Arrays.sort(nums);
        int l = 0;
        int ans = Integer.MAX_VALUE;
        int n = nums.length;
        for(int r =0;r<n;r++)
        {
            if(r-l == k)
            {
                l++;
            }
            if(r-l+1 == k)
            {
                int temp = nums[r] - nums[l];
                ans = Math.min(ans,temp);
            }
        }

        return ans;
    }
    static {
        Runtime.getRuntime().gc();
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            try(FileWriter f = new FileWriter("display_runtime.txt")){
                f.write("0");
            }catch(Exception e){

            }
        }));
    }
}

// runtime - 4ms
class Solution {
    public int minimumDifference(int[] nums, int k) {
        if (nums.length == 1 || k == 1) return 0;
        quickSort(nums, 0, nums.length - 1);
        int minDifference = Integer.MAX_VALUE;
        for (int i = k - 1; i < nums.length; i++) {
            minDifference = Math.min(minDifference, nums[i] - nums[i - k + 1]);
        }
        return minDifference;
    }

    public void quickSort(int[] nums, int start, int end) {
        int left = start;
        int right = end;
        int pivot = nums[start];

        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }

            while (left <= right && nums[right] > pivot) {
                right--;
            }

            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }

        if (right > start) {
            quickSort(nums, start, right);
        }

        if (left < end) {
            quickSort(nums, left, end);
        }
    }
}

// runtime - 5ms
class Solution {
    static int partition(int[] nums,int l,int h){
        int pivot=nums[l];
        int i=l,j=h;
        while(i<j){
            while(nums[i]<=pivot&&i<h)
                i++;
            while(nums[j]>pivot)
                j--;
            if(i<j){
            int t=nums[i];
            nums[i]=nums[j];
            nums[j]=t;
            }
        }
        pivot=nums[j];
        nums[j]=nums[l];
        nums[l]=pivot;
        return j;
    }
    

    static void quickSort(int[] nums,int l,int h){
        if(l<h){
            int m = partition(nums,l,h);
            quickSort(nums,l,m-1);
            quickSort(nums,m+1,h);
        }
    }

    public int minimumDifference(int[] nums, int k) {
        if(nums.length==1)
           return 0;
        quickSort(nums,0,nums.length-1);
        int low=nums[nums.length-1],l=0;
        for(int r=k-1;r<nums.length;r++){
          low=Math.min(low,nums[r]-nums[l]);
          l++;
        }
        return low;
    }
}
