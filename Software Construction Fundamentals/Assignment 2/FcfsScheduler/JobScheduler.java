import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class JobScheduler {
    private static int timeUtilized;

    JobScheduler() {
        timeUtilized = 0;
    }

    /**
     * This Function Calculates the completion time, turn around time, waiting time,
     * average waiting time and maximum waiting time.
     * @param processList
     */
    public static void calculateTimes(ArrayList<CpuProcess> processList){
        processList.sort(Comparator.comparingInt(CpuProcess::getArrivalTime));
        int totalWaitingTime = 0;
        for(CpuProcess process : processList){
            timeUtilized = Math.max(timeUtilized, process.getArrivalTime());
            process.setCompletionTime(process.getBurstTime() + timeUtilized);
            process.setTurnAroundTime(process.getCompletionTime() - process.getArrivalTime());
            process.setWaitingTime(timeUtilized - process.getArrivalTime());
            CpuProcess.setMaxWaitingTime(Math.max(CpuProcess.getMaxWaitingTime(), process.getWaitingTime()));
            totalWaitingTime += process.getWaitingTime();
            timeUtilized = process.getCompletionTime();
        }
        CpuProcess.setAvgWaitingTime(totalWaitingTime / processList.size());
    }

    /**
     * This function systematically displays each process and its associated values.
     * @param processList
     */
    public static void displayResult(ArrayList<CpuProcess> processList){
        System.out.println("\n+===========================================================+");
        System.out.println("|\t\t     Scheduled Jobs List     \t\t|");
        System.out.println("+===========================================================+");
        System.out.println("| PID | Arrival | Burst | Completion | Waiting | TurnAround |");
        System.out.println("+===========================================================+");
        for(CpuProcess process : processList){
            System.out.printf("| %-3d | %-7d | %-5d | %-10d | %-7d | %-10d |\n",
            process.getProcessId(), process.getArrivalTime(), process.getBurstTime(),
            process.getCompletionTime(), process.getWaitingTime(), process.getTurnAroundTime());
        }
        System.out.println("+===========================================================+");
        System.out.println("| Average Waiting Time: " + CpuProcess.getAvgWaitingTime());
        System.out.println("| Maximum Waiting Time: " + CpuProcess.getMaxWaitingTime());
        System.out.println("+===========================================================+");
    }

    /**
     * This Function is to get a valid integer input in the given range.
     * @param sc
     * @param min
     * @param max
     * @return valid integer.
     */
    public static int getNumInput(Scanner sc, int min, int max){
        int num;
        while(true){
            try{
                num = sc.nextInt();
                sc.nextLine();
                if(num >= min && num <= max)return num;
                else System.out.println("Enter a valid number between " + min + " & " + max);
            }catch(Exception e){
                System.out.println("Enter a valid number between " + min + " & " + max);
                sc.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("======== Welcome to FCFS Job Scheduler ========");
        System.out.print("Enter number of processes: ");
        int numProcesses = getNumInput(sc, 0, 20);
        int arrivalTime, burstTime;
        ArrayList<CpuProcess> processList = new ArrayList<>();
        for(int i = 1; i <= numProcesses; i++){
            System.out.println("$$ Process " + i +":");
            System.out.print("Enter Arrival Time: ");
            arrivalTime = getNumInput(sc, 0, 500);
            System.out.print("Enter Burst Time: ");
            burstTime = getNumInput(sc, 0, 500);
            CpuProcess process = new CpuProcess(arrivalTime, burstTime);
            processList.add(process);
        }
        calculateTimes(processList);
        displayResult(processList);
    }


    
}
