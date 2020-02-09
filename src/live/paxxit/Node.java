package live.paxxit;

/**
 * This will be the object which will contain pointers back to itself so that we have a reference
 * to the next elements in the list.
 */
public class Node {
    // for now we will start simple and say that our "linked list" can only contain strings.
    private String data;
    private Node next;
    private Node previous;

    public Node(String data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    /**
     * Add a node after this one.
     * @param node The next node in the linked list.
     */
    public void addAfter(Node node) {
        this.next = node;
    }

    /**
     * Add a node before this one.
     * @param node The node to add before this one in the linked list.
     */
    public void addBefore(Node node) {
        // so you could get cheaty and return the node that was just added
        // but I like to make things difficult. So lets pretend that we don't
        // have a reference to the node outside of the root node.
        this.previous = node;
    }

    public boolean next() {
        // i feel like this is wrong. but i will look up the correct way to do it in a bit.
        return this.next != null;
    }

    public boolean prev() {
        // same here. something about using a comparator against null seems wrong.

        // if you're curious as to why i'm skeptical, it's because null is nothing. a black void.
        // you cant compare something to nothing because if something exists, then it obviously doesn't NOT exist.
        // again, i'm super rusty with java, so this may be okay. \shrug
        return this.previous != null;
    }

    // -------------------------
    // Getters and setters

    // encapsulate your stuff.
    public String getData() {
        return this.data;
    }

    // -------------------------
    // Overrides

    public String toString() {
        // bare with me... I haven't coded in java in several years....

        // Format the string representation of this object to look like {"previous" -> "this" -> "next"}

        // If you're unfamiliar with string builders, look up why they're preferred over the overloaded "+"
        // way of concatenation. Maybe they've optimized it since Java6, and if someone knows whether they did or not
        // please let me know.
        StringBuilder sb = new StringBuilder();

        if (this.prev()) {
            sb.append(this.previous.getData());
            sb.append(" -> ");
        }

        sb.append(this.getData());

        if (this.next()) {
            sb.append(" -> ");
            sb.append(this.next.getData());
        }
        return sb.toString();
    }
}
