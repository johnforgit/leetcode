import java.util.*;

class Solution {
    public int prefixConnected(String[] words, int k) {
        Arrays.sort(words);
        Map<String, List<String>> map = new HashMap<>();

        for (int i = 1; i < words.length; i++) {
            if (words[i].length() < k || words[i - 1].length() < k) {
                continue;
            }

            String prefix1 = words[i].substring(0, k);
            String prefix2 = words[i - 1].substring(0, k);

            if (prefix1.equals(prefix2)) {
                map.putIfAbsent(prefix1, new ArrayList<>());
                map.get(prefix1).add(words[i]);
                map.get(prefix1).add(words[i - 1]);
            }
        }

        return map.size();
    }
}


// runtime - 14ms
class Solution {
    public int prefixConnected(String[] words, int k) {
        Set set1 = new HashSet<>(), set2 = new HashSet<>();
        for (var x: words)
            if (x.length() >= k){
                var prfx = x.substring(0, k).hashCode();
                if (!set1.add(prfx))
                    set2.add(prfx);
            }
        return set2.size();
    }
}


// runtime - 18ms
class Solution {
    public int prefixConnected(String[] words, int k) {
        Map<String,Boolean> map=new HashMap<>();
        int ans=0;
        for(String x:words){
            if(x.length()<k) continue;
            String pre=x.substring(0,k);
            if(map.containsKey(pre)){
                if(map.get(pre)==false){map.put(pre,true);
                ans++;
                }
            }
            else{
                map.put(pre,false);
            }
        }
        return ans;
    }
}


// runtime - 22ms
class Solution {
    public int prefixConnected(String[] words, int k) {
         int res = 0;
        String[] array = words;
        Map<String , Integer> mapped = new HashMap<>();
        for(String word : array){
            if(word.length()< k)continue;
            String prefix = word.substring(0,k);
            mapped.put(prefix,mapped.getOrDefault(prefix , 0 ) + 1);
        }
        for(int f : mapped.values()){
            if(f >= 2){
                res++;
            }
        }
        return res;
    }
}


// runtime - 26ms
class Solution {
    class Node{
        int cnt;
        Node[] children;
        Node(){
            cnt = 0;
            children = new Node[26];
        }
    }
    public int prefixConnected(String[] words, int k) {
        int connectedGroup = 0;
        Node root = new Node();
        for(String word : words){
            Node temp = root;
            for(int i = 0;i<k && word.length() >= k;i++){
                char ch = word.charAt(i);
                if(temp.children[ch - 'a'] == null){
                    temp.children[ch - 'a'] = new Node();
                }
                temp = temp.children[ch - 'a'];
                temp.cnt++;
                if(temp.cnt == 2 && i == k - 1){
                    connectedGroup++;
                }
            }
        }
        return connectedGroup;
    }
}
