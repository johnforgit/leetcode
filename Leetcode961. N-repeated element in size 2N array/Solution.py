class Solution:
    def repeatedNTimes(self, nums: List[int]) -> int:
        '''
        d={}
        for i in nums:
            if i in d:
                return i
            d[i]=1
        '''

        seen = set()
        for x in nums:
            if x in seen:
                return x
            seen.add(x)

        # another approach using a dictionary
        '''
        n = len(nums)//2
        freq = defaultdict(int)
        for x in nums:
            freq[x] += 1
            if freq[x] == n:
                return x
        '''
