import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static final CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT+1);
    public static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    final static ArrayBlockingQueue<Car> arrayBlockingQueue = new ArrayBlockingQueue<>(CARS_COUNT/2);
    private static Integer racePositionResult = 0;

    public static synchronized Integer getRacePositionResult() {
        return racePositionResult +=1;
    }

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(arrayBlockingQueue), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        try {
            rwl.writeLock().lock();
            cyclicBarrier.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        }
        catch (InterruptedException | BrokenBarrierException e){
            e.printStackTrace();
        }
        finally {
            rwl.writeLock().unlock();
        }

        try {
            cyclicBarrier.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        }
        catch (InterruptedException | BrokenBarrierException e){
            e.printStackTrace();
        }

        for (int i = 0; i < cars.length; i++) {
            if (cars[i].getRacePositionResult().equals(1)) {
                System.out.println("Победитель >>> " + cars[i].getName());
            }
        }
    }
}
import java.util.concurrent.BrokenBarrierException;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    private Integer racePositionResult;

    public Integer getRacePositionResult() {
        return racePositionResult;
    }

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            MainClass.cyclicBarrier.await();
            MainClass.rwl.readLock().lock();

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            racePositionResult = MainClass.getRacePositionResult();
            MainClass.cyclicBarrier.await();
        }
        catch(InterruptedException | BrokenBarrierException e){
            e.printStackTrace();
        }
        finally {
            MainClass.rwl.readLock().unlock();
        }
    }
}
public abstract class Stage {
    protected int length;
    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}
public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
import java.util.concurrent.ArrayBlockingQueue;

public class Tunnel extends Stage {
    private ArrayBlockingQueue<Car> queue;

    public Tunnel(ArrayBlockingQueue<Car> queue) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.queue = queue;
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                queue.put(c);

                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                queue.take();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
public class Race {
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
