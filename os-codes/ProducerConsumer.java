
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerConsumer {

    static ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<>(10);
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producer();
                } catch (InterruptedException ex) {
                    Logger.getLogger(proCon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException ex) {
                    Logger.getLogger(proCon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        t1.start();
        t2.start();

    }

    private static void producer() throws InterruptedException {
        Random random = new Random();

        while (true) {
            q.put(random.nextInt(100));
        }
    }

    private static void consumer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            Thread.sleep(1000);
            Integer num = q.take();
            System.out.println("Taken : " + num + " queue size : " + q.size());
        }

    }

}
