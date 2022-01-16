package mediator;

import java.util.ArrayList;
import java.util.List;

public class DemoChatRooom {

    public static void main(String[] args) {
        Person john = new Person("John");
        Person jill = new Person("Jill");
        Person kate = new Person("Kate");
        ChatRoom cafe = new ChatRoom("Cafe");
        cafe.join(john);
        cafe.join(jill);
        cafe.broadcast(john, "Hey");
        cafe.broadcast(jill, "hi");
        cafe.join(kate);
        cafe.privateMsg(jill, kate, "Welcome to the room ");
    }
}

class Person {
    Person(String name) {
        this.name = name;
    }

    final String name;

    void display(String msg, String from) {
        if (from.equalsIgnoreCase(name))
            System.out.println("(" + name + ")" + " Me: " + msg);
        else
            System.out.println("(" + name + ")" + from + " : " + msg);
    }
}

class ChatRoom {
    List<Person> members = new ArrayList<>();

    ChatRoom(String name) {
        this.name = name;
    }

    final String name;

    void join(Person person) {
        members.add(person);
    }

    void broadcast(Person person, String msg) {
        members.stream().forEach(x -> {
            x.display(msg, person.name);
        });
    }

    void privateMsg(Person personFrom, Person personTo, String msg) {
        personTo.display(msg, personFrom.name);
    }

}