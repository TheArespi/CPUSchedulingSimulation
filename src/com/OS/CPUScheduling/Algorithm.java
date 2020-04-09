package com.OS.CPUScheduling;

import java.util.*;

class Job {
	
	public static HashMap<String, Integer> nameList = new HashMap<>();
	
	public String name;
	public int arrivalTime;
	public int burstTime;
	public int priority;
	public boolean finished;
	public boolean arrived;
	
	public Job(String name, int arrivalTime, int burstTime, int priority) {
		if (nameList.containsKey(name)) {
			this.name = name + Integer.toString(nameList.get(name));
			nameList.put(name, nameList.get(name) + 1);
		} else {
			this.name = name;
			nameList.put(name, 1);
		
		}
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.priority = priority;
		
		finished = false;
		arrived = false;
	}
}

class Schedule {
	
	public static int endOfGanttChart = 0;
	
	public String name;
	public int burstTime;
	public int serviceTime;
	public int endTime;
	
	public Schedule(Job job, int burstTime) {
		this.name = job.name;
		this.burstTime = burstTime;
		this.serviceTime = endOfGanttChart;
		this.endTime = endOfGanttChart + burstTime;
		
		endOfGanttChart = endTime;
	}
}

class Result {
	public String name;
	public int turnAroundTime;
	public int waitingTime;
}

public class Algorithm {
	
	private static List<Job> sortByArrivalTime(List<Job> jobs) {
		List<Job> sortedJobs = new ArrayList<>();
		int jobsSize = jobs.size();
		
		for (int i = 0; i < jobsSize; i++) {
			Job earliest = null;
			for (Job job : jobs) {
				if (earliest == null || earliest.arrivalTime > job.arrivalTime)
					earliest = job;
			}
			sortedJobs.add(earliest);
			jobs.remove(earliest);
		}
		
		return sortedJobs;
	}
	
	private static List<Job> sortByBurstTime(List<Job> jobs) {
		List<Job> sortedJobs = new ArrayList<>();
		int jobsSize = jobs.size();
		
		for (int i = 0; i < jobsSize; i++) {
			Job smallest = null;
			for (Job job : jobs) {
				if (smallest == null || smallest.burstTime > job.burstTime)
					smallest = job;
			}
			sortedJobs.add(smallest);
			jobs.remove(smallest);
		}
		
		return sortedJobs;
	}
	
	//1 is higher in priority than 2
	private static List<Job> sortByPriority(List<Job> jobs) {
		List<Job> sortedJobs = new ArrayList<>();
		int jobsSize = jobs.size();
		
		for (int i = 0; i < jobsSize; i++) {
			Job prioritized = null;
			for (Job job : jobs) {
				if (prioritized == null || prioritized.priority > job.priority)
					prioritized = job;
			}
			sortedJobs.add(prioritized);
			jobs.remove(prioritized);
		}
		
		return sortedJobs;
	}
	
	public static List<Schedule> firstComeFirstServe(List<Job> jobs){
		List<Schedule> ganttChart = new ArrayList<>();
		
		jobs = sortByArrivalTime(jobs);
		
		for (Job job : jobs)
			ganttChart.add(new Schedule(job,job.burstTime));
		
		return ganttChart;
	}
	
	public static List<Schedule> shortestJobFirst(List<Job> jobs){
		List<Schedule> ganttChart = new ArrayList<>();
		
		jobs = sortByBurstTime(jobs);
		
		for (Job job : jobs)
			ganttChart.add(new Schedule(job,job.burstTime));
		
		return ganttChart;
	}
	
	public static List<Schedule> nonPreemptivePriority(List<Job> jobs){
		List<Schedule> ganttChart = new ArrayList<>();
		
		jobs = sortByPriority(jobs);
		
		for (Job job : jobs)
			ganttChart.add(new Schedule(job,job.burstTime));
		
		return ganttChart;
	}
	
	public static List<Schedule> shortestRemainingTimeFirst(List<Job> jobs){
		List<Schedule> ganttChart = new ArrayList<>();
		
		return ganttChart;
	}
	
	public static List<Schedule> preemptivePriority(List<Job> jobs){
		List<Schedule> ganttChart = new ArrayList<>();
		
		return ganttChart;
	}
	
	public static List<Schedule> roundRobin(List<Job> jobs, int quantumTime){
		List<Schedule> ganttChart = new ArrayList<>();
		
		return ganttChart;
	}
	
	public static void main(String args[]) {
		List<Job> jobs = new ArrayList<>();
		jobs.add(new Job("A",0,2,0));
		jobs.add(new Job("B",1,3,1));
		List<Schedule> gantt = firstComeFirstServe(jobs);
		
		for (Schedule sched : gantt) {
			System.out.println("arrived: " + sched.serviceTime + " " + sched.name + "[" + sched.burstTime + "] end: " + sched.endTime); 
		}
	}
	
}
