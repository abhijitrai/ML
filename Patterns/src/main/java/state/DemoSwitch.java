package state;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DemoSwitch {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LightSwitch ls = new LightSwitch();
        while (true) {
            try {
                System.out.println("Switch : " + ls);
                System.out.println("Choose - 0 to Off");
                System.out.println("Choose - 1 to On");
                int val = Integer.parseInt(br.readLine());
                if (val == 0)
                    ls.getState().turnOff();
                if (val == 1)
                    ls.getState().turnOn();
            } catch (Exception e) {
                System.out.println("Adios amigo!");
                break;
            }
        }
    }
}


class LightSwitch {

    @Setter
    @Getter
    LightBulbState state = new OffState(this);

    @Override
    public String toString() {
        return "LightSwitch{" +
                "current state=" + state +
                '}';
    }
}

interface LightBulbState {
    default void turnOff() {
        System.out.println("The light is already off !");
    }

    default void turnOn() {
        System.out.println("The light is already on !");
    }
}


class OnState implements LightBulbState {
    public OnState(LightSwitch ls) {
        this.ls = ls;
    }

    LightSwitch ls;

    @Override
    public void turnOff() {
        System.out.println("Switch Turned Off !");
        ls.setState(new OffState(ls));
    }

    @Override
    public String toString() {
        return "OnState";
    }
}


class OffState implements LightBulbState {
    public OffState(LightSwitch ls) {
        this.ls = ls;
    }

    LightSwitch ls;

    @Override
    public void turnOn() {
        System.out.println("Switch Turned On !");
        ls.setState(new OnState(ls));
    }

    @Override
    public String toString() {
        return "OffState";
    }
}

