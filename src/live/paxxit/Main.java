package live.paxxit;

import live.paxxit.Node;

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
        Node root = new Node("I am root");
        root.addAfter(new Node("I'm node 2!"));
        root.addBefore(new Node("I come before root!"));
        // cool, it works!
        // don't forget to commit!
        System.out.println(root.toString());
    }
}
