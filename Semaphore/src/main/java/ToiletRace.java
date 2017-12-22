import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ToiletRace {
    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);//创建一个固定长度30的线程池

    private static Semaphore s = new Semaphore(10);//创建一个固定长度10的信号量

    public static void main(String[] args) {
        for (int i = 1; i <= THREAD_COUNT; i++) {
            threadPool.execute(new Employee(String.valueOf(i),s));//将线程Employee放到线程池中执行
        }
        threadPool.shutdown();
    }
}
