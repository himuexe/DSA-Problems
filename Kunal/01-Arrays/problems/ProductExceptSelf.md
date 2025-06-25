# Product of Array Except Self

**Date:** June 25, 2025  
**Category:** Kunal Arrays  
**Source:** Kunal DSA Course  
**Difficulty:** Medium  
**Topic:** Array/Prefix Sum

## Problem Statement

Given an integer array `nums`, return an array `answer` such that `answer[i]` is equal to the product of all the elements of `nums` except `nums[i]`.

The product of any prefix or suffix of `nums` is **guaranteed** to fit in a **32-bit** integer.

You must write an algorithm that runs in **O(n)** time and without using the **division** operation.

**Example:**
```
Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Explanation: 
- answer[0] = 2*3*4 = 24
- answer[1] = 1*3*4 = 12  
- answer[2] = 1*2*4 = 8
- answer[3] = 1*2*3 = 6
```

## Intuition/Approach

**Two-Pass Prefix-Suffix Product:**
- **Key Insight:** For each position i, result = (product of all elements before i) × (product of all elements after i)
- **First pass:** Calculate prefix products in the result array
- **Second pass:** Multiply with suffix products calculated on-the-fly

**Space Optimization:** Use the output array to store prefix products, then compute suffix products in a single variable during the second pass.

## Algorithm Steps

1. **Initialize:** Create result array, `prefixProduct[0] = 1`
2. **First pass (Prefix):** For each position i from 1 to n-1:
   - `prefixProduct[i] = prefixProduct[i-1] * nums[i-1]`
3. **Second pass (Suffix):** For each position i from n-1 to 0:
   - `prefixProduct[i] *= suffixProduct`
   - `suffixProduct *= nums[i]`
4. **Return:** The modified prefixProduct array contains final results

## Key Observations

- **No division needed:** Avoids division operation constraints
- **Space efficient:** Uses output array for intermediate storage
- **Two-pass algorithm:** First for prefix, second for suffix
- **Handle zeros:** Algorithm naturally handles zero elements
- **Overflow protection:** Problem guarantees no overflow

## Time & Space Complexity

- **Time Complexity:** O(n) - Two passes through array
- **Space Complexity:** O(1) extra space (not counting output array)

## Edge Cases Considered

- [x] Array with single element (return [1] - no other elements)
- [x] Array with zeros (product correctly becomes 0)
- [x] Array with negative numbers (signs handled correctly)
- [x] Array with ones (no effect on products)
- [x] Minimum length array (n=2)

## Solution Code

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] prefixProduct = new int[nums.length];
        
        // First pass: Calculate prefix products
        prefixProduct[0] = 1;
        for(int i = 1; i < nums.length; i++) {
            prefixProduct[i] = prefixProduct[i-1] * nums[i-1];
        }
        
        // Second pass: Multiply with suffix products
        int suffixProduct = 1;
        for(int i = nums.length - 1; i >= 0; i--) {
            prefixProduct[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }
        
        return prefixProduct;
    }
}
```

## Algorithm Walkthrough

### Example: nums = [1,2,3,4]

**After First Pass (Prefix Products):**
```
prefixProduct = [1, 1, 2, 6]
- prefixProduct[0] = 1 (no elements before index 0)
- prefixProduct[1] = 1 (product of elements before index 1: [1])
- prefixProduct[2] = 2 (product of elements before index 2: [1,2])
- prefixProduct[3] = 6 (product of elements before index 3: [1,2,3])
```

**During Second Pass (Suffix Multiplication):**
```
i=3: prefixProduct[3] = 6 * 1 = 6, suffixProduct = 4
i=2: prefixProduct[2] = 2 * 4 = 8, suffixProduct = 12  
i=1: prefixProduct[1] = 1 * 12 = 12, suffixProduct = 24
i=0: prefixProduct[0] = 1 * 24 = 24, suffixProduct = 24

Final result: [24, 12, 8, 6]
```

## Alternative Approaches

1. **Brute Force O(n²):**
```java
public int[] productExceptSelf(int[] nums) {
    int[] result = new int[nums.length];
    for(int i = 0; i < nums.length; i++) {
        int product = 1;
        for(int j = 0; j < nums.length; j++) {
            if(i != j) product *= nums[j];
        }
        result[i] = product;
    }
    return result;
}
```

2. **Division Approach (if allowed):**
```java
public int[] productExceptSelf(int[] nums) {
    int totalProduct = 1;
    int zeroCount = 0;
    
    for(int num : nums) {
        if(num == 0) zeroCount++;
        else totalProduct *= num;
    }
    
    int[] result = new int[nums.length];
    for(int i = 0; i < nums.length; i++) {
        if(nums[i] == 0) {
            result[i] = (zeroCount == 1) ? totalProduct : 0;
        } else {
            result[i] = (zeroCount > 0) ? 0 : totalProduct / nums[i];
        }
    }
    return result;
}
```

3. **Separate Prefix/Suffix Arrays:**
```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] prefix = new int[n];
    int[] suffix = new int[n];
    int[] result = new int[n];
    
    // Calculate prefix products
    prefix[0] = 1;
    for(int i = 1; i < n; i++) {
        prefix[i] = prefix[i-1] * nums[i-1];
    }
    
    // Calculate suffix products  
    suffix[n-1] = 1;
    for(int i = n-2; i >= 0; i--) {
        suffix[i] = suffix[i+1] * nums[i+1];
    }
    
    // Combine results
    for(int i = 0; i < n; i++) {
        result[i] = prefix[i] * suffix[i];
    }
    
    return result;
}
```

## Mathematical Analysis

### Product Distribution
For array of length n, each element contributes to (n-1) products:
- Element at index i affects all result positions except i
- Total multiplications: n × (n-1) in brute force vs 2n in optimal

### Zero Handling
- **One zero:** All products are 0 except the zero's position
- **Multiple zeros:** All products are 0
- **No zeros:** Standard prefix-suffix multiplication

## Key Learnings

1. **Prefix-suffix pattern:** Powerful technique for array problems
2. **Space optimization:** Reuse output array for intermediate results
3. **Two-pass efficiency:** O(n) time with O(1) extra space
4. **Avoid division:** Mathematical constraints can be handled algorithmically
5. **Product accumulation:** Running products in both directions

## Related Problems

- Maximum Product Subarray
- Minimum Number of Operations to Make Array Empty
- Range Product Queries of Powers
- Construct Product Matrix
- Apply Operations to Make All Array Elements Equal to Zero

**LeetCode Connection:** LeetCode #238 - Product of Array Except Self 