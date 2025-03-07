# Problem Statement
Design a class JobScheduler to simulate FCFS (First Come First Serve) scheduling algorithm. 
FCFS means the process which arrives first, gets executed first. 

Assume that we are receiving a number of processes with their arrival time and burst time seconds in a two dimensional array as input.  For example:

[0][10]
[6][20]
[60][10]
[110][5]

Define method to perform following operations: 

Calculate completion time for each process. 
Calculate waiting time for each process.
Calculate turn around time for each process.
Average waiting time of processes.
Maximum waiting time period for a process in queue.


Here we have simple formulae for calculating various times for given processes:

1. Completion Time: Time taken for the execution to complete, starting from arrival time of first process.
2. Turn Around Time: Time taken to complete after arrival. In simple words, it is the difference between the Completion time and the Arrival time.
3. Waiting Time: Total time the process has to wait before it's execution begins. It is the difference between the Turn Around time and the Burst time of the process.
4. Burst Time : Time required to execute a process.
