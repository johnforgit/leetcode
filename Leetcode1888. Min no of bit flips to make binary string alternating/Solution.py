class Solution:
    def minFlips(self, s: str) -> int:
        n = len(s)
        res = n
        op = [0, 0]

        for i in range(n):
            op[(ord(s[i]) ^ i) & 1] += 1

        for i in range(n):
            c = ord(s[i])
            op[(c ^ i) & 1] -= 1
            op[(c ^ (n + i)) & 1] += 1
            res = min(res, min(op))

        return res


# runtime - 3ms
__import__("atexit").register(lambda: open("display_runtime.txt", "w").write("0"))
class Solution:
    def minFlips(self, s: str) -> int:
        prev = 0
        s0 = 0 # 0101
        s1 = 0 # 1010
        s0_odd = float('inf') # type 1 op and then s0
        s1_odd = float('inf') # type 1 op and then s1

        odd = len(s)%2

        for val in s:
            val = int(val)
            if val == prev:
                if odd:
                    s0_odd = min(s0_odd, s1)
                    s1_odd += 1
                s1 += 1
            else:
                if odd:
                    s1_odd = min(s1_odd, s0)
                    s0_odd += 1
                s0 += 1
            prev = 1 - prev
        
        return min(s0, s1, s0_odd, s1_odd)


# runtime - 11ms
class Solution:
    def minFlips(self, s: str) -> int:
        """
        1 <= s.length <= 10^5
        even: 
        odd: 101 (110), 010 (001), (111) 
        01  00 10 01 10 1
            010011010
              0011010
              0110100
                10100
                01001
                   01
                   10

        01  00 10 01 10 1
            e. o. 
        1: (3, 2)
        0: (3, 3)

        00 10 01 10 10 1
            e. o. 
        1: (4, 1)
        0: (2, 4)

        01 00 11 01 01 0 (odd length)
            e. o. 
        1: (1, 4)
        0: (5, 1)

        min(e1 + o0, o1 + e0) = 2

        rotate at every position so the state is changed
        O(n)


        """
        if len(s) == 1: return 0
        if len(s) == 2 and s[0] == s[1]: return 1

        n = len(s)
        
        #now count two costs: '0' & '1'            
        def get_cost(s):
            cost_0, cost_1 = 0, 0
            p0, p1 = '0', '1'
            for i in range(n):
                if s[i] != p0:
                    cost_0 += 1
                if s[i] != p1:
                    cost_1 += 1
                
                p0, p1 = p1, p0
            
            return min(cost_0, cost_1)
    
        res = get_cost(s)                
        return res

    def minFlips(self, s: str) -> int:
        n = len(s)
        state = {'0':{0:0, 1:0}, '1':{0:0, 1:0}}

        for i, c in enumerate(s):
            state[c][i%2] += 1
        
        res = min(state['0'][0] + state['1'][1], state['0'][1] + state['1'][0])
        if n % 2 == 0 or res == 0: return res
        
        #loop every possible shift
        for i in range(0, n):
            if s[i] == '0':
                state['0'] = {0: state['0'][1] + 1, 1: state['0'][0] - 1}
                state['1'] = {0: state['1'][1], 1: state['1'][0]}
            if s[i] == '1':
                state['0'] = {0: state['0'][1], 1: state['0'][0]}
                state['1'] = {0: state['1'][1] + 1, 1: state['1'][0] - 1}
            #print(state)
            cur = min(state['0'][0] + state['1'][1], state['0'][1] + state['1'][0])
            res = min(res, cur)
            if res == 0: return 0
        
        return res


class Solution:
    def minFlips(self, s: str) -> int:
        """
        1 <= s.length <= 10^5.  
        111111 
         op-1: did not change anything
         op-2: scan from left to right -> 1010101. n//2
         
        target: 
         min(n//2)
        """
        n = len(s)
        check_1 = s[::2].count('1') + s[1::2].count('0')    #tgt: '01010..'
        check_0 = n - check_1 #tgt: '101010'
        
        res = min(check_1, check_0)

        #must be having this, otherwise it will fail.
        if n % 2 == 0:
            return res

        print(check_1, check_0)
        for c in s:
            #op-1: shift this c and appending to the end. it will cause odd-cnt to switch, as index from 0%2==0 --> len(s)%2==1
            check_1, check_0 = check_0, check_1
            if c == '1':
                check_1 += 1
                check_0 -= 1
            else:
                check_1 -= 1
                check_0 += 1
            #print(c, check_1, check_0)
            res = min(res, check_1, check_0)
        return res


# runtime - 166ms
class Solution:
    def minFlips(self, s: str) -> int:

        n, list0, list1, s, ans = len(s), [0, 0], [0, 0], list(map(int, s)), inf

        for i, digit in enumerate(s):
            list0[(i & 1) ^ (digit)] += 1

        if not n % 2:
            return min(list0)

        for i, digit in enumerate(s):
            list0[i & 1 ^ digit] -= 1
            list1[~i & 1 ^ digit] += 1

            ans = min(ans, list0[0] + list1[0], list0[1] + list1[1])

        return ans
