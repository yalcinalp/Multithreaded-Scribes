import java.util.concurrent.*;

public class Scribes {
    // Use Semaphores for the resources' availability
    public static Semaphore pens;
    public static Semaphore ink_bottles;

    public static void main(String[] args) {
        if (args.length != 3) {
            return;
        }
        int nScribes = Integer.parseInt(args[0]);
        int nPens = Integer.parseInt(args[1]);
        int nBottles = Integer.parseInt(args[2]);

        pens = new Semaphore(nPens, true);
        ink_bottles = new Semaphore(nBottles, true);

        ExecutorService executor = Executors.newFixedThreadPool(nScribes);
        for (int i = 1; i <= nScribes; i++) {
            executor.execute(new Scribe(i));
        }

        executor.shutdown();
    }

    public static class Scribe implements Runnable {
        private int scribe_number;

        public Scribe(int scribe_number) {
            this.scribe_number = scribe_number;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    ink_bottles.acquire();
                }
                catch (InterruptedException e) {
                    continue;
                }
                Actions.takeBottle(scribe_number);

                try {
                    pens.acquire();
                }
                catch (InterruptedException e) {
                    ink_bottles.release();
                    Actions.putBottle(scribe_number);
                    continue;
                }
                Actions.takePen(scribe_number);

                Actions.write(scribe_number);

                Actions.putPen(scribe_number);
                pens.release();

                Actions.putBottle(scribe_number);
                ink_bottles.release();
            }
        }
    }
}

