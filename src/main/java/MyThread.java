import java.util.concurrent.Callable;

public class MyThread implements Callable<Integer> {
    public static final int TIME_TO_WAIT = 3000;
    private int count = 0;
    private int maxCount = 5;

    @Override
    public Integer call() throws Exception {
        try {
            while (count < maxCount) {
                Thread.sleep(TIME_TO_WAIT);
                System.out.printf("%s работаю\n", Thread.currentThread().getName());
                count++;
            }
        } catch (InterruptedException err) {
            System.out.printf("%s спал\n", Thread.currentThread().getName());
            count++;

        } finally {
            System.out.printf("%s завершен\n", Thread.currentThread().getName());
            count++;
        }
        return count;
    }
}
