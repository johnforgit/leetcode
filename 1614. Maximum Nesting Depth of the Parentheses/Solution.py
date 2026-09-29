class Solution:
    def maxDepth(self, s: str) -> int:
        ans, openBrackets = 0, 0
        for c in s:
            if c == '(':
                openBrackets += 1
            elif c == ')':
                openBrackets -= 1
            ans = max(ans, openBrackets)
        return ans


class Solution:
    def maxDepth(self, s: str) -> int:
        ans, st = 0, []
        for c in s:
            if c == '(':
                st.append(c)
            elif c == ')':
                st.pop()
            ans = max(ans, len(st))
        return ans


