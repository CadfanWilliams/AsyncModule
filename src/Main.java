import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) throws InterruptedException {
       getWebpageRunnable runnable = new getWebpageRunnable();
       Thread thread = new Thread(runnable);
        System.out.println("exercise 1");
        thread.start();
        thread.join();

        System.out.println("exercise 2");
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 6; i++) {
            pool.submit(runnable);
        }
        pool.shutdown();

        System.out.println("exercise 3");
        System.out.println("Parrallel:" + countProbablePrimesParallel(90000));
        System.out.println("Sequential:" + countProbablePrimes(90000));
    }

    static long countProbablePrimesParallel(long n) {
        long threadStarted = System.currentTimeMillis();
        long count = LongStream.rangeClosed(2, n)
                .mapToObj(BigInteger::valueOf)
                .parallel()
                .filter((i) -> i.isProbablePrime(50))
                .count();

        long threadFinished = System.currentTimeMillis();
        System.out.printf("Parallel process took %s milliseconds", (threadFinished - threadStarted));
        return count;

    }

    static long countProbablePrimes(long n) {
        long threadStarted = System.currentTimeMillis();
        long count = LongStream.rangeClosed(2, n)
                .mapToObj(BigInteger::valueOf)
                .filter((i) -> i.isProbablePrime(50))
                .count();

        long threadFinished = System.currentTimeMillis();
        System.out.printf("Non Parallel process took %s milliseconds", (threadFinished - threadStarted));
        return count;

    }
}