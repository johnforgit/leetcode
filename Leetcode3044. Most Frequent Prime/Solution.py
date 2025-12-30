class Solution:
    def mostFrequentPrime(self, mat: List[List[int]]) -> int:
        self.d=defaultdict(int)
        self.dir = [[1, 0], [-1, 0], [0, 1], [0, -1], [1,1]]
        for i in range(len(mat)):
            for j in range(len(mat[0])):
                mat[i][j] = str(mat[i][j])

        def isPrime(n):
            if n<2:
                return False
            for i in range(2, int(n**0.5)+1):
                if n%i==0:
                    return False
            return True
        def up(i, j, cur, seen):
            if(i<0 or i>=len(mat) or j<0 or j>=len(mat[0])):
                return
            cur.append(mat[i][j])
            seen.add((i,j))
            dig = int("".join(cur))
            if isPrime(dig) and dig>10:
                self.d[dig] += 1
            up(i-1, j, cur, seen)
        
        def down(i, j, cur, seen):
            if(i<0 or i>=len(mat) or j<0 or j>=len(mat[0])):
                return
            cur.append(mat[i][j])
            seen.add((i, j))
            dig = int("".join(cur))
            if isPrime(dig) and dig>10:
                self.d[dig] += 1
            down(i+1, j, cur, seen)
        
        def right(i, j, cur, seen):
            if(i<0 or i>=len(mat) or j<0 or j>=len(mat[0])):
                return
            cur.append(mat[i][j])
            seen.add((i, j))
            dig = int("".join(cur))
            if isPrime(dig) and dig>10:
                self.d[dig] += 1
            right(i, j+1, cur, seen)

        def left(i, j, cur, seen):
            if(i<0 or i>=len(mat) or j<0 or j>=len(mat[0])):
                return
            cur.append(mat[i][j])
            seen.add((i, j))
            dig = int("".join(cur))
            if isPrime(dig) and dig>10:
                self.d[dig] += 1
            left(i, j-1, cur, seen)
        
        def downright(i, j, cur, seen):
            if(i<0 or i>=len(mat) or j<0 or j>=len(mat[0])):
                return
            cur.append(mat[i][j])
            seen.add((i, j))
            dig = int("".join(cur))
            if isPrime(dig) and dig>10:
                self.d[dig] += 1
            downright(i+1, j+1, cur, seen)

        def downleft(i, j, cur, seen):
            if(i<0 or i>=len(mat) or j<0 or j>=len(mat[0])):
                return
            cur.append(mat[i][j])
            seen.add((i, j))
            dig = int("".join(cur))
            if isPrime(dig) and dig>10:
                self.d[dig] += 1
            downleft(i+1, j-1, cur, seen)

        def upright(i, j, cur, seen):
            if(i<0 or i>=len(mat) or j<0 or j>=len(mat[0])):
                return
            cur.append(mat[i][j])
            seen.add((i, j))
            dig = int("".join(cur))
            if isPrime(dig) and dig>10:
                self.d[dig] += 1
            upright(i-1, j+1, cur, seen)

        def upleft(i, j, cur, seen):
            if(i<0 or i>=len(mat) or j<0 or j>=len(mat[0])):
                return
            cur.append(mat[i][j])
            seen.add((i, j))
            dig = int("".join(cur))
            if isPrime(dig) and dig>10:
                self.d[dig] += 1
            upleft(i-1, j-1, cur, seen)

        for i in range(len(mat)):
            for j in range(len(mat[0])):
                up(i, j, [], set())
                down(i, j, [], set())
                left(i, j, [], set())
                right(i, j, [], set())
                downright(i, j, [], set())
                downleft(i, j, [], set())
                upright(i, j, [], set())
                upleft(i, j, [], set())

        if not self.d:
            return -1
        
        biggest = max(self.d.values())
        sl = SortedList()
        for a, b in self.d.items():
            if b==biggest:
                sl.add(a)
        
        return sl[-1]
