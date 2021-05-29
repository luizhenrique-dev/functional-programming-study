package streams;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FP02Number {

	public static void main(String[] args) {
		List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
		printSquaresOfEvenNumbersInList(numbers);
		printCubesOfOddNumbersInList(numbers);
		printTheSumOfSquares(numbers);
		printDistinctValues(numbers);
		printSortedValues(numbers);

		List<Integer> squaredNumbers = squareList(numbers);
		List<Integer> evenNumbersOnly = evenNumbersOnly(numbers);
		System.out.println("Squared: " + squaredNumbers);
		System.out.println("Only Even: " + evenNumbersOnly);
	}

	private static void printSquaresOfEvenNumbersInList(List<Integer> numbers) {
		numbers.stream()
				.filter(number -> number % 2 == 0)
				.map(number -> number * number)
				.forEach(System.out::println);
	}

	private static void printCubesOfOddNumbersInList(List<Integer> numbers) {
		numbers.stream()
				.filter(number -> number % 2 != 0)
				.map(number -> number * number * number)
				.forEach(System.out::println);
	}

	private static void printTheSumOfSquares(List<Integer> numbers) {
		Integer sum = numbers.stream()
				.map(number -> number * number)
				.reduce(0, Integer::sum);
		System.out.println(sum);
	}

	private static void printDistinctValues(List<Integer> numbers) {
		numbers.stream()
				.distinct()
				.forEach(System.out::println);
	}

	private static void printSortedValues(List<Integer> numbers) {
		numbers.stream()
				.distinct()
				.sorted(Comparator.reverseOrder())
				.forEach(System.out::println);
	}

	private static List<Integer> squareList(List<Integer> numbers) {
		return numbers.stream().map(number -> number * number).collect(Collectors.toUnmodifiableList());
	}

	private static List<Integer> evenNumbersOnly(List<Integer> numbers) {
		return numbers.stream().filter(number -> number % 2 == 0).collect(Collectors.toUnmodifiableList());
	}
}
