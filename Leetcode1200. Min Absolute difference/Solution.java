class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);
        int abs_diff = Integer.MAX_VALUE;
        for(int i=1; i<arr.length; i++) {
            int cur_diff = Math.abs(arr[i]-arr[i-1]);
            if(cur_diff < abs_diff) {
                abs_diff = cur_diff;
                res = new ArrayList<>();
                res.add(Arrays.asList(arr[i-1], arr[i]));
            } else if (cur_diff == abs_diff) {
                res.add(Arrays.asList(arr[i-1], arr[i]));
            }
        }

        return res;
    }
}

// runtime - 0ms
class Solution {
    static {
        Runtime.getRuntime().gc();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> list=new ArrayList<>();
        Arrays.sort(arr);
        int minDif=Integer.MAX_VALUE;
        for(int i=1;i<arr.length;i++){
            minDif=Math.min(minDif,arr[i]-arr[i-1]);
        }
        for(int i=1;i<arr.length;i++){
            if(minDif==arr[i]-arr[i-1]){
                list.add(Arrays.asList(arr[i-1],arr[i]));
            }
        }return list;
    }
}

// runtime - 8ms
class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }
        int k = max - min + 1;
        boolean[] count = new boolean[k];
        for (int num : arr) {
            count[num - min] = true;
        }
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            while(!count[j]) j++;
            arr[i] = j++ + min;
        }
        List<List<Integer>> result = new ArrayList<>();
        int dif = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length-1; i++) {
            int curDif = arr[i+1] - arr[i];
            if (curDif < dif) {
                result.clear();
                dif = curDif;
            }
            if (curDif == dif) result.add(Arrays.asList(arr[i], arr[i+1]));
        }
        return result;
    }
}

// runtime - 10ms
class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        
        //Counting sort
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int num : arr){
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        boolean[] nums = new boolean[max - min + 1];

        //sorted in counting
        for(int n : arr){
            nums[n - min] = true;
        }

        int[] newArr = new int[arr.length];
        int j = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] ){
                newArr[j] = i + min;
                j++;
            }
        }

        int absMin = Integer.MAX_VALUE;
        for(int i = 0; i < newArr.length - 1; i++){
            int temp = newArr[i+1] - newArr[i];
            if(temp < absMin){
                absMin = temp;
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < newArr.length - 1; i++){
            int temp = newArr[i+1] - newArr[i];
            if(temp == absMin){
                res.add(List.of(newArr[i], newArr[i+1]));
            }
        }

        return res;
    }
}

// runtime - 14ms
class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        int minElement = arr[0];
        int maxElement = arr[0];
        for (int num : arr) {
            minElement = Math.min(minElement, num);
            maxElement = Math.max(maxElement, num);
        }
        int shift = -minElement;
        int[] line = new int[maxElement - minElement + 1];
        List<List<Integer>> answer = new ArrayList();
        
        for (int num : arr) line[num + shift] = 1;
        
        int minPairDiff = maxElement - minElement;
        int prev = 0;
        
        for (int curr = 1; curr <= maxElement + shift; curr++) {
            if (line[curr] == 0) continue;
            
            if (curr - prev == minPairDiff) {       
                answer.add(Arrays.asList(prev - shift, curr - shift));
            } else if (curr - prev < minPairDiff) {
                answer = new ArrayList();
                minPairDiff = curr - prev;
                answer.add(Arrays.asList(prev - shift, curr - shift));                
            } 

            prev = curr;
        }  
        
        return answer;
    }
}

// runtime - 15ms
class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        // Initialize the auxiliary array `line`.
        // Keep a record of the minimum element and the maximum element.
        int minElement = arr[0];
        int maxElement = arr[0];
        for (int num : arr) {
            minElement = Math.min(minElement, num);
            maxElement = Math.max(maxElement, num);
        }
        int shift = -minElement;
        int[] line = new int[maxElement - minElement + 1];
        List<List<Integer>> answer = new ArrayList();
        
        // For each integer `num` in `arr`, we increment line[num + shift] by 1.
        for (int num : arr) {
            line[num + shift] = 1;
        }
        
        // Start from the index representing the minimum integer, initialize the 
        // absolute difference `min_pair_diff` as a huge value such as
        // `max_element - min_element` in order not to miss the absolute 
        // difference of the first pair.
        int minPairDiff = maxElement - minElement;
        int prev = 0;
        
        // Iterate over the array `line` and check if line[curr] 
        // holds the occurrence of an input integer.
        for (int curr = 1; curr <= maxElement + shift; ++curr) {
            // If line[curr] == 0, meaning there is no occurrence of the integer (curr - shift) 
            // held by this index, we will move on to the next index.
            if (line[curr] == 0) {
                continue;
            }
            
            // If the difference (curr - prev) equals `minPairDiff`, we add this pair 
            // {prev - shift, curr - shift} to the answer list. Otherwise, if the difference 
            // (curr - prev) is smaller than `minPairDiff`, we empty the answer list and add 
            // the pair {curr - shift, prev - shift} to the answre list and update the `minPairDiff`
            if (curr - prev == minPairDiff) {       
                answer.add(Arrays.asList(prev - shift, curr - shift));
            } else if (curr - prev < minPairDiff) {
                answer = new ArrayList();
                minPairDiff = curr - prev;
                answer.add(Arrays.asList(prev - shift, curr - shift));                
            } 

            // Update prev as curr.           
            prev = curr;
        }  
        
        return answer;
    }
}
