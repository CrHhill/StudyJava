import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyTryLock {
    private static ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread("线程1"){
            @Override
            public void run() {
                Thread thread = Thread.currentThread();
                boolean tryLock = lock.tryLock();
                System.out.println(thread.getName()+"======"+tryLock);
                if(tryLock){
                    try {
                        System.out.println(thread.getName()+"得到了锁");
                        for(int i = 0;i < 5;i++){
                            arrayList.add(i);
                        }
                        Thread.sleep(1000);
                    }catch (Exception e){
                    }finally {
                        System.out.println(thread.getName()+"释放了锁");
                        lock.unlock();
                    }
                }

            }
        }.start();

        new Thread("线程2") {
            @Override
            public void run() {
                Thread thread = Thread.currentThread();
                boolean tryLock = lock.tryLock();
                System.out.println(thread.getName()+"======="+tryLock);
                if(tryLock){
                    try {
                        System.out.println(thread.getName() + "得到了锁");
                        for(int i = 0;i < 20;i++){
                            arrayList.add(i);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                        System.out.println(thread.getName() + "释放了锁");
                    }
                }
            }
        }.start();
    }
}
