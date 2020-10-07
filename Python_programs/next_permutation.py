"""

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
The replacement must be in-place and use only constant extra memory.

"""

import time


class Solution:
    def next_per(self, arr):
        prefix = len(arr)-2
        while prefix > -1 and arr[prefix] >= arr[prefix+1]:
            prefix -= 1

        if prefix == -1:
            return arr.reverse()

        suffix = len(arr)-1
        while suffix > prefix:
            if arr[suffix] > arr[prefix]:
                arr[suffix], arr[prefix] = arr[prefix], arr[suffix]
            suffix -= 1

        arr[prefix+1:] = reversed(arr[prefix+1:])
        return arr


if __name__ == "__main__":
    s = Solution()
    start = time.time()
    print("enter a list of integers")
    arr = input()
    arr = list(map(int, arr.split(',')))
    print(s.next_per(arr))
    print("time taken: {} seconds".format(time.time() - start))
    