
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class ViratKohliProblem{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter total number of bowlers: ");
        int numOfBowlers = scanner.nextInt();
        Map<String, Integer> bowlers = new HashMap<>(numOfBowlers);
        for(int i = 0; i < numOfBowlers; i++){
            System.out.print("Enter " + i + " Bowlers name: ");
            String name = scanner.nextLine();
            System.out.print("Enter his/her balls left: ");
            int numOfBallsLeft = scanner.nextInt();
            bowlers.put(name, numOfBallsLeft);
        }

        PriorityQueue<Map<String, Integer>> maxHeap = new PriorityQueue<>((o1, o2) -> o1 - o2, numOfBowlers);
        
    }


}