import java.util.LinkedList;

// Animal base class
abstract class Animal {
    private String name;
    private int order; // Tracks order of arrival

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    // Determine if this animal is older than another
    public boolean isOlderThan(Animal other) {
        return this.order < other.getOrder();
    }
}

// Dog class
class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
}

// Cat class
class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
}

// Animal Shelter class
class AnimalShelter {
    private LinkedList<Dog> dogs;
    private LinkedList<Cat> cats;
    private int order; 

    public AnimalShelter() {
        dogs = new LinkedList<>();
        cats = new LinkedList<>();
        order = 0;
    }

    // Enqueue a new animal
    public void enqueue(Animal animal) {
        animal.setOrder(order++);
        if (animal instanceof Dog) {
            dogs.addLast((Dog) animal);
        } else if (animal instanceof Cat) {
            cats.addLast((Cat) animal);
        }
    }

    // Dequeue the oldest animal (either dog or cat)
    public Animal dequeueAny() {
        if (dogs.isEmpty() && cats.isEmpty()) {
            return null;
        } else if (dogs.isEmpty()) {
            return dequeueCat();
        } else if (cats.isEmpty()) {
            return dequeueDog();
        }
        // Compare the oldest dog and cat to find which came first
        Dog oldestDog = dogs.peek();
        Cat oldestCat = cats.peek();
        if (oldestDog.isOlderThan(oldestCat)) {
            return dequeueDog();
        } else {
            return dequeueCat();
        }
    }

    // Dequeue the oldest dog
    public Dog dequeueDog() {
        return dogs.isEmpty() ? null : dogs.poll();
    }

    // Dequeue the oldest cat
    public Cat dequeueCat() {
        return cats.isEmpty() ? null : cats.poll();
    }
}

// Main class to demonstrate the functionality
public class Main {
    public static void main(String[] args) {
        AnimalShelter shelter = new AnimalShelter();

        // Adding animals
        shelter.enqueue(new Dog("Rory"));
        shelter.enqueue(new Cat("Whiskers"));
        shelter.enqueue(new Dog("Buddy"));
        shelter.enqueue(new Cat("Simone"));


        // Adopting animals determined by order of arrival
        System.out.println("Adopt any: " + shelter.dequeueAny().getName());
        System.out.println("Adopt cat: " + shelter.dequeueCat().getName()); 
        System.out.println("Adopt dog: " + shelter.dequeueDog().getName()); 
        System.out.println("Adopt any: " + shelter.dequeueAny().getName()); 
    }
}