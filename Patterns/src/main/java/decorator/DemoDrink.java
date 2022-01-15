package decorator;


public class DemoDrink {
    public static void main(String[] args) {
        // Tea with milk please
        System.out.println(new AddMilk(new Tea()).getDrink());
        System.out.println(new AddCream(new AddSugar(new AddMilk(new Coffee()))).getDrink());
    }
}

interface Drink {
    String getDrink();
}

class Tea implements Drink {
    String baseDrink = "Tea";

    public String getDrink() {
        return baseDrink;
    }
}

class Coffee implements Drink {
    String baseDrink = "Coffee";

    public String getDrink() {
        return baseDrink;
    }
}

abstract class Decorator implements Drink {
    Drink drink;
}

class AddCream extends Decorator {

    AddCream(Drink drink) {
        this.drink = drink;
    }

    @Override
    public String getDrink() {
        return "Cream & " + this.drink.getDrink();
    }
}

class AddMilk extends Decorator {

    AddMilk(Drink drink) {
        this.drink = drink;
    }

    @Override
    public String getDrink() {
        return "Milk &  " + this.drink.getDrink();
    }
}

class AddSugar extends Decorator {

    AddSugar(Drink drink) {
        this.drink = drink;
    }

    @Override
    public String getDrink() {
        return "Sugar &  " + this.drink.getDrink();
    }
}
