import java.util.Arrays;

class Solution {
    public int maxTwoEvents(int[][] events) {     
        /*
        List<int[]> times = new ArrayList<>();
        //convert events into start and end times with their values
        //(start, 1, value) -> start time of event
        //(end+1, 0, value) -> end time of event
        for(int[] event:events) {
            // 1 denotes start time
            times.add(new int[]{event[0], 1, event[2] });

            // 0 denotes end time
            times.add(new int[]{event[1]+1, 0, event[2] });
        }

        times.sort((a, b) -> a[0]==b[0] 
                    ? Integer.compare(a[1], b[1])
                    : Integer.compare(a[0], b[0])
        );
        
        int ans=0, maxValue=0;
        // process the sorted times
        for(int[] time:times) {
            if(time[1]==1) {
                // start time: Calculate the max sum
                ans=Math.max(ans, time[2]+maxValue);
            } else {
                maxValue = Math.max(maxValue, time[2]);
            }
        }
        return ans;
        */

        // solution using min heap
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(
            Comparator.comparingInt(Pair::getKey)
        );

        // Sort the array in ascending order
        Arrays.sort(events, (a, b) -> a[0]-b[0]);
        int maxVal=0, maxSum=0;

        for(int[] event:events) {
            while(!pq.isEmpty() && pq.peek().getKey()<event[0]) {
                maxVal = Math.max(maxVal, pq.peek().getValue());
                pq.poll();
            }
            maxSum = Math.max(maxSum, maxVal + event[2]);
            pq.add(new Pair<>(event[1], event[2]));
        }
        return maxSum;
    }
}
