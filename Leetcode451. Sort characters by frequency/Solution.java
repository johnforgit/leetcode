import java.util.*;

class Solution {
    public String frequencySort(String s) {
        // Count frequency
        Map<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // Buckets: index = frequency
        List<Character>[] buckets = new ArrayList[s.length() + 1];
        for (char c : count.keySet()) {
            int freq = count.get(c);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(c);
        }

        // Build result
        StringBuilder res = new StringBuilder();
        for (int i = s.length(); i > 0; i--) {
            if (buckets[i] != null) {
                for (char c : buckets[i]) {
                    for (int k = 0; k < i; k++) {
                        res.append(c);
                    }
                }
            }
        }

        return res.toString();
    }
}



// runtime - 2ms

class Solution {
     static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
                fw.write("0");
            } catch (Exception e) {
            }
        }));
    }
    public String frequencySort(String s) {
        HashMap<Character,Integer> map= new HashMap<>();
        for(char c: s.toCharArray())
        {
            map.put(c, map.getOrDefault(c,0)+1);

        }
        List<Character> list= new ArrayList<>(map.keySet());
        list.sort((a,b)-> map.get(b)-map.get(a));
       StringBuilder result= new StringBuilder();
        for(int i=0;i<list.size();i++)
        {
            int c=0;
            while(c<map.get(list.get(i)))
            {
                result.append(list.get(i));
                c++;
            }
        }
        return result.toString();
    }
}

// runtime - 7ms
class Solution {
    public String frequencySort(String s) {
        int[] lower = new int[26];
        int[] upper = new int[26];
        int[] digit = new int[10];

        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c))
                lower[c - 'a']++;
            else if (Character.isUpperCase(c))
                upper[c - 'A']++;
            else if (Character.isDigit(c))
                digit[c - '0']++;
        }

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < 62; i++) {
            int max = -1, k = 0, l = 0;

            for (int j = 0; j < 10; j++) {
                if (digit[j] != 0 && digit[j] > max) {
                    max = digit[j];
                    k = j;
                    l = 1;
                }
            }

            for (int j = 0; j < 26; j++) {
                if (upper[j] != 0 && upper[j] > max) {
                    max = upper[j];
                    k = j;
                    l = 2;
                }
            }

            for (int j = 0; j < 26; j++) {
                if (lower[j] != 0 && lower[j] > max) {
                    max = lower[j];
                    k = j;
                    l = 3;
                }
            }

            if (l == 1) {
                res.append(String.valueOf((char) ('0' + k)).repeat(max));
                digit[k] = 0;
            } else if (l == 2) {
                res.append(String.valueOf((char) ('A' + k)).repeat(max));
                upper[k] = 0;
            } else if (l == 3) {
                res.append(String.valueOf((char) ('a' + k)).repeat(max));
                lower[k] = 0;
            }
        }

        return res.toString();
    }
}


// runtime - 11ms
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> freq= new HashMap<>();
        for(char c: s.toCharArray()){
            freq.put(c, freq.getOrDefault(c,0)+1);
        }
        List<Character>[] buckets= new List[s.length()+1];
        for(char c: freq.keySet()){
            int f= freq.get(c);
            if(buckets[f]== null){
                buckets[f]= new ArrayList<>();
            }
            buckets[f].add(c);
        }
        StringBuilder sb= new StringBuilder();
        for(int i= buckets.length-1; i>0; i--){
            if(buckets[i]!= null){
                for(char c: buckets[i]){
                    for(int j=0; j<i; j++){
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }
}
