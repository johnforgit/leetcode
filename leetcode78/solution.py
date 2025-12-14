class Solution:
    '''
    def subsets(self, nums: List[int]) -> List[List[int]]:
        res = []
        subsets=[]

        # create subsets with the number at index i
        def create_subset(i):
            # if the index is equal to the length of the array
            # that means we have reached the end
            # return the array
            if i==len(nums):
                res.append(subsets[:])
                return
            subsets.append(nums[i])
            create_subset(i+1)

            # remove the last element from the subset to backtrack and
            # recursively call create_subset with the same index
            subsets.pop()
            create_subset(i+1)
        
        create_subset(0)
        return res
    '''
    def subsets(self, nums: List[int]) -> List[List[int]]:
        res = []

        def dfs(sub, start):
            res.append(sub[:])

            for i in range(start, len(nums)):
                sub.append(nums[i])
                dfs(sub, i+1)
                sub.pop()
        dfs([],0)
        return res
