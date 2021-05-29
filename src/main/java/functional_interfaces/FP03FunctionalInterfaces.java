package functional_interfaces;

import java.util.List;
import java.util.Random;
import java.util.function.*;

public class FP03FunctionalInterfaces {

	public static void main(String[] args) {
		List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

		/** Functional Interface possui apenas UM método abstrato. Predicate é usado para testar algo com um argumento. */
		Predicate<Integer> isEvenPredicate = x -> x % 2 == 0;
		Predicate<Integer> isEvenPredicate2 = new Predicate<Integer>() {
			@Override
			public boolean test(Integer x) {
				return x % 2 == 0;
			}
		};

		/** Representa uma função que aceita um argumento e produz um resultado. */
		Function<Integer, Integer> squareFunction = x -> x * x;
		Function<Integer, Integer> squareFunction2 = new Function<Integer, Integer>() {
			@Override
			public Integer apply(Integer x) {
				return x * x;
			}
		};

		/** Representa uma operação que aceita apenas um argumento e não tem nenhum retorno. */
		Consumer<Integer> sysoutConsumer = System.out::println;
		Consumer<Integer> sysoutConsumer2 = new Consumer<Integer>() {
			@Override
			public void accept(Integer x) {
				System.out.println(x);
			}
		};

		System.out.println("------Exemplos de Functional Interfaces -------");
		numbers.stream()
				.filter(isEvenPredicate)
				.map(squareFunction)
				.forEach(sysoutConsumer);
		System.out.println("-------------");
		numbers.stream()
				.filter(isEvenPredicate2)
				.map(squareFunction2)
				.forEach(sysoutConsumer2);

		System.out.println("-----SUPPLIER------");
		/** Representa uma operação que não possui entrada mas tem algum retorno/saída */
		Supplier<Integer> randomInteger = () -> {
			Random random = new Random();
			return random.nextInt(1000);
		};
		System.out.println(randomInteger.get());

		System.out.println("------UNARY OPERATOR-------");
		/** Representa uma operação que possui uma entrada realiza uma ação e tem uma saída do mesmo tipo */
		UnaryOperator<Integer> unaryOperator = (x) -> 3 * x;
		System.out.println(unaryOperator.apply(10));
	}
}
