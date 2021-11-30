import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        System.out.println("*** Вариант для исполнения всех задач ***");
        methodSubmit(threadPool);
        System.out.println("\n\n*** Вариант для получения результата самой быстрой задачи ***");
        methodInvokeAny(threadPool);
        threadPool.shutdown();
    }

    public static void methodSubmit(ExecutorService threadPool) {
        System.out.println("Задача с методом submit()");

        Callable<Integer> myThread1 = new MyThread();
        Callable<Integer> myThread2 = new MyThread();
        Callable<Integer> myThread3 = new MyThread();
        Callable<Integer> myThread4 = new MyThread();

        final Future<Integer> task1 = threadPool.submit(myThread1);
        final Future<Integer> task2 = threadPool.submit(myThread2);
        final Future<Integer> task3 = threadPool.submit(myThread3);
        final Future<Integer> task4 = threadPool.submit(myThread4);

        try {
            while(!task1.isDone() || !task2.isDone() || !task3.isDone() || !task4.isDone()){
            Thread.sleep(1500);
            }
        } catch (InterruptedException err) {

        }

        try {
            System.out.println("Результат: " + task1.get());
            System.out.println("Результат: " + task2.get());
            System.out.println("Результат: " + task3.get());
            System.out.println("Результат: " + task4.get());
            int totalCount = task1.get() + task2.get() + task3.get() + task4.get();
            System.out.println("Всего сообщений: " + totalCount);

        } catch (InterruptedException | ExecutionException err) {

        }
    }

    public static void methodInvokeAny(ExecutorService threadPool) {
        System.out.println("Задача с методом invokeAny()");
        Callable<Integer> myThread1 = new MyThread();
        Callable<Integer> myThread2 = new MyThread();
        Callable<Integer> myThread3 = new MyThread();
        Callable<Integer> myThread4 = new MyThread();

        List<Callable<Integer>> taskList = new ArrayList<>();
        taskList.add(myThread1);
        taskList.add(myThread2);
        taskList.add(myThread3);
        taskList.add(myThread4);

        int taskResults = 0;

        try {
            taskResults = threadPool.invokeAny(taskList);

        } catch (InterruptedException | ExecutionException err) {

        }
        System.out.println("Результат: " + taskResults);
    }
}
