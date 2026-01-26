class Solution:
    def maximumCostSubstring(self, s: str, chars: str, vals: List[int]) -> int:
        # initialize the alphabet array
        v=[i+1 for i in range(26)]

        # assign the corresponding value of characters present in chars and vals
        for i in range(len(chars)):
            v[ord(chars[i]) - ord('a')] = vals[i]
        print(v)

        n = len(s)
        ans = 0
        temp = 0
        vector_s = []
        # find the score of the string
        for i in range(n):
            vector_s.append(v[ord(s[i])-ord('a')])
        print(vector_s)

        for i in range(n):
            temp += vector_s[i]
            if(temp < 0):
                temp = 0
            ans = max(temp, ans)

        return ans


# runtime - 41ms
class Solution:
    def maximumCostSubstring(self, s: str, chars: str, vals: List[int]) -> int:
        cost = {
            "a": 1,
            "b": 2,
            "c": 3,
            "d": 4,
            "e": 5,
            "f": 6,
            "g": 7,
            "h": 8,
            "i": 9,
            "j": 10,
            "k": 11,
            "l": 12,
            "m": 13,
            "n": 14,
            "o": 15,
            "p": 16,
            "q": 17,
            "r": 18,
            "s": 19,
            "t": 20,
            "u": 21,
            "v": 22,
            "w": 23,
            "x": 24,
            "y": 25,
            "z": 26,
        }
        for i, char in enumerate(chars):
            cost[char] = vals[i]

        maxCost = 0
        currCost = 0
        for char in s:
            currCost += cost[char]
            if currCost > maxCost:
                maxCost = currCost
            if currCost < 0:
                currCost = 0
        return maxCost


# runtime - 45ms
import string


class Solution:
    def maximumCostSubstring(self, s: str, chars: str, vals: List[int]) -> int:
        missing_chars = False
        for i, letter in enumerate(string.ascii_lowercase,1):
            if letter not in chars:
                chars = chars+ letter
                vals.append(i)
                missing_chars = True

        char_value = {}
        for c,v in zip(chars,vals):
            char_value[c] = v
        sub_arr_sum = char_value[s[0]]
        max_sum = sub_arr_sum

        for char in s[1:]:
            if sub_arr_sum < 0:
                sub_arr_sum = char_value[char]
            else:
                sub_arr_sum += char_value[char]
            if sub_arr_sum > max_sum:
                max_sum = sub_arr_sum
        return max_sum if max_sum > 0 else 0

# runtime - 49ms
from typing import List


class Solution:
    def maximumCostSubstring(self, s: str, chars: str, vals: List[int]) -> int:
        c_to_val = {c: v for c, v in zip(chars, vals)}
        for alph in range(0, 26):
            ch_alph = chr(alph + ord('a'))
            if ch_alph not in c_to_val:
                c_to_val[ch_alph] = alph + 1

        ans = 0

        curr = 0
        for ch in s:
            curr += c_to_val[ch]
            if curr < 0:
                curr = 0
            ans = max(ans, curr)

        return ans

# runtime - 53ms
class Solution:
    def maximumCostSubstring(self, s: str, chars: str, vals: List[int]) -> int:
        def maximum_subarray(nums):
            curr_sum, best_sum = 0, 0
            for n in nums:
                curr_sum += n
                if curr_sum < n:
                    curr_sum = n
                if best_sum < curr_sum:
                    best_sum = curr_sum
            return best_sum

        dictionary = {}
        for i, c in enumerate(chars):
            dictionary[c] = i
        nums = [
            ord(c) - ord("a") + 1 if c not in dictionary else vals[dictionary[c]]
            for c in s
        ]
        return maximum_subarray(nums)
