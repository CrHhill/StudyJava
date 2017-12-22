import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Employee implements Runnable {

    private String id;//当前id
    private volatile Semaphore semaphore;//信号量
    private static Random random = new Random(47);

    public Employee(String id, Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }

    public void run() {
        try {
            semaphore.acquire();//获取信号量许可
            System.out.println(Thread.currentThread().getName() +" "+ this.id + " is using the toilet");
            TimeUnit.MICROSECONDS.sleep(random.nextInt(2000));
            System.out.println(Thread.currentThread().getName() +" "+this.id + " is leaving");
        } catch (InterruptedException e) {
        } finally {
            semaphore.release();//释放
        }
    }
}
