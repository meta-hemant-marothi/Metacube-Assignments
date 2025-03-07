public class CpuProcess {
    private static double avgWaitingTime;
    private static int maxWaitingTime;
    private static int uniqueId = 1;

    public static double getAvgWaitingTime() {
        return avgWaitingTime;
    }

    public static void setAvgWaitingTime(double avgWaitingTime) {
        CpuProcess.avgWaitingTime = avgWaitingTime;
    }
    
    public static int getMaxWaitingTime() {
        return maxWaitingTime;
    }

    public static void setMaxWaitingTime(int maxWaitingTime) {
        CpuProcess.maxWaitingTime = maxWaitingTime;
    }

    private int arrivalTime;
    private int burstTime;
    private int completionTime;
    private int waitingTime;
    private int turnAroundTime;
    private int processId;

    CpuProcess(int arrivalTime, int burstTime){
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.processId = uniqueId++;
    }

    public int getArrivalTime(){
        return arrivalTime;
    }
    
    public void setArrivalTime(int arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime(){
        return burstTime;
    }

    public void setBurstTime(int burstTime){
        this.burstTime = burstTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }
    
}
