# Activity Selection (Max Non-overlapping Intervals)

**Source:** AC | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
Given start and end times of activities, select the maximum number of non-overlapping activities that can be performed by a single person (one at a time).

## Intuition/Approach
- Classic greedy: pick the activity with the earliest finishing time to leave room for as many subsequent activities as possible.
- Sort activities by end time and iteratively choose the next activity whose start time is at least the last selected end time.

## Key Observations
- Sorting by end time is optimal; sorting by start time can fail.
- If multiple activities share the same end time, their relative order does not matter.
- We only need to track the end time of the last selected activity.

## Algorithm Steps
1. Combine `start[i]`, `end[i]`, and index into tuples.
2. Sort tuples by `end` time ascending.
3. Initialize `count = 0` and `lastEnd = -∞` (or first selected end time post-pick).
4. Iterate activities in sorted order; if `start >= lastEnd`, select it, increment `count`, update `lastEnd`.
5. Return `count` (and optionally the selected indices).

## Complexity Analysis
- **Time Complexity:** O(n log n) for sorting, O(n) selection
- **Space Complexity:** O(n) for the activity tuples
- **Justification:** Greedy pass is linear after sorting.

## Edge Cases Considered
- [x] Empty input
- [x] Single activity
- [x] Identical end times
- [x] Overlapping vs touching intervals (start == lastEnd is allowed)
- [ ] Other: very large ranges

## Solution Code

```java
import java.util.*;

public class ActivitySelection {
    public static int maxActivities(int[] start, int[] end) {
        int n = start.length;
        int[][] activities = new int[n][2];
        for (int i = 0; i < n; i++) {
            activities[i][0] = start[i];
            activities[i][1] = end[i];
        }
        Arrays.sort(activities, Comparator.comparingInt(o -> o[1]));
        int count = 0;
        int lastEnd = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (activities[i][0] >= lastEnd) {
                count++;
                lastEnd = activities[i][1];
            }
        }
        return count;
    }
}
```

## Alternative Approaches
- Interval scheduling can be solved with dynamic programming, but greedy by earliest finish time is optimal and simpler.

## Personal Notes
- If returning actual indices, carry the original index in the tuple.

---
**Tags:** #arrays #greedy #intervals
