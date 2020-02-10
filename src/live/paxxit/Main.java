package live.paxxit;

import live.paxxit.Node;

class X {

    @Override
    public String toString() {
        return "This is type X";
    }
}

/**
 * Our goal is to create a doubly linked list without actually implementing the List interface.
 */
public class Main {
    public static void main(String[] args) {
        // The reason that we create an instance of main and execute the program through
        // the `run` method, is so that we have persistent state.
        // It scales a lot easier later when you have to implement the same logic across multiple threads or
        // whatever.
        Main main = new Main();
        main.run();
    }

    public Main() {
        // nothing to do here yet.
    }

    /**
     * Main execution point of our program.
     */
    public void run() {
        Node<String> root = new Node<String>("I am root");
        root.add(new Node<Integer>(3));
        root.add(new Node<X>(new X()));

        // hasNext up, we're going to create a `delete` method in the node.
        // this method should delete a node without breaking the links.
        // So when we delete a node, the node previous to the deleted node should point to
        // the node that came after the deleted node.
        System.out.println(root.toString());
    }
}
