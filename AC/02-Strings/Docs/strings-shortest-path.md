# Shortest Path

**Source:** AC | **Topic:** Strings/Geometry | **Difficulty:** Medium  

---

## Problem Statement
Given a string of directions (N, S, E, W), compute the shortest straight-line distance from the origin to the final position.

## Intuition/Approach
Simulate movement on a 2D grid to find final coordinates, then apply Euclidean distance from origin.

## Key Observations
- W/E change x; N/S change y
- Straight-line distance ignores the actual path
- Euclidean distance: sqrt(x^2 + y^2)

## Algorithm Steps
1. Initialize x = 0, y = 0
2. For each character dir in input: update x or y
3. Compute distance = floor of sqrt(x^2 + y^2) as per implementation
4. Return distance

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass; constant extra storage

## Edge Cases Considered
- [x] Return to origin → distance 0
- [x] Single direction
- [x] Circular path
- [x] Long path with many back-and-forth moves
- [ ] Invalid characters (assumed valid input)

## Solution Code

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

## Alternative Approaches
- Track displacement vector only
- Use complex numbers to encode moves
- Manhattan distance (not shortest path)

## Personal Notes
- Matches common robot movement simulation tasks

---
**Tags:** #strings #geometry #simulation