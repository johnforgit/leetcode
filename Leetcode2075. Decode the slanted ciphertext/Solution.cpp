class Solution {
public:
    string decodeCiphertext(string encodedText, int rows) {
        int n = encodedText.size();
        if(rows == 1)
            return encodedText;
        
        int cols = n/rows;
        string res;
        res.reserve(n);

        for(int c=0; c<cols; ++c) {
            int r=0, j=c;
            while(r < rows && j<cols) {
                res += encodedText[r*cols + j];
                ++r;
                ++j;
            }
        }

        while(!res.empty() && res.back() == ' ') {
            res.pop_back();
        }
        return res;
    }
};


// runtime - 4ms
#pragma GCC optimize("Ofast,unroll-loops,inline")
#include <string>

static const int speedup = []() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    return 0;
}();

class Solution {
public:
    std::string decodeCiphertext(std::string& encodedText, int rows) {
        if (rows == 1) return encodedText;
        
        const int L = encodedText.length();
        if (L == 0) return "";
        
        const int C = L / rows;
        const char* s = encodedText.data();
        
        // Максимальная длина расшифрованного текста
        std::string res(C, ' '); 
        // Но реально символов может быть больше из-за диагоналей, 
        // однако по условию originalText вписывается в сетку.
        // Правильный расчет длины:
        std::string out;
        out.reserve(C); 

        // Идем по стартовым позициям в первой строке
        for (int j = 0; j < C; ++j) {
            for (int r = 0; r < rows; ++r) {
                int c = j + r;
                if (c >= C) break;
                out += s[r * C + c];
            }
        }

        // Мгновенная обрезка хвоста через поиск последнего символа
        size_t last = out.find_last_not_of(' ');
        if (last == std::string::npos) return "";
        out.erase(last + 1);

        return out;
    }
};


// runtime - 7ms
class Solution {
public:
    string decodeCiphertext(string encoded, int rows) {
        int n = encoded.length();
        int cols = n / rows;

        string original;
        original.reserve(n);

        int last_chr = -1;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (i + j == cols) {
                    original.resize(last_chr + 1); 
                    return original;
                }
                int idx = j * cols + i + j;
                if (encoded[idx] != ' ') last_chr = original.length();
                original.push_back(encoded[idx]);
            }
        }
        original.resize(last_chr + 1);
        return original;
    }
};



// runtime - 8ms
class Solution {
public:
    string decodeCiphertext(string encodedText, int rows) {
        int cols = encodedText.length()/rows;
        string ans = "";
        int i = 0, j = 0, turn = 0;
        while(i < rows && j < cols){
            ans += encodedText[i*cols+j];
            if (i == 0 && j == cols-1)
                break;
            i++,j++;
            if (i == rows){
                i = 0;
                j = ++turn;
            }
        }
        while(ans.length() && ans.back() == ' ')
            ans.pop_back();
        return ans;
    }
};


// runtime - 10ms
class Solution {
public:
    string decodeCiphertext(string encodedText, int rows) {
        int n = size(encodedText), m = n / rows, l = 0;
        if(n <= 1) return encodedText;
        for(char c : encodedText){
            if(c != ' ') l++;
        }
        
        char o[1000000];
        int k = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<rows;j++){
                char c = encodedText[j * m + ((i + j) % m)];
                o[k++] = c;
                if(c != ' '){
                    l--;
                    if(l == 0) return string(o, k);
                }
            }
        }

        return "";
    }
};


// runtime - 11ms
class Solution {
public:
    void find_last(const string& text, int cols, int rows, int& li, int& lj) {
        for (int k = cols - 1; k >= 0; --k) {
            int d = min(rows - 1, cols - k - 1);
            int mx_i = d;
            int mx_j = k + d;
            for (int idx = 0; idx <= d; ++idx) {
                int i = mx_i - idx;
                int j = mx_j - idx;
                if (text[i * cols + j] != ' ') {
                    li = i;
                    lj = j;
                    return;
                }
            }
        }
    }
    string decodeCiphertext(string text, int rows) {
        if (text.size() == 0) return ""; 
        int n = text.size();
        int cols = n / rows;
        string result;
        int i = 0;
        int j = 0;
        int li = 0;
        int lj = 0;
        int sj = cols - 1;
        sj = 0;
        i = 0; 
        j = 0;
        find_last(text, cols, rows, li, lj);
        while (i != li || j != lj) {
            result += text[i * cols + j];
            ++i;
            ++j;
            if (i == rows || j == cols) {
                i = 0; 
                j = ++sj;
            }
        }
        result += text[i * cols + j];
        return result;
    }
};
