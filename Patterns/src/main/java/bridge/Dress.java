package bridge;

import java.util.List;

import static java.util.List.of;


public class Dress {
    List <DressItem> items ;
    public Dress(List<DressItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Dress{" +
                "items=" + items +
                '}';
    }
}


class DressItem {
    String item;
    Color color;
    Fabric fabric;

    public DressItem(String item, Color color, Fabric fabric) {
        this.item = item;
        this.color = color;
        this.fabric = fabric;
    }


    @Override
    public String toString() {
        return "DressItem{" +
                "item='" + item + '\'' +
                ", color=" + color +
                ", fabric=" + fabric +
                '}';
    }
}

class Pants extends DressItem {


    public Pants(Color color, Fabric fabric) {
        super("Pant", color, fabric);
    }
}

class Shirt extends DressItem {
    public Shirt( Color color, Fabric fabric) {
        super("Shirt", color, fabric);
    }
}

class Color {
    Color(String color) {
        this.color = color;
    }
    String color;

    @Override
    public String toString() {
        return "Color{" +
                "color='" + color + '\'' +
                '}';
    }
}

class Red extends Color {
    Red() {
        super("Red");
    }
}

class Blue extends Color {
    Blue() {
        super("Blue");
    }
}


class Fabric {
    Fabric(String type) {
        this.type = type;
    }

    String type;

    @Override
    public String toString() {
        return "Fabric{" +
                "type='" + type + '\'' +
                '}';
    }
}

class Cotton extends Fabric {
    Cotton() {
        super("Cotton");
    }
}

class Synthetic extends Fabric {
    Synthetic() {
        super("Synthethic");
    }

}

class TestClass {
    public static void main(String[] args) {
//        Make a Dress with Red Cotton Shirt & Blue Synthetic Pant
        System.out.println(new Dress(of(new Pants(new Blue(),new Synthetic() ))));
        System.out.println(new Dress(of(new Shirt(new Red(),new Cotton() ))));
    }
}

