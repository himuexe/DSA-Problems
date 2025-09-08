# Implement Stack Using Two Queues

**Source:** AC | **Topic:** Queues | **Difficulty:** Easy  

---

## Problem Statement
Implement a stack (LIFO) using two queues with `push`, `pop`, and `peek`.

## Intuition/Approach
Maintain two queues `q1` and `q2`. To push, add to the non-empty queue. To pop/peek, move elements from the non-empty queue to the other until one is left; return the last element.

## Key Observations
- This variant makes `push` O(1) and `pop`/`peek` O(n).
- An alternative variant reverses the costs by reordering on push.
- Guard for empty stack before operations.

## Algorithm Steps
1. Push: if `q1` not empty, `q1.add(x)` else `q2.add(x)`.
2. Pop: move elements until one remains in non-empty queue; return it; others go to the other queue.
3. Peek: same as pop but put last element back into the other queue.

## Complexity Analysis
- **Time Complexity:** Push O(1), Pop O(n), Peek O(n)
- **Space Complexity:** O(n)

## Edge Cases Considered
- [x] Pop/peek on empty
- [x] Multiple pushes before a pop
- [x] Single element

## Solution Code

```java
import java.util.*;
public class StackQ {
    static class Stack{
        static Queue<Integer> q1 = new LinkedList<>();
        static Queue<Integer> q2 = new LinkedList<>();
        public static boolean isEmpty(){
            return q1.isEmpty() && q2.isEmpty();
        }
        public static void push(int data){
            if(!q1.isEmpty()){
                q1.add(data);
            } else {
                q2.add(data);
            }
        }
        public static int pop(){
            if(isEmpty()){
                System.out.println("Stack is empty");
                return -1;
            }
            int top = -1;
            if(!q1.isEmpty()){
                while(!q1.isEmpty()){
                    top = q1.remove();
                    if(q1.isEmpty()){
                        break;
                    }
                    q2.add(top);
                }
            } else {
                while(!q2.isEmpty()){
                    top = q2.remove();
                    if(q2.isEmpty()){
                        break;
                    }
                    q1.add(top);
                }
            }
            return top;
        }
        public static int peek(){
            if(isEmpty()){
                System.out.println("Stack is empty");
                return -1;
            }
            int top = -1;
            if(!q1.isEmpty()){
                while(!q1.isEmpty()){
                    top = q1.remove();
                    q2.add(top);
                }
            } else {
                while(!q2.isEmpty()){
                    top = q2.remove();
                    q1.add(top);
                }
            }
            return top;
        }
    }
}
```

## Alternative Approaches
- Make `push` O(n) by reordering such that newest is always at front of the non-empty queue; then pop O(1).

## Personal Notes
Choose variant based on operation distribution.

---
**Tags:** #stack #queues #implementation
