package state;

import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.List.of;

public class DemoTelephone {

    public static void main(String[] args) throws IOException {
        new Telephone().operate();

    }

}

class Telephone {
    State currentState = State.ON_HOOK;
    BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

    void operate() throws IOException {
        while (true) {
            Transition.transitions
                    .get(currentState)
                    .forEach(x -> System.out.println("Available Action : " + x.getLeft()));
            try {
            int indx = Integer.parseInt(console.readLine());
            Pair<Event, State> pair;

                pair = Transition.transitions
                        .get(currentState).get(indx);
                currentState = pair.getRight();
                System.out.println("Event Selected " + pair.getLeft());
                System.out.println("Transitioned to " + currentState);
            }catch (Exception e ){
                System.out.println("Time to go !");
                break;
            }


        }
    }

}

enum State {
    ON_HOOK,
    OFF_HOOK,
    RINGING,
    CONNECTED
}

enum Event {
    END_CALL,
    PICK_UP_RECEIVER,
    DIAL,
    PICKED_UP_BY_CALLEE,
    CALL_CANCELLED_BY_CALLEE,
    CALL_CANCELLED_BY_CALLER
}

class Transition {

    static Map<State, List<Pair<Event, State>>> transitions = new HashMap();

    static {
//        Dialing
        transitions.put(State.ON_HOOK,
                of(Pair.of(Event.PICK_UP_RECEIVER, State.OFF_HOOK)));
        transitions.put(State.OFF_HOOK,
                of(
                        Pair.of(Event.DIAL, State.RINGING),
                        Pair.of(Event.END_CALL, State.ON_HOOK)
                ));
        transitions.put(State.RINGING,
                of(
                        Pair.of(Event.PICKED_UP_BY_CALLEE, State.CONNECTED),
                        Pair.of(Event.CALL_CANCELLED_BY_CALLEE, State.OFF_HOOK),
                        Pair.of(Event.CALL_CANCELLED_BY_CALLER, State.OFF_HOOK)
                ));
        transitions.put(State.CONNECTED,
                of(
                        Pair.of(Event.CALL_CANCELLED_BY_CALLEE, State.OFF_HOOK),
                        Pair.of(Event.CALL_CANCELLED_BY_CALLER, State.OFF_HOOK)
                ));

    }
}


