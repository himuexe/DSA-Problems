# Shortest Path - AC Strings

## Problem Statement
**Difficulty:** Medium  
**Topic:** Coordinate Geometry, String Processing  
**Source:** Apna College (AC)

Given a string representing movement directions (N, S, E, W), calculate the shortest path distance between starting point (0,0) and final position.

**Example:**
- Input: `"WNEENESENNN"`
- Output: `5` (straight-line distance from origin to final position)

## Intuition/Approach
**Algorithm: Coordinate Tracking + Euclidean Distance**
1. **Track position** - Maintain x,y coordinates starting from (0,0)
2. **Process directions** - Update coordinates based on movement commands
3. **Calculate displacement** - Find final position relative to origin
4. **Compute distance** - Use Euclidean distance formula

**Key Insight:** Shortest path is the straight-line distance regardless of actual path taken.

## Key Observations
- **Coordinate system** - Standard 2D plane with origin at (0,0)
- **Direction mapping** - W/E affect x-coordinate, N/S affect y-coordinate
- **Distance calculation** - Euclidean formula: √(x² + y²)
- **Path optimization** - Direct distance ignores intermediate movements

## Algorithm Implementation
```java
public static int calcPath(String str){
    int x=0, y=0;
    for(int i=0;i<str.length();i++){
        char dir = str.charAt(i);
        if(dir == 'W'){
            x--;
        }
        else if(dir == 'N'){
            y++;
        }
        else if(dir == 'S'){
            y--;
        }
        else{
            x++;
        }
    }
    return (int)Math.sqrt((x*x)+(y*y));
}
```

## Complexity Analysis
- **Time Complexity:** O(n) - Single pass through direction string
- **Space Complexity:** O(1) - Only storing coordinate variables
- **Mathematical Operations:** Constant time square root calculation

## Edge Cases Considered
1. **Return to origin** - Distance = 0 when final position is (0,0)
2. **Single direction** - Direct movement to final position
3. **Circular path** - May end up close to or at origin
4. **Long path** - Distance depends only on final displacement
5. **Invalid characters** - Current code assumes valid NSEW input

## Alternative Approaches
1. **Vector approach** - Track displacement vector directly
2. **Complex numbers** - Use complex arithmetic for 2D movement
3. **Path tracking** - Store all positions (unnecessary for this problem)
4. **Manhattan distance** - Alternative distance metric (not shortest path)

## LeetCode Connection
- **Similar Problems:** Robot movement, coordinate tracking
- **Pattern:** Position simulation problems
- **Related:** Grid-based pathfinding problems

## Cross-References
- **Related AC Problems:** N/A - unique coordinate geometry problem
- **Similar Kunal Problems:** N/A - specific to geometric calculations
- **Core Concept:** Coordinate geometry and distance calculation

---
**Learning Focus:** Coordinate system manipulation, Euclidean distance, geometric problem solving 