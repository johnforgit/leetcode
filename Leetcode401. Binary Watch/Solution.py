class Solution:
    def readBinaryWatch(self, turnedOn: int) -> List[str]:
        ans = list()
        for i in range(1024):
            h, m = (
                i >> 6,
                i & 0x3F,
            )  # Extract the high 4 bits and low 6 bits using bitwise operations
            if h < 12 and m < 60 and bin(i).count("1") == turnedOn:
                ans.append(f"{h}:{m:02d}")
        return ans



# runtime - 0ms
class Solution:
    """Find all times for binary watch if K lights are turned on.
    The watch has 4 lights to represent the hours (0-11)
    and 6 lights to represent the minutes (0-59).
    """

    def readBinaryWatch(self, k: int) -> List[str]:
        return self.readBinaryWatch_v1(k)

    def readBinaryWatch_v1(self, k: int) -> List[str]:
        """
        # Design:
        - Intuition: Must check total number of lights used.
        - Approach: Treat like a combinatorial partitioning problem.
        - Complexity: Runtime O(K)=O(1). Memory O(1).

        # Submission:

        """

        from itertools import combinations, product
        if k > 8: return []
        hhmm_times = []
        hh_values = [1, 2, 4, 8]; mm_values = [1, 2, 4, 8, 16, 32]
        hh_lights_max, mm_lights_max = 3, 5
        hh_values_max, mm_values_max = 11, 59
        for hh_lights in range(min(hh_lights_max, k) + 1):
            mm_lights = k - hh_lights
            hh = [sum(x) for x in list(combinations(hh_values, hh_lights))]
            mm = [sum(x) for x in list(combinations(mm_values, mm_lights))]
            for h, m in product(hh, mm):
                if h <= hh_values_max and m <= mm_values_max:
                    hhmm_times.append(f"{h}:{m:02d}")
        return hhmm_times


class Solution3:
    """Find all times on binary watch using K lights.
    The 4 LEDs for the hours (0-11) use up to 3 lights.
    The 6 LEDs for the minutes (0-59) use up to 5 lights.

    # Intuition:
    - The one at a time method builds K splits one at a time
        where each split has (A, K-A) lights for hours and minutes
        and generates HH=comb(4, A) and MM=comb(6, K-A) values
        as well as their HH*MM element-wise pair-wise combinations.
        Takes runtime T(K*(HH+MM+HH*MM))=O(K)=O(1) and memory O(1).
    """

    def readBinaryWatch(self, k: int) -> List[str]:
        return self.readBinaryWatch_v1(k)

    def readBinaryWatch_v1(self, k: int) -> List[str]:
        """The one at a time method builds K splits one at a time.

        # Submission:
        - 1: Runtime 0 ms (100%). Memory 19.73 MB (*13%).
        - 2: Runtime 0 ms (100%). Memory 19.56 MB (*13%).
        - 3: Runtime 0 ms (100%). Memory 19.69 MB (*13%).
        """
        from itertools import combinations
        if k > 8: return []
        hhmm_times = []
        hh_leds = [1, 2, 4, 8]; mm_leds = [1, 2, 4, 8, 16, 32]
        for a in range(min(3, k)+1):
            hh = [sum(x) for x in list(combinations(hh_leds, a))]
            mm = [sum(x) for x in list(combinations(mm_leds, k-a))]
            hhmm_times.extend([
                f"{h}:{m:02}" 
                for h, m in ((h, m) for h in hh for m in mm)
                if h <= 11 and m <= 59
            ])
        return hhmm_times


# runtime - 1ms

class Solution:
    def readBinaryWatch(self, turnedOn: int) -> List[str]:
        # we define a list as time
        time = []
        # for all hours in range 12 as 0-11 hours we have
        # for loop coz we wanna iterate till 12
        for hr in range(12):
            # similarly we wanna iterate 0(1) + 59 = 60 
            # for loop coz minutes goes till 60
            for mini in range(60):
                # binary value of hours and minutes and increment the count to 
                # checking how many bits are set to 1 in hr
                hrbin = bin(hr).count('1')
                # checking how many bits are set to 1 in mini
                minibin = bin(mini).count('1')
                # and as turnedOn is an int, we append if sum matches
                # checking if total count is equal to given turnedOn bits
                if hrbin + minibin == turnedOn:
                    # formatting it as hr:mm and appending to our list
                    time.append(f"{hr}:{mini:02d}")
        # final result return kar diya
        return time

# runtime - 2ms
class Solution:
    def count_ones(self, digit):
        count = 0

        while digit > 0:
            if digit % 2 == 1:
                count += 1

            digit >>= 1

        return count

    def readBinaryWatch(self, turnedOn: int) -> List[str]:
        result = []

        for hours in range(12):
            for minutes in range(60):
                if self.count_ones(hours) + self.count_ones(minutes) == turnedOn:
                    result_str = f"{hours}:{minutes:02}"
                    result.append(result_str)

        return result
        
