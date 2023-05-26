package thread.test;



import java.util.concurrent.locks.ReentrantLock;

//lock
public class ll3 {
    private int num;
    private ReentrantLock lock = new ReentrantLock();

    public void printabc(String name, int target) throws InterruptedException {
        for (int i = 0; i < 10; ) {
            lock.lock();
            if (num % 3 == target) {
                num++;
                i++;
                System.out.println(i);
                System.out.println(name);
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ll3 lunliu = new ll3();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            lunliu.printabc("A", 0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            lunliu.printabc("B", 1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            lunliu.printabc("C", 2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
        System.out.println("ok");
    }
}
