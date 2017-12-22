public class MySynchronized2 {
    public static void main(String[] args){
        final MySynchronized synchronized1 = new MySynchronized();
        final MySynchronized synchronized2 = new MySynchronized();
        new Thread("thread1"){
            @Override
            public void run(){
                synchronized (synchronized1){
                    try {
                        System.out.println(this.getName()+":start");
                        Thread.sleep(1000);
                        System.out.println(this.getName()+":wake up");
                        System.out.println(this.getName()+":end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (synchronized2){
                        System.out.println(this.getName()+":start");
                    }
                }
            }
        }.start();

        new Thread("thread2"){
            @Override
            public void run(){
                synchronized (synchronized2){
                    try {
                        System.out.println(this.getName()+":start");
                        Thread.sleep(1000);
                        System.out.println(this.getName()+":wake up");
                        System.out.println(this.getName()+":end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (synchronized1){
                        System.out.println(this.getName()+":start");
                    }
                }
            }
        }.start();
    }
}
