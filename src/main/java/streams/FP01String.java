package streams;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FP01String {

	public static void main(String[] args) {
		List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes", "Olho");
		courses.stream().filter(FP01String::containSpringWord).forEach(FP01String::print);
		courses.stream().filter(course -> course.contains("Spring")).forEach(System.out::println);
		courses.stream().filter(FP01String::containAtLeast4Letters).forEach(FP01String::print);
		printNumberCharacters(courses);
		printSortedWithCustomizedComparator(courses);

		List<Integer> lengthCourseList = lengthCourseList(courses);
		System.out.println(lengthCourseList);
	}

	private static boolean containAtLeast4Letters(final String valueToCheck) {
		return valueToCheck.length() >= 4;
	}

	private static boolean containSpringWord(final String valueToCheck) {
		return valueToCheck.contains("Spring");
	}

	private static void printNumberCharacters(final List<String> courses) {
		courses.stream()
				.map(course -> course + " - " + course.length())
				.forEach(System.out::println);
	}

	private static void print(final String toPrint) {
		System.out.println(toPrint);
	}

	private static void printSortedWithCustomizedComparator(List<String> courses) {
		courses.stream()
				.sorted(Comparator.comparing(String::length))
				.forEach(System.out::println);
	}

	private static List<Integer> lengthCourseList(List<String> courses) {
		return courses.stream().map(String::length).collect(Collectors.toUnmodifiableList());
	}
}
