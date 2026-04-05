class Solution(object):
    def judgeCircle(self, moves):
        x = y = 0
        for move in moves:
            if move == 'U': y -= 1
            elif move == 'D': y += 1
            elif move == 'L': x -= 1
            elif move == 'R': x += 1

        return x == y == 0


# runtime - 0ms
class Solution:
    def judgeCircle(self, moves: str) -> bool:
        return moves.count('U')==moves.count('D') and moves.count('L')==moves.count('R')


# runtime - 7ms
class Solution:
    def judgeCircle(self, moves: str) -> bool:
        cnt = Counter(moves)
        return cnt['U'] == cnt['D'] and cnt['L'] == cnt['R']


# runtime - 10ms
class Solution:
    def judgeCircle(self, moves: str) -> bool:
        if len(moves) % 2:
            return False
        c = Counter(moves)
        l = c.get('L', 0)
        r = c.get('R', 0)
        u = c.get('U', 0)
        d = c.get('D', 0)
        return l == r and u == d
