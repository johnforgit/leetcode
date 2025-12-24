class Solution:
    def minimumBoxes(self, apple: List[int], capacity: List[int]) -> int:
        num_boxes=0
        capacity.sort(reverse=True)
        total_apples = sum(apple)
        while total_apples > 0:
            total_apples -= capacity[num_boxes]
            num_boxes += 1
        return num_boxes
