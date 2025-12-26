class Solution:
    def bestClosingTime(self, customers: str) -> int:
        min_penalty=0
        BEST_CLOSING_HOUR=0
        INITIAL_COST=0
        
        for i in range(len(customers)):
            INITIAL_COST += -1 if customers[i] == 'Y' else 1
            if INITIAL_COST < min_penalty:
                BEST_CLOSING_HOUR = i+1
                min_penalty = INITIAL_COST
        return BEST_CLOSING_HOUR


