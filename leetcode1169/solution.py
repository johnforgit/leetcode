class Solution:
    '''
    # takes more time
    def invalidTransactions(self, transactions: List[str]) -> List[str]:
        d=defaultdict(list)
        c=Counter(transactions)
        ans = []
        # store the values in a dictionary and sort it
        for t in transactions:
            name, time, amount, city = t.split(",")
            if (int(amount) > 1000) and (ans.count(t) < c[t]):
                ans.append(t)
            d[name].append([time, city])
            d[name].sort(key = lambda x:x[0])

        for t in transactions:
            name, time, amount, city = t.split(",")
            val = d[name]
            for v in val:
                diff = abs(int(v[0]) - int(time))
                if diff <= 60 and v[1] != city and ans.count(t)<c[t]:
                    ans.append(t)
        return ans
    '''
    def invalidTransactions(self, transactions: list[str]) -> list[str]:
        name_map = defaultdict(list)
        for idx, t in enumerate(transactions):
            name, time, amount, city = t.split(",")
            txn_data = [int(time), int(amount), city, idx]
            name_map[name].append(txn_data)

        output = []

        for idx, t in enumerate(transactions):
            name, time, amount, city = t.split(",")
            time, amount = int(time), int(amount)

            if amount > 1000:
                output.append(t)
            else:
                # lookup for another transactions
                for other_time, other_amount, other_city, other_idx in name_map[name]:
                    if idx != other_idx and abs(other_time - time) <=60 and city != other_city:
                        output.append(t)
                        break
        
        return output

