package composite;

import java.util.ArrayList;
import java.util.List;

public class PrintTree<P> {
    protected P val;
    protected List<PrintTree> children;

    public PrintTree() {
    }

    public void print(final String indent) {
        final String childIndent = indent + '\t';
        System.out.println(indent + val);
        if (children != null)
            children.stream().forEach(x -> x.print(childIndent));

    }

}

class TestClass {
    public static void main(String[] args) {
        PrintTree base = new PrintTree<>();
        base.val = "Root Object ";
        PrintTree words = new PrintTree();
        words.val = "These are certain words";
        PrintTree numbers = new PrintTree();
        numbers.val = Integer.parseInt("10");
        base.children= new ArrayList();
        base.children.add(words);
        base.children.add(numbers);
        base.print("");

    }
}