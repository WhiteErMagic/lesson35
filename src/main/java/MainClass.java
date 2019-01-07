import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class MainClass {
    public static final int CARS_COUNT = 8;
    public static void main(String[] args) {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        //Контроль количества машин в тоннеле
        Semaphore semaphore = new Semaphore(CARS_COUNT/2);

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));

        //Для вывода всех готовится и готов.
        CyclicBarrier cl = new CyclicBarrier(CARS_COUNT);

        CountDownLatch countDownFinish = new CountDownLatch(CARS_COUNT);

        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cl, countDownFinish, semaphore);
        }

        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactoryBuilder().build());
        for (int i = 0; i < cars.length; i++) {
            executorService.execute(cars[i]);
        }

        //Маркет финиша
        try {
            countDownFinish.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        executorService.shutdown();
    }

}