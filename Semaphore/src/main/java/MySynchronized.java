public class MySynchronized implements Runnable{

    private static final Object objectA = new Object();
    private static final Object objectB = new Object();
    private boolean flag;

    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("当前线程 为：" + threadName + "\tflag = " + flag);
        if (flag) {
            synchronized (objectA) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(threadName + "已进入同步代码块objectA，准备进入objectB");
                synchronized (objectB) {
                    System.out.println(threadName + "已经进入同步代码块objectB");
                }
            }

        } else {
            synchronized (objectB) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(threadName + "已进入同步代码块objectB，准备进入objectA");
                synchronized (objectA) {
                    System.out.println(threadName + "已经进入同步代码块objectA");
                }
            }
        }
    }

    public static void main(String[] args) {
        MySynchronized deadlock1 = new MySynchronized();
        MySynchronized deadlock2 = new MySynchronized();
        deadlock1.flag = true;
        deadlock2.flag = false;
        Thread thread1 = new Thread(deadlock1);
        Thread thread2 = new Thread(deadlock2);
        thread1.start();
        thread2.start();

    }
}
