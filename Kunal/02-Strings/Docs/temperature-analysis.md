# Temperature Analysis System

**Source:** Kunal | **Topic:** Strings/Data Parsing | **Difficulty:** Medium  

---

## Problem Statement
Given entries like `"21 July 30"`, compute average temperature and identify coldest/hottest days.

## Intuition/Approach
Parse each entry (date, month, temperature), aggregate statistics, and guard against malformed data.

## Key Observations
- Robust parsing and error handling are essential
- Single pass suffices for sum/min/max
- Output formatting can be standardized via String.format

## Algorithm Steps
1. Initialize sum, count, min, max, and corresponding date labels
2. For each entry: split parts; parse temperature if present
3. Update aggregates and min/max trackers
4. Compute average as sum/count (if count > 0)

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1) basic implementation; O(n) if storing records
- **Justification:** Single pass over input

## Edge Cases Considered
- [x] Empty dataset
- [x] Invalid number formats
- [x] Missing fields
- [x] Single entry
- [x] All equal temperatures
- [x] Negative temperatures
- [x] Extra whitespace

## Solution Code

```java
public class TemperatureAnalysis {
    public static String findAverageTemperature(String[] data) {
        if (data == null || data.length == 0) return "0.0";
        double sum = 0; int count = 0;
        for (String entry : data) {
            try {
                String[] parts = entry.split(" ");
                if (parts.length >= 3) {
                    double temp = Double.parseDouble(parts[2]);
                    sum += temp; count++;
                }
            } catch (NumberFormatException e) { /* skip */ }
        }
        if (count == 0) return "0.0";
        return String.format("%.1f", sum / count);
    }
}
```

## Alternative Approaches
- Stream-based processing with DTOs
- CSV/JSON parsing libraries
- Time series analysis with date parsing

## Personal Notes
- Keep parsing strict and fail-safe; skip bad entries without failing the whole batch

---
**Tags:** #strings #parsing #datastructures