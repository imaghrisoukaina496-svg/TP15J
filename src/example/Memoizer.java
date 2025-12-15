package example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class Memoizer {
	public static void main(String[] args) {
		System.out.println("Version non-memoized:");
		long start = System.currentTimeMillis();
		System.out.println("fibonacci(10) = " + fibo(10));
		System.out.println("Temps: " + (System.currentTimeMillis() - start) + "ms");

		System.out.println("\nVersion memoized:");
		Function<Integer, Long> fibMemo = memoize(Memoizer::fibo);
		start = System.currentTimeMillis();
		System.out.println("fibonacci(10) = " + fibMemo.apply(10));
		System.out.println("Temps: " + (System.currentTimeMillis() - start) + "ms");

		System.out.println("\nDeuxième appel memoized (cache):");
		start = System.currentTimeMillis();
		System.out.println("fibonacci(10) = " + fibMemo.apply(10));
		System.out.println("Temps: " + (System.currentTimeMillis() - start) + "ms");
	}

	private static long fibo(int n) {
		if (n <= 1)
			return n;
		return fibo(n - 1) + fibo(n - 2);
	}

	public static <T, R> Function<T, R> memoize(Function<T, R> function) {
		Map<T, R> cache = new ConcurrentHashMap<>();
		return input -> cache.computeIfAbsent(input, function);
	}
}
