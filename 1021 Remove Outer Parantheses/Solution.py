# runtime - 0ms
class Solution:
    def removeOuterParentheses(self, s: str) -> str:
        res, stack = [], []
        for c in s:
            if c == ")":
                stack.pop()
            if stack:
                res.append(c)
            if c == "(":
                stack.append(c)
        return "".join(res)

# runtime - 1ms
class Solution:
    def removeOuterParentheses(self, s: str) -> str:
        res, level = [], 0
        for c in s:
            if c == ")":
                level -= 1
            if level > 0:
                res.append(c)
            if c == "(":
                level += 1
        return "".join(res)