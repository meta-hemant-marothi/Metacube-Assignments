import java.util.*;

public class ViratKohliProblem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numOfBowlers = getValidatedInput(scanner, "Enter total number of bowlers (must be > 0): ");
        int numOfBallsLeftToBePlayedByVirat = getValidatedInput(scanner, "Enter total number of balls Virat is going to play (must be > 0): ");

        Map<String, Integer> bowlers = getBowlersData(scanner, numOfBowlers);

        System.out.println("\nBowler order:");
        playBalls(bowlers, numOfBallsLeftToBePlayedByVirat, numOfBowlers);
    }

    private static int getValidatedInput(Scanner scanner, String message) {
        int input;
        do {
            System.out.println(message);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next();
            }
            input = scanner.nextInt();
        } while (input <= 0);
        return input;
    }

    private static Map<String, Integer> getBowlersData(Scanner scanner, int numOfBowlers) {
        Map<String, Integer> bowlers = new HashMap<>();
        scanner.nextLine();
        for (int i = 0; i < numOfBowlers; i++) {
            String name;
            do {
                System.out.print("Enter bowler " + (i + 1) + " name: ");
                name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("Invalid input. Bowler name cannot be empty.");
                }
            } while (name.isEmpty());

            int numOfBallsLeft = getValidatedInput(scanner, "Enter " + name + "'s balls left (must be > 0): ");
            bowlers.put(name, numOfBallsLeft);
        }
        return bowlers;
    }

    private static void playBalls(Map<String, Integer> bowlers, int numOfBallsLeftToBePlayedByVirat, int numOfBowlers) {
        PriorityQueue<String> maxHeap = new PriorityQueue<>((o1, o2) -> bowlers.get(o1) - bowlers.get(o2), numOfBowlers);
        for(String bowler : bowlers.keySet()){
            maxHeap.insert(bowler);
        }

        while (numOfBallsLeftToBePlayedByVirat > 0) {
            String currentBowler = maxHeap.poll();
            System.out.print(currentBowler + " -> ");

            int ballsLeft = bowlers.get(currentBowler) - 1;
            bowlers.put(currentBowler, ballsLeft);

            if (ballsLeft > 0) {
                maxHeap.insert(currentBowler);
            }

            numOfBallsLeftToBePlayedByVirat--;
        }
    }
}