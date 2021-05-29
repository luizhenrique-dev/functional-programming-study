package streams;

import java.util.List;

public class FP02Functional {

	public static void main(String[] args) {
		List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

		int sum = addList(numbers);
		System.out.println("Sum: " + sum);

		int maxValue = maxValue(numbers);
		System.out.println("Max value: " + maxValue);

		int minValue = minValue(numbers);
		System.out.println("Min value: " + minValue);
	}

	private static int addList(List<Integer> numbers) {
		return numbers.stream()
//		.reduce(0, streams.FP02Functional::sum);
//		.reduce(0, (aggregate, nextNumber) -> aggregate + nextNumber);
				.reduce(0, Integer::sum);
	}

	private static int sum(int aggregate, int nextNumber) {
		return aggregate + nextNumber;
	}

	private static int maxValue(List<Integer> numbers) {
		return numbers.stream()
				.reduce(Integer.MIN_VALUE, FP02Functional::calculateMaxValue);
	}

	private static int minValue(List<Integer> numbers) {
		return numbers.stream()
				.reduce(Integer.MAX_VALUE, FP02Functional::calculateMinValue);
	}

	private static int calculateMaxValue(int value, int valueComparable) {
		return Math.max(value, valueComparable);
	}

	private static int calculateMinValue(int value, int valueComparable) {
		return Math.min(value, valueComparable);
	}

}
