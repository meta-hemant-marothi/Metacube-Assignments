
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import java.util.PriorityQueue;

// import java.util.*;

// public class TaskScheduler {
//     public static int leastInterval(char[] tasks, int n) {
//         Map<Character, Integer> taskCounts = new HashMap<>();
        
//         // Count the occurrences of each task
//         for (char task : tasks) {
//             taskCounts.put(task, taskCounts.getOrDefault(task, 0) + 1);
//         }
        
//         // Create a max heap based on task counts
//         PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
//         maxHeap.addAll(taskCounts.values());
        
//         int intervals = 0;

//         while (!maxHeap.isEmpty()) {
//             List<Integer> tempList = new ArrayList<>();
//             int cooldown = n + 1; // Number of slots in one cycle

//             // Process the cycle
//             while (cooldown > 0 && !maxHeap.isEmpty()) {
//                 tempList.add(maxHeap.poll());
//                 cooldown--;
//             }

//             // Update task counts and add back to the heap
//             for (int count : tempList) {
//                 if (count - 1 > 0) {
//                     maxHeap.add(count - 1);
//                 }
//             }

//             // Add to intervals
//             intervals += maxHeap.isEmpty() ? tempList.size() : n + 1;
//         }

//         return intervals;
//     }

//     public static void main(String[] args) {
//         char[] tasks1 = {'A', 'A', 'A', 'B', 'B', 'B'};
//         int n1 = 2;
//         System.out.println("Minimum intervals: " + leastInterval(tasks1, n1)); // Output: 8

//         char[] tasks2 = {'A', 'C', 'A', 'B', 'D', 'B'};
//         int n2 = 1;
//         System.out.println("Minimum intervals: " + leastInterval(tasks2, n2)); // Output: 6

//         char[] tasks3 = {'A', 'A', 'A', 'B', 'B', 'B'};
//         int n3 = 3;
//         System.out.println("Minimum intervals: " + leastInterval(tasks3, n3)); // Output: 10
//     }
// }


public class TaskScheduler{
    public int minimumIntervals(char[] tasks, int cooldown){
        Map<Character, Integer> tasksCount = new HashMap<>();
        for(char task : tasks){
            tasksCount.put(task, tasksCount.getOrDefault(task, 0) + 1);
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(tasksCount.values());

        while(!maxHeap.isEmpty()){
            List<Integer> tempList = new ArrayList<>();
            cooldown += 1;
        }
    }
}