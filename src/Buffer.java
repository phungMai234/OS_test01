import java.util.concurrent.Semaphore;

public class Buffer extends Thread {
    public Semaphore mutex;
    public Semaphore full;
    public Semaphore empty;
    public int number;
    public String wantTo;
    public String status;

    public Buffer(String wantTo, Semaphore mutex, Semaphore full, Semaphore empty, int number) {
        this.mutex = mutex;
        this.full = full;
        this.empty = empty;
        this.number = number;
        this.wantTo = wantTo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void run() {
        if (this.wantTo.equals("sx")) {
            do {
                try {
                    //wait
                    setStatus("waiting");
                    while (this.empty.availablePermits() <= 0) {

                    }
                    this.empty.acquire();

                    while (this.mutex.availablePermits() <= 0) {

                    }
                    this.mutex.acquire();
                    //cs
                    setStatus("add th item to the buffer");
                    sleep(5000);

                    this.empty.release();
                    this.mutex.release();

                    //rm
                    setStatus("relax");
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (true);
        } else if (this.wantTo.equals("user")) {
            do {
                try {
                    setStatus("waiting");
                    while (this.full.availablePermits() <= 0) {

                    }
                    this.full.acquire();

                    while (this.mutex.availablePermits() <= 0) {

                    }
                    this.mutex.acquire();

                    //cs
                    setStatus("remove an item from buffer to nexttc");
                    sleep(5000);

                    this.full.release();
                    this.mutex.release();

                    //rm
                    setStatus("relax");
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (true);
        }
    }
}
