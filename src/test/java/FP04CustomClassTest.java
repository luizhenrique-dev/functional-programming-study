
import domain.Course;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FP04CustomClassTest {

	Course spring = new Course("Spring", "Framework", 98, 20000);
	Course microservices = new Course("Microservices", "Microservices", 96, 25000);
	Course azure = new Course("Azure", "Cloud", 99, 21000);
	Course fullstack = new Course("FullStack", "FullStack", 91, 14000);
	Course defaultCourse = new Course("Default Course", "Default Course", 85, 10000);

	List<Course> courses = List.of(
			spring,
			new Course("Spring Boot", "Framework", 95, 18000),
			new Course("Micronaut", "Framework", 97, 22000),
			new Course("API", "Microservices", 97, 22000),
			microservices,
			fullstack,
			new Course("AWS", "Cloud", 92, 21000),
			azure,
			new Course("Docker", "Cloud", 92, 20000),
			new Course("Kubernetes", "Cloud", 91, 20000)
	);
	//[Spring:20000:98, Spring Boot:18000:95, Micronaut:22000:97, API:22000:97, Microservices:25000:96, FullStack:14000:91, AWS:21000:92, Azure:21000:99, Docker:20000:92, Kubernetes:20000:91]

	@Test
	void getTop5Courses() {
		Comparator<Course> comparingByNoOfStudentsAndNoReviews = Comparator.comparingInt(Course::getNumberOfStudents).thenComparingInt(Course::getReviewScore).reversed();
		List<Course> coursesOrderedByNoOfStudentsAndNoReviewsLimited = courses
				.stream()
				.sorted(comparingByNoOfStudentsAndNoReviews)
				.limit(5)
				.collect(Collectors.toUnmodifiableList());

		assertEquals(5, coursesOrderedByNoOfStudentsAndNoReviewsLimited.size());
		assertEquals(microservices, coursesOrderedByNoOfStudentsAndNoReviewsLimited.get(0));
	}

	@Test
	void skipTop3Results() {
		Comparator<Course> comparingByNoOfStudentsAndNoReviews = Comparator.comparingInt(Course::getNumberOfStudents).thenComparingInt(Course::getReviewScore).reversed();
		List<Course> coursesOrderedByNoOfStudentsAndNoReviewsSkiped = courses
				.stream().sorted(comparingByNoOfStudentsAndNoReviews)
				.skip(3)
				.collect(Collectors.toUnmodifiableList());
		//[Microservices:25000:96, Micronaut:22000:97, API:22000:97, Azure:21000:99, AWS:21000:92, Spring:20000:98, Docker:20000:92, Kubernetes:20000:91, Spring Boot:18000:95, FullStack:14000:91]

		assertEquals(7, coursesOrderedByNoOfStudentsAndNoReviewsSkiped.size());
		assertEquals(azure, coursesOrderedByNoOfStudentsAndNoReviewsSkiped.get(0));
	}

	@Test
	void skipTop3ResultsLimitedTo5() {
		Comparator<Course> comparingByNoOfStudentsAndNoReviews = Comparator.comparingInt(Course::getNumberOfStudents).thenComparingInt(Course::getReviewScore).reversed();
		List<Course> coursesOrderedByNoOfStudentsAndNoReviewsSkiped = courses
				.stream()
				.sorted(comparingByNoOfStudentsAndNoReviews)
				.skip(3)
				.limit(5)
				.collect(Collectors.toUnmodifiableList());
		//[Microservices:25000:96, Micronaut:22000:97, API:22000:97, Azure:21000:99, AWS:21000:92, Spring:20000:98, Docker:20000:92, Kubernetes:20000:91, Spring Boot:18000:95, FullStack:14000:91]

		assertEquals(5, coursesOrderedByNoOfStudentsAndNoReviewsSkiped.size());
		assertEquals(azure, coursesOrderedByNoOfStudentsAndNoReviewsSkiped.get(0));
	}

	@Test
	void returnAllCoursesUntilOneElementDoesntMeetASpecificCriteria() {
		List<Course> coursesUntilRS95 = courses
				.stream()
				.takeWhile(course -> course.getReviewScore() >= 95)
				.collect(Collectors.toUnmodifiableList());
		//[Spring:20000:98, Spring Boot:18000:95, Micronaut:22000:97, API:22000:97, Microservices:25000:96] o próximo FullStack:14000:91 não atende ao criterio portanto a lista foi retornada

		assertEquals(5, coursesUntilRS95.size());
	}

	@Test
		/* Pega o restante que sobrar a partir do elemento ao qual o critério não foi obedecido */
	void skipAllCoursesUntilElementMeetASpecificCriteria() {
		List<Course> coursesDroppedUntilRSGt95 = courses
				.stream()
				.dropWhile(course -> course.getReviewScore() >= 95)
				.collect(Collectors.toUnmodifiableList());
		//[FullStack:14000:91, AWS:21000:92, Azure:21000:99, Docker:20000:92, Kubernetes:20000:91]

		assertEquals(5, coursesDroppedUntilRSGt95.size());
	}

	@Test
		/* Pega o último(máximo) elemento da lista de acordo com o Comparator informado */
	void maxCourseObeyingASpecificCriteria() {
		Comparator<Course> comparingByNoOfStudentsAndNoReviews = Comparator.comparingInt(Course::getNumberOfStudents).thenComparingInt(Course::getReviewScore).reversed();
		Optional<Course> courseWithMaxNoOfStudentsAndNoReviews = courses
				.stream()
				.max(comparingByNoOfStudentsAndNoReviews);

		assertEquals(fullstack, courseWithMaxNoOfStudentsAndNoReviews.orElse(null));
	}

	@Test
		/* Pega o primeiro(mínimo) elemento da lista de acordo com o Comparator informado */
	void minCourseObeyingASpecificCriteria() {
		Comparator<Course> comparingByNoOfStudentsAndNoReviews = Comparator.comparingInt(Course::getNumberOfStudents).thenComparingInt(Course::getReviewScore).reversed();
		Optional<Course> courseWithMaxNoOfStudentsAndNoReviews = courses
				.stream()
				.min(comparingByNoOfStudentsAndNoReviews);

		assertEquals(microservices, courseWithMaxNoOfStudentsAndNoReviews.orElse(null));
	}

	@Test
		/* Pega o primeiro(mínimo) elemento da lista de acordo com o Comparator informado */
	void courseWithScoreUnder90Empty() {
		Predicate<Course> reviewScoreLt90Predicate = course -> course.getReviewScore() < 90;
		Comparator<Course> comparingByNoOfStudentsAndNoReviews = Comparator.comparingInt(Course::getNumberOfStudents).thenComparingInt(Course::getReviewScore).reversed();
		Optional<Course> courseWithReviewScoreLt90 = courses
				.stream()
				.filter(reviewScoreLt90Predicate)
				.min(comparingByNoOfStudentsAndNoReviews);

		assertEquals(Optional.empty(), courseWithReviewScoreLt90);
	}

	@Test
		/* Pega o primeiro(mínimo) elemento da lista de acordo com o Comparator informado */
	void courseWithScoreUnder90ReturnsDefaultCourse() {
		Predicate<Course> reviewScoreLt90Predicate = course -> course.getReviewScore() < 90;
		Comparator<Course> comparingByNoOfStudentsAndNoReviews = Comparator.comparingInt(Course::getNumberOfStudents).thenComparingInt(Course::getReviewScore).reversed();
		Course courseWithReviewScoreLt90 = courses
				.stream()
				.filter(reviewScoreLt90Predicate)
				.min(comparingByNoOfStudentsAndNoReviews)
				.orElse(defaultCourse);

		assertEquals(defaultCourse, courseWithReviewScoreLt90);
	}

	@Test
		/* Pega o primeiro elemento de acordo com o Comparator informado */
	void findFirstCourseThatMeetsASpecificCriteria() {
		Predicate<Course> reviewScoreLt90Predicate = course -> course.getReviewScore() < 90;
		Optional<Course> courseWithReviewScoreLt90 = courses
				.stream()
				.filter(reviewScoreLt90Predicate)
				.findFirst();

		assertEquals(Optional.empty(), courseWithReviewScoreLt90);
	}

	@Test
		/* Pega o primeiro elemento de acordo com o Comparator informado */
	void findFirstCourseThatMeetsASpecificCriteria2() {
		Predicate<Course> reviewScoreGt95Predicate = course -> course.getReviewScore() > 95;
		Optional<Course> courseWithReviewScoreGt95 = courses
				.stream()
				.filter(reviewScoreGt95Predicate)
				.findFirst();

		assertEquals(spring, courseWithReviewScoreGt95.get());
	}

	@Test
		/* Pega o qualquer elemento da stream de acordo com o Comparator informado. É aleatório e não determinístico */
	void findAnyCourseThatMeetsASpecificCriteria() {
		Predicate<Course> reviewScoreGt95Predicate = course -> course.getReviewScore() > 95;
		Optional<Course> courseWithReviewScoreGt95 = courses
				.stream()
				.filter(reviewScoreGt95Predicate)
				.findAny();

		assertEquals(spring, courseWithReviewScoreGt95.get());
	}
}
