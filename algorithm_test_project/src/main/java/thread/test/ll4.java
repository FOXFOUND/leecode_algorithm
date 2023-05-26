package thread.test;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//条件变量lock
public class ll4 {
    private int num;
    private static ReentrantLock lock = new ReentrantLock();
    static Condition c1 = lock.newCondition();
    static Condition c2 = lock.newCondition();
    static Condition c3 = lock.newCondition();

    public void printabc(String name, int target, Condition c, Condition next) throws InterruptedException {
        for (int i = 0; i < 10; ) {
            lock.lock();
            while (num % 3 != target) {
                c.await();
            }
            num++;
            i++;
            System.out.println(name);
            next.signal();
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ll4 lunliu = new ll4();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            lunliu.printabc("A", 0, c1, c2);
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
                            lunliu.printabc("B", 1, c2, c3);
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
                            lunliu.printabc("C", 2, c3, c1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }
}