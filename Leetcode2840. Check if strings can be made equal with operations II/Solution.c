bool checkStrings(char* s1, char* s2) {
    int len = strlen(s1);
    if (len != strlen(s2)) {
        return false;
    }

    int counts[256] = {0};

    for (int i = 0; i < len; i++) {
        int offset = (i & 1) << 7;
        counts[offset + s1[i]]++;
        counts[offset + s2[i]]--;
    }

    for (int i = 0; i < 256; i++) {
        if (counts[i] != 0) {
            return false;
        }
    }

    return true;
}


// runtime - 0ms
bool checkStrings(char* s1, char* s2) {
    int n1 = strlen(s1);
    int n2 = strlen(s2);

    if (n1 != n2) return false;
    int s1_odd[256] = {0}, s1_even[256] = {0}, s2_odd[256] = {0}, s2_even[256] = {0};

    for (int i = 0; i < n1; i++) {
        if (i & 0x1) {
            unsigned char j = s1[i] - 'a';
            unsigned char k = s2[i] - 'a';
            s1_odd[j]++;
            s2_odd[k]++;
        } else {
            unsigned char j = s1[i] - 'a';
            unsigned char k = s2[i] - 'a';
            s1_even[j]++;
            s2_even[k]++;            
        }
    }

    for (int i = 0; i < 256; i++) {
        if (s1_odd[i] != s2_odd[i] || s1_even[i] != s2_even[i]) return false;
    }
    return true;
}
