class Solution {
public:
    int repeatedStringMatch(string a, string b) {
        int ans = 1;
        int num = b.length() / a.length();
        string start = a;

        if (a.find(b) != string::npos)
            return 1;

        for (int i = 0; i < num + 1; i++) {
            if (start.find(b) == string::npos) {
                start += a;
                ans++;
            }
            if (start.find(b) != string::npos)
                return ans;
        }
        return -1;
    }
};


// runtime - 0 ms
class Solution {
    bool rabinKarp(string str, string pattern){
        int n=str.length();
        int m=pattern.length();

        if(m>n) return false;

        long long int mod=1e9+7;
        long long int strCode=0;
        long long int patternCode=0;
        long long int power=1;
        long long int base=31;

        for(int i=0; i<m-1; i++){
            power=(power*base)%mod;
        }

        for(int i=0 ;i<m; i++){
            strCode=(strCode*base+(str[i]-'a'+1))%mod;
            patternCode=(patternCode*base+(pattern[i]-'a'+1))%mod;
        }

        for(int i=0; i<=n-m; i++){
            if(strCode==patternCode){
                if(str.substr(i,m)==pattern) return true;
            }
            if(i<n-m){
                strCode=(strCode-(str[i]-'a'+1)*power)%mod;
                if(strCode<0) strCode+=mod;
                strCode=(strCode*base+(str[i+m]-'a'+1))%mod;
            }
            
        }
        return false;
    }
public:
    int repeatedStringMatch(string a, string b) {
        int n=a.length();
        int m=b.length();
        int cnt=1;
        string orig=a;
        while(a.length()<m){
            a+=orig;
            cnt++;
        }
        if(rabinKarp(a,b)) return cnt;
        a+=orig;
        cnt++;
        if(rabinKarp(a,b)) return cnt;
        return -1;
    }
};
