import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock {

    private static ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private static Lock lock = new ReentrantLock();

    public static <E> void main(String[] args) {
        new Thread("线程1"){
            @Override
            public void run() {
                Thread thread = Thread.currentThread();//返回对当前正在执行的线程对象的引用

                lock.lock();//得到锁
                try {
                    System.out.println(thread.getName() + "得到了锁");
                    for(int i = 1;i < 5;i++){
                        arrayList.add(i);
                    }
                }catch (Exception e){
                }finally {
                    System.out.println(thread.getName() + "释放了锁");
                    lock.unlock();
                }
            }
        }.start();

        new Thread("线程2") {
            @Override
            public void run() {
                Thread thread = Thread.currentThread();
                lock.lock();
                try {
                    System.out.println(thread.getName() + "得到了锁");
                    for (int i = 0; i < 5; i++) {
                        arrayList.add(i);
                    }
                } catch (Exception e) {
                } finally {
                    System.out.println(thread.getName() + "释放了锁");
                    lock.unlock();
                }

            };
        }.start();
    }
}
