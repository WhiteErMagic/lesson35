import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static boolean getStart;//Для вывода сообщения о старте 1 раз
    private Semaphore semaphore;//Контроль количества машин в тоннеле
    private static boolean haveWinner = false;
    static {
        CARS_COUNT = 0;
    }
    private Race race;

    private CyclicBarrier cl;
    private int speed;
    private CountDownLatch cdFinish;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CyclicBarrier cl, CountDownLatch cdFinish, Semaphore semaphore) {
        this.race = race;
        this.cl = cl;
        this.getStart = false;
        this.speed = speed;
        this.semaphore = semaphore;
        this.cdFinish = cdFinish;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            cl.await();//Сначала начнем подготовку
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cl.await();//Ожидание готовности
            getStart();//Выведем сообщение о старте
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        cdFinish.countDown();//Финиширование
        if(!haveWinner){
            haveWinner = true;
            System.out.println(this.getName() + " WIN");
        }
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    //Вывод сообщения о старте
    //Синхронизируем на случай, если несколько машин одновременно закончат подготовку
    public static synchronized void getStart(){
        if(!getStart) {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            getStart = true;
        }
    }
}
