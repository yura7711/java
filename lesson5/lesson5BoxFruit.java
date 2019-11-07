package fruits;

public abstract class Fruit {
    private float weightFruit;

    public Fruit(float weight) {
        this.weightFruit = weight;
    }

    public float getWeightFruit() {
        return weightFruit;
    }
}


package fruits;

public class Apple extends Fruit {
    public Apple() {
        super(1.0f);
    }
}


package fruits;

public class Orange extends Fruit {
    public Orange() {
        super(1.5f);
    }
}


import fruits.Fruit;
import fruits.Apple;
import fruits.Orange;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    public List<T> listFruit;
    private int count;

    public Box() {
        this.count = 0;
        this.listFruit = new ArrayList<>();
    }

    public int getCount() {
        return count;
    }

    public void addFruit(T myFruit){
        listFruit.add(myFruit);
        this.count += 1;
    }

    public float getWeight(){
        if (listFruit.size() == 0){
            return 0;
        }
        return listFruit.size()* listFruit.get(0).getWeightFruit();
    }

    public boolean compare(Object obj){
        if (!(obj instanceof Box)){
            return false;
        }
        if (this.listFruit.size() != ((Box) obj).listFruit.size()){
            return false;
        }
        else if (this.listFruit.size() == 0 && ((Box) obj).listFruit.size() == 0){
            return true;
        }

        return this.listFruit.get(0).getClass() == ((Box) obj).listFruit.get(0).getClass();
    }

    public void moveToAnotherBox(Box<T> newBox){
        newBox.listFruit.addAll(this.listFruit);
        this.listFruit.clear();
    }
}


import fruits.Apple;
import fruits.Fruit;
import fruits.Orange;

public class MainApp {
    public static void main(String[] args) {
        Box<Apple> myBoxApple = new Box();
        Box<Apple> myBoxApple2 = new Box();
        Box<Orange> myBoxOrange = new Box();

        myBoxApple.addFruit(new Apple());
        myBoxApple.addFruit(new Apple());
        myBoxApple.addFruit(new Apple());
        myBoxApple.addFruit(new Apple());

        myBoxApple2.addFruit(new Apple());
        myBoxApple2.addFruit(new Apple());

        myBoxOrange.addFruit(new Orange());
        myBoxOrange.addFruit(new Orange());

        System.out.println(myBoxApple.getCount());

        System.out.println(myBoxApple.getWeight());
        System.out.println(myBoxOrange.getWeight());
        System.out.println(myBoxApple.compare(myBoxApple2));

        System.out.println("myBoxApple=" + myBoxApple.getWeight());
        System.out.println("myBoxApple2=" + myBoxApple2.getWeight());
        System.out.println("Пересыпаем содержимое из myBoxApple в myBoxApple2");
        myBoxApple.moveToAnotherBox(myBoxApple2);
        System.out.println("myBoxApple=" + myBoxApple.getWeight());
        System.out.println("myBoxApple2=" + myBoxApple2.getWeight());
    }
}
