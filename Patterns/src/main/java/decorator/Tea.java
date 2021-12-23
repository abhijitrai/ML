package decorator;

interface Drink{ String getDrink();}

public class Tea implements  Drink {
    String baseDrink = "Tea" ;
    public String getDrink(){
        return baseDrink;
    }
}

class Coffee implements  Drink {
    String baseDrink = "Coffee" ;
    public String getDrink(){
        return baseDrink;
    }
}

class AddCream implements Drink{
    Drink drink ;
    AddCream(Drink drink)
    {
        this.drink = drink;
    }
    @Override
    public String getDrink() {
        return "Cream & " + this.drink.getDrink();
    }
}
class AddMilk implements Drink{
    Drink drink ;
    AddMilk(Drink drink)
    {
        this.drink = drink;
    }
    @Override
    public String getDrink() {
        return "Milk &  " + this.drink.getDrink();
    }
}

class AddSugar implements Drink{
    Drink drink ;
    AddSugar(Drink drink)
    {
        this.drink = drink;
    }
    @Override
    public String getDrink() {
        return "Sugar &  " + this.drink.getDrink();
    }
}

class TestClass {
    public static void main(String[] args) {
        // Tea with milk please
        System.out.println(new AddMilk(new Tea()).getDrink());
        System.out.println(new AddCream(new AddSugar(new AddMilk(new Coffee()))).getDrink());
    }
}