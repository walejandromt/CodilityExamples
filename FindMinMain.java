package main;

import java.util.concurrent.ThreadLocalRandom;

public class FindMinMain {

	public static void main(String[] args) {
		FindMinMain p = new FindMinMain();
		int[] array = p.solution(4);
		int min = p.find_min(array);
		System.out.println(min);
	}

	private static final Integer MIN = 1;
	private static final Integer MAX = 1000;

	public int[] solution(int N) {
		// write your code in Java SE 8
		return generateArray(N);
	}

	private int[] generateArray(int size) {
		int[] array = new int[size];
		for (int x = 0; x < size; x++) {
			array[x] = generateRamdumNumber();
			System.out.println("Number -> " + array[x]);
		}

		System.out.println("Array -> " + array);
		return array;
	}

	private int generateRamdumNumber() {
		return ThreadLocalRandom.current().nextInt(MIN, MAX);
	}

	int find_min(int[] A) {
		int ans = 0;
		for (int i = 1; i < A.length; i++) {
			if (ans > A[i]) {
				ans = A[i];
			}
		}
		return ans;
	}

}
