# Repeated DNA Sequences

**Difficulty:** Medium  
**Topic:** Strings, Sliding Window, Hash Set  
**Source:** Kunal (Kunal Kushwaha)

## Problem Statement

The DNA is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'. When studying DNA, it is useful to identify repeated sequences within the DNA.

Given a string `s` that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

**Example:**
```
Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC","CCCCCAAAAA"]
```

## Approach & Intuition

### Substring Generation with HashSet:
The solution uses a sliding window approach combined with hash sets to efficiently track seen and repeated sequences.

### Algorithm Steps:
1. **Sliding Window:** Generate all possible 10-character substrings
2. **First HashSet (`set`):** Track all sequences we've seen
3. **Second HashSet (`list`):** Track sequences that appear more than once
4. **Duplicate Detection:** If a sequence is already in the first set, add it to the result set

### Key Insight:
Using two HashSets ensures:
- We don't add duplicates to the result
- We efficiently check for previously seen sequences in O(1) time

## Key Observations

1. **Fixed Window Size:** Always looking for 10-character sequences
2. **Duplicate Prevention:** Using HashSet for result prevents duplicate entries
3. **Efficient Lookup:** HashSet provides O(1) average time complexity for contains()
4. **Space-Time Tradeoff:** Uses extra space for HashSets to achieve better time complexity

## Complexity Analysis

**Time Complexity:** O(n) where n is the length of the string
- Single pass through the string
- Each substring operation is O(10) = O(1)
- HashSet operations are O(1) average case

**Space Complexity:** O(n) 
- Two HashSets can store up to O(n) substrings
- Each substring is 10 characters long

## Edge Cases Considered

1. **String length < 10:** No valid sequences (though loop handles this)
2. **No repeated sequences:** Returns empty list
3. **All sequences repeated:** All valid 10-letter sequences returned
4. **Minimum valid input:** String of exactly 10 characters

## Code Implementation

```java
public static List<String> findRepeatedDnaSequences(String s) {
    Set<String> set = new HashSet<>();      // Track seen sequences
    Set<String> list = new HashSet<>();     // Track repeated sequences
    
    for (int i = 0; i <= s.length() - 10; i++) {
        String substring = s.substring(i, i + 10);
        if (set.contains(substring)) {
            list.add(substring);  // Found a repeat
        } else {
            set.add(substring);   // First time seeing this sequence
        }
    }
    return new ArrayList<>(list);
}
```

## Alternative Solutions

1. **Brute Force:** Check every substring against every other - O(n²) time
2. **Rolling Hash:** Use polynomial rolling hash for better performance
3. **Trie-based:** Use Trie data structure for prefix matching

## Optimization Opportunities

1. **Rolling Hash:** For very long strings, could use rolling hash to avoid substring creation
2. **Bit Manipulation:** Since DNA has only 4 characters, could use 2 bits per character

## Cross-Reference

- **Related Problems:** Substring matching, pattern recognition
- **Prerequisites:** String manipulation, HashSet usage
- **Next Steps:** Rolling hash techniques, bit manipulation for DNA

---

*Documented on: 2024-12-19*  
*Category: Strings (Pattern Recognition)* 