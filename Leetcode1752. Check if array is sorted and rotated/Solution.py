class Solution:
    def check(self, nums: List[int]) -> bool:
        switch = 0
        for i in range(len(nums)-1):
            if nums[i] > nums[i+1]:
                switch += 1
            if switch == 2:
                return False

        # compare the last character and first character
        if nums[0] < nums[-1]:
            switch += 1

        return switch < 2
