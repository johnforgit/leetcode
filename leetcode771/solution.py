class Solution:
    '''
    def numJewelsInStones(self, jewels: str, stones: str) -> int:
        # O(S * J)
        num_stones_in_jewels = 0
        for i in range(0 ,len(stones)):
            if stones[i] in jewels:
                num_stones_in_jewels += 1

        return num_stones_in_jewels
    '''
    
    # another solution. more time efficent
    def numJewelsInStones(self, jewels: str, stones: str) -> int:
        #O(S+J)
        jewels = set(jewels)
        num_stones_in_jewels = 0
        for i in range(0 ,len(stones)):
            if stones[i] in jewels:
                num_stones_in_jewels += 1

        return num_stones_in_jewels
