class Solution:
    def checkStrings(self, s1: str, s2: str) -> bool:
        return Counter(s1[::2]) == Counter(s2[::2]) and Counter(
            s1[1::2]
        ) == Counter(s2[1::2])


# runtime - 4ms
class Solution:
    def checkStrings(self, s1: str, s2: str) -> bool:
        e1, e2 = s1[::2], s2[::2]
        o1, o2 = s1[1::2], s2[1::2]
        
        for c in 'abcdefghijklmnopqrstuvwxyz':
            if e1.count(c) != e2.count(c): return False
            if o1.count(c) != o2.count(c): return False
        
        return True


# runtime - 7ms
class Solution:
    def checkStrings(self, s1: str, s2: str) -> bool:
        e1, e2 = s1[0::2], s2[0::2]
        o1, o2 = s1[1::2], s2[1::2]
        for char_code in range(97, 123): # 'a' to 'z'
            char = chr(char_code)
            if e1.count(char) != e2.count(char):
                return False
            if o1.count(char) != o2.count(char):
                return False
                
        return True        


# runtime - 10ms
class Solution:
    def checkStrings(self, s1: str, s2: str) -> bool:
        # Step 1: Vectorize by converting to bytes (zero-copy if using memoryview)
        # Step 2: Slice (Mask) into Even and Odd channels
        # Step 3: Use an optimized frequency count or algebraic sum
        
        # Even Channel
        e1, e2 = s1[0::2], s2[0::2]
        # Odd Channel
        o1, o2 = s1[1::2], s2[1::2]
        
        # The 'Vectorized' Invariant Check: 
        # We check the 26-char frequency vector for each channel.
        # This is faster than manual loops because .count() is implemented in C.
        for char_code in range(97, 123): # 'a' to 'z'
            char = chr(char_code)
            if e1.count(char) != e2.count(char):
                return False
            if o1.count(char) != o2.count(char):
                return False
                
        return True


# runtime - 13ms
import numpy as np

class Solution:
    def checkStrings(self, s1: str, s2: str) -> bool:
        str1 = np.frombuffer(s1.encode(), dtype=np.uint8) - 97
        str2 = np.frombuffer(s2.encode(), dtype=np.uint8) - 97
        even1 = np.bincount(str1[::2])
        even2 = np.bincount(str2[::2])
        odd1 = np.bincount(str1[1::2])
        odd2 = np.bincount(str2[1::2])
        return np.array_equal(even1, even2) and np.array_equal(odd1, odd2)


# runtime - 16ms
class Solution: # Time = O(n) | Space = O(1)
    def checkStrings(self, s1: str, s2: str) -> bool:
        return all(s1[::2].count(c) == s2[::2].count(c) \
            and s1[1::2].count(c) == s2[1::2].count(c) \
                for c in 'abcdefghijklmnopqrstuvwxyz')
