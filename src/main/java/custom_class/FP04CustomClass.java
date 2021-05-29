package custom_class;

import domain.Course;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FP04CustomClass {
	public static void main(String[] args) {
		List<Course> courses = List.of(
				new Course("Spring", "Framework", 98, 20000),
				new Course("Spring Boot", "Framework", 95, 18000),
				new Course("Micronaut", "Framework", 97, 22000),
				new Course("API", "Microservices", 97, 22000),
				new Course("Microservices", "Microservices", 96, 25000),
				new Course("FullStack", "FullStack", 91, 14000),
				new Course("AWS", "Cloud", 92, 21000),
				new Course("Azure", "Cloud", 99, 21000),
				new Course("Docker", "Cloud", 92, 20000),
				new Course("Kubernetes", "Cloud", 91, 20000)
		);

		Predicate<Course> reviewScoreGt95Predicate = course -> course.getReviewScore() > 95;
		//Check courses with high scores over than 90
		List<Course> coursesWithHighScore = courses.stream()
				.filter(reviewScoreGt95Predicate)
				.collect(Collectors.toUnmodifiableList());
		System.out.println(coursesWithHighScore);
//		[Spring:20000:98, Micronaut:22000:97, API:22000:97, Microservices:25000:96, Azure:21000:99]

		//allMatch, similar to every on Groovy, should be true
		boolean allCoursesWithHighScore = courses.stream().allMatch(course -> course.getReviewScore() > 90);
		System.out.println(allCoursesWithHighScore);

		//allMatch, similar to 'every' on Groovy, should be false
		boolean allCoursesWithVeryHighScore = courses.stream().allMatch(reviewScoreGt95Predicate);
		System.out.println(allCoursesWithVeryHighScore);

		//noneMatch, should be false
		boolean noneCoursesWithVeryHighScore = courses.stream().noneMatch(reviewScoreGt95Predicate);
		System.out.println(noneCoursesWithVeryHighScore);

		Predicate<Course> reviewScoreLt90Predicate = course -> course.getReviewScore() < 90;
		//noneMatch, should be true
		boolean noneCoursesWithLess90Score = courses.stream().noneMatch(reviewScoreLt90Predicate);
		System.out.println(noneCoursesWithLess90Score);

		//anyMatch, similar to 'any' on Groovy, should be true
		boolean anyCourseWithHigher95Score = courses.stream().anyMatch(reviewScoreGt95Predicate);
		System.out.println(anyCourseWithHigher95Score);

		Comparator<Course> comparingByNoOfStudentsIncreasing = Comparator.comparingInt(Course::getNumberOfStudents);

		Comparator<Course> comparingByNoOfStudentsDecreasing = Comparator.comparingInt(Course::getNumberOfStudents).reversed();

		List<Course> coursesOrderedByNoOfStudents = courses.stream().sorted(comparingByNoOfStudentsIncreasing).collect(Collectors.toUnmodifiableList());
		List<Course> coursesOrderedByNoOfStudentsDecreasing = courses.stream().sorted(comparingByNoOfStudentsDecreasing).collect(Collectors.toUnmodifiableList());
		System.out.println(coursesOrderedByNoOfStudents);
		//[FullStack:14000:91, Spring Boot:18000:95, Spring:20000:98, Docker:20000:92, Kubernetes:20000:91, AWS:21000:92, Azure:21000:99, Micronaut:22000:97, API:22000:97, Microservices:25000:96]
		System.out.println(coursesOrderedByNoOfStudentsDecreasing);
		//[Microservices:25000:96, Micronaut:22000:97, API:22000:97, AWS:21000:92, Azure:21000:99, Spring:20000:98, Docker:20000:92, Kubernetes:20000:91, Spring Boot:18000:95, FullStack:14000:91]

		Comparator<Course> comparingByNoOfStudentsAndNoReviews = Comparator.comparingInt(Course::getNumberOfStudents).thenComparingInt(Course::getReviewScore).reversed();
		List<Course> coursesOrderedByNoOfStudentsAndNoReviews = courses.stream().sorted(comparingByNoOfStudentsAndNoReviews).collect(Collectors.toUnmodifiableList());
		System.out.println("Multiple comparisons: " + coursesOrderedByNoOfStudentsAndNoReviews);
		//[Microservices:25000:96, Micronaut:22000:97, API:22000:97, Azure:21000:99, AWS:21000:92, Spring:20000:98, Docker:20000:92, Kubernetes:20000:91, Spring Boot:18000:95, FullStack:14000:91]

	}
}
