package functional_interfaces;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FP03BehaviorParametrization {

	public static void main(String[] args) {
		List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

		Function<Integer, Integer> squaredFunction = x -> x * x;
		List<Integer> squaredNumbers = mapAndCreateNewList(numbers, x -> x * x);
		List<Integer> squaredNumbers2 = mapAndCreateNewList(numbers, squaredFunction);
		List<Integer> cubedNumbers = mapAndCreateNewList(numbers, x -> x * x * x);
		List<Integer> doubledNumbers = mapAndCreateNewList(numbers, x -> x + x);

		System.out.println(squaredNumbers);
		System.out.println(squaredNumbers2);
		System.out.println(cubedNumbers);
		System.out.println(doubledNumbers);
	}

	private static List<Integer> mapAndCreateNewList(List<Integer> numbers, Function<Integer, Integer> function) {
		return numbers.stream()
				.map(function)
				.collect(Collectors.toUnmodifiableList());
	}
}
