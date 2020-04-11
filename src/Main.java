import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Semaphore mutex = new Semaphore(1);
        Semaphore full = new Semaphore(0);
        Semaphore empty = new Semaphore(5);
        Buffer[] buffers = new Buffer[5];
        for(int i = 0; i < 5; i++)
        {
            String want;
            want = i % 2 == 0 ? "producer" : "user";
            buffers[i] = new Buffer(want, mutex, full, empty, i);
        }
        for(int i = 0; i < 5; i++)
        {
            buffers[i].start();
        }
        while(true){
            for(int i = 0; i < 5; i++)
            {
                System.out.println(buffers[i].number + " is a " + buffers[i].wantTo + ", now " + buffers[i].status);
            }
            System.out.println("------------");
            sleep(2000);

        }
    }
}
