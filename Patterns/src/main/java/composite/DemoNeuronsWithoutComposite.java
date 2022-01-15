package composite;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.of;

public class DemoNeuronsWithoutComposite {
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
//        n01.connectTo(of(n11, n12, n13));
//        n02.connectTo(of(n11, n12, n13));
//        n03.connectTo(of(n11, n12, n13));
//        n11.connectTo(of(n21, n22));
//        n12.connectTo(of(n21, n22));
//        n13.connectTo(of(n21, n22));

        System.out.println("Wait");


    }


    static class Neuron {

        public Neuron(int layer, int number) {
            this.layer = layer;
            this.number = number;
        }

        int layer;
        int number;
        List<Neuron> out = new ArrayList<>();
        List<Neuron> in = new ArrayList<>();

        void connectTo(Neuron to) {
            this.out.add(to);
            to.in.add(this);
        }

        void connectTo(List<Neuron> neuronList) {
            neuronList.stream().forEach(this::connectTo);
        }

    }

    static class NeuronLayer {
        NeuronLayer(List<Neuron> neurons) {
            this.neuronList = neurons;
        }

        List<Neuron> neuronList;

        void connectTo(Neuron neuron) {
            neuronList.stream().forEach(x -> x.connectTo(neuron));
        }

        void connectTo(NeuronLayer layer) {
            List<Neuron> neuron = layer.neuronList;
            neuronList.stream().forEach(x ->
                    {
                        neuron.stream().forEach(
                                y -> {
                                    x.connectTo(y);
                                }
                        );

                    }
            );
        }

    }

}
