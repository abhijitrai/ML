package composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.List.of;

public class DemoNeuronsWithComposite {
    public static void main(String[] args) {
        Neuron n01 = new Neuron(0, 1);
        Neuron n02 = new Neuron(0, 2);
        Neuron n03 = new Neuron(0, 3);
        Neuron n11 = new Neuron(1, 1);
        Neuron n12 = new Neuron(1, 2);
        Neuron n13 = new Neuron(1, 3);
        Neuron n21 = new Neuron(2, 1);
        Neuron n22 = new Neuron(2, 2);

        NeuronLayer layer1 = new NeuronLayer(of(n01, n02, n03));
        NeuronLayer layer2 = new NeuronLayer(of(n11, n12, n13));
        NeuronLayer layer3 = new NeuronLayer(of(n21, n22));

        layer1.connectTo(layer2);
        layer2.connectTo(layer3);

        Neuron end = new Neuron(2, 2);
        layer3.connectTo(end);

        System.out.println("Wait");


    }
}


interface NeuronIterable<T> extends Iterable<Neuron> {

    default void connectTo(Iterable<Neuron> to) {
        to.forEach(x ->
                {
                    this.forEach(
                            y -> {
                                x.connectTo(y);
                            }
                    );
                }
        );
    }


}

class Neuron implements NeuronIterable<Neuron> {

    int layer;
    int number;
    List<Neuron> out = new ArrayList<>();
    List<Neuron> in = new ArrayList<>();
    List<Neuron> list;

    public Neuron(int layer, int number) {
        this.layer = layer;
        this.number = number;
        this.list = of(this);
    }

    void connectTo(Neuron to) {
        this.out.add(to);
        to.in.add(this);
    }

    @Override
    public Iterator<Neuron> iterator() {
        return list.iterator();
    }
}

class NeuronLayer implements NeuronIterable<Neuron> {
    NeuronLayer(List<Neuron> neurons) {
        this.neuronList = neurons;
    }

    List<Neuron> neuronList;

    @Override
    public Iterator<Neuron> iterator() {
        return neuronList.iterator();
    }
}


