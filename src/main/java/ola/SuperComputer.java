package ola;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SuperComputer {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println(new SuperComputer().solve(in));
	}

	public String solve(Scanner in) {
		int n = in.nextInt();
		in.nextLine();

		PriorityQueue<Task> endTime = new PriorityQueue<>(Comparator.comparingInt(Task::getEnd));
		PriorityQueue<Task> startTime = new PriorityQueue<>(Comparator.comparingInt(Task::getStart));
		int noOfTasks = 0;

		for (int i = 0 ; i < n ; i++) {
			Task e = new Task(in.nextInt(), in.nextInt());
			endTime.add(e);
			startTime.add(e);
			in.nextLine();
		}

		while (!endTime.isEmpty()) {
			Task t = endTime.poll();

			while (!endTime.isEmpty() && t.getEnd() > endTime.peek().getStart()) {
				endTime.poll();
			}

			noOfTasks++;
		}

		return String.valueOf(noOfTasks);
	}
	public static class Task {
		final int start;
		final int time;

		public Task(int start, int time) {
			this.start = start;
			this.time = time;
		}

		public int getStart() {
			return start;
		}

		public int getEnd() {
			return start + time;
		}

		@Override
		public String toString() {
			return "Task{" +
					"start=" + start +
					", time=" + time +
					", endTime=" + getEnd() +
					'}';
		}
	}
}
