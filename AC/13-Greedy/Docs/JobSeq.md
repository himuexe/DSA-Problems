# Job Sequencing with Deadlines (Max Profit)

**Source:** AC | **Topic:** Greedy | **Difficulty:** Medium  

---

## Problem Statement
Given jobs with deadlines and profits, schedule at most one job per unit time to maximize total profit. Each job takes a single time slot and must be completed before or on its deadline.

## Intuition/Approach
Sort jobs by profit descending and greedily place each job in the latest available time slot on or before its deadline. This preserves capacity for earlier slots for other jobs.

## Key Observations
- Always pick higher profit jobs first.
- Place as late as possible to keep earlier slots flexible.
- The maximum deadline bounds the timeline length.

## Algorithm Steps
1. Create job objects `(id, deadline, profit)` and sort by `profit` descending.
2. Compute `maxDeadline` to size the time slot array.
3. For each job in sorted order:
   - Find the latest free slot `<= deadline` and assign.
   - Accumulate profit and record job id.
4. Output scheduled jobs and total profit.

## Complexity Analysis
- **Time Complexity:** O(N log N + N * D) where D is max deadline
- **Space Complexity:** O(D)
- **Justification:** Sort dominates; scanning up to deadline per job.

## Edge Cases Considered
- [x] Multiple jobs with same deadline
- [x] Deadlines smaller than available slots
- [ ] Very large deadlines (optimize with DSU)
- [ ] Zero-profit jobs
- [ ] Other: Empty job list

## Solution Code

```java
import java.util.*;

public class JobSeq {
    static class Job {
        int deadline, profit, id;
        Job(int id, int deadline, int profit) {
            this.id = id; this.deadline = deadline; this.profit = profit;
        }
    }

    public static void main(String[] args) {
        int jobsInfo[][] = {{4, 20}, {1, 10}, {1, 40}, {1, 30}};
        ArrayList<Job> jobs = new ArrayList<>();
        for (int i = 0; i < jobsInfo.length; i++) {
            jobs.add(new Job(i, jobsInfo[i][0], jobsInfo[i][1]));
        }
        Collections.sort(jobs, (a, b) -> b.profit - a.profit);
        int maxDeadline = 0;
        for (Job job : jobs) maxDeadline = Math.max(maxDeadline, job.deadline);
        boolean[] slots = new boolean[maxDeadline + 1];
        ArrayList<Integer> seq = new ArrayList<>();
        int totalProfit = 0;
        for (Job curr : jobs) {
            for (int t = Math.min(maxDeadline, curr.deadline); t > 0; t--) {
                if (!slots[t]) {
                    slots[t] = true;
                    seq.add(curr.id);
                    totalProfit += curr.profit;
                    break;
                }
            }
        }
        System.out.println("The jobs selected are:");
        for (int id : seq) System.out.print("J" + id + " ");
        System.out.println("\nTotal profit: " + totalProfit);
    }
}
```

## Alternative Approaches
- Disjoint Set Union (Union-Find) to jump to previous free slot in near O(α(N)).
- Min-heap by profit keeping at most D jobs; schedule after processing all.

## Personal Notes
Union-Find optimization reduces slot search from O(D) to near O(1) amortized.

---
**Tags:** #greedy #scheduling #sorting #unionfind
