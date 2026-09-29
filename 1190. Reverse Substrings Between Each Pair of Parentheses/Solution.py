class Solution:
    def reverseParentheses(self, s: str) -> str:
        open_parentheses_indices = deque()
        result = []

        for current_char in s:
            if current_char == "(":
                # Store the current length as the start index
                # for future reversal
                open_parentheses_indices.append(len(result))
            elif current_char == ")":
                start = open_parentheses_indices.pop()
                # Reverse the substring between the matching parentheses
                result[start:] = result[start:][::-1]
            else:
                # Append non-parenthesis characters to the processed list
                result.append(current_char)
        return "".join(result)

# Using wormhole teleportation technique
class Solution:
    def reverseParentheses(self, s: str) -> str:
        n = len(s)
        open_parentheses_indices = []
        pair = [0] * n

        # First pass: Pair up parentheses
        for i in range(n):
            if s[i] == "(":
                open_parentheses_indices.append(i)
            if s[i] == ")":
                j = open_parentheses_indices.pop()
                pair[i] = j
                pair[j] = i

        # Second pass: Build the result string
        result = []
        curr_index = 0
        direction = 1

        while curr_index < n:
            if s[curr_index] == "(" or s[curr_index] == ")":
                curr_index = pair[curr_index]
                direction = -direction
            else:
                result.append(s[curr_index])
            curr_index += direction

        return "".join(result)