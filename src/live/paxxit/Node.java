package live.paxxit;

import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;

/**
 * This will be the object which will contain pointers back to itself so that we have a reference
 * to the hasNext elements in the list.
 */
public class Node <T> {
    // for now we will start simple and say that our "linked list" can only contain strings.
    private T data;
    private Node next;
    private Node previous;
    private HashMap<Integer, Node> index;

    public Node(T data) {
        this.index = new HashMap<Integer, Node>();
        this.data = data;
        this.index.put(0, this);

        this.next = null;
        this.previous = null;
    }

    public Node(T data, HashMap<Integer, Node> idx) {
        this.index = idx;
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public int size() {
        return this.index.size();
    }

    /**
     * Add a node after this one.
     *
     * @param node The hasNext node in the linked list.
     */
    public boolean add(Node node) {
        return add(node, this.index);
    }

    /**
     * Add a node after this one.
     *
     *
     * From the perspective of the root node,
     * when calling add, it should recursively go to
     * the next node until it reaches the end,
     * then add the node there and update the index.
     *
     * @param node The hasNext node in the linked list.
     * @param index A HashMap instance that is shared from the root node.
     */
    public boolean add(Node node, HashMap<Integer, Node> index) {
        // my brain is not working yet...
        // i woke up like right before i started streaming.

        // from the perspective of the root node,
        // when calling add, it should recursively go to
        // the next node until it reaches the end,
        // then add the node there and update the index.

        // lol, it wont build because of the lifecycle of the test
        // having to pass before the build succeeds.
        if (this.hasNext()) {
            return this.next.add(node, index);
        } else {
            this.next = node;
            index.put(index.size(), node);
        }
        this.next.setPrevious(this);
        return true;
    }

    /**
     * Delete this node from the linked list.
     *
     * This node should be deleted but not break the links between the other nodes.
     */
    public void remove() {
        // if this node has a previous node, then link the previous node to this nodes hasNext node.
        if (this.hasPrevious() && this.hasNext()) {
            // simply adding our hasNext node should overwrite the reference to `this` node, removing the link and
            // effectively deleting this node.
//            this.previous.add(this.next);
        }
        // now lets figure out what to do if there's no previous node.
    }

    public void remove(int idx) {
        if (idx > this.size() - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        // be back soon!
        //
        // if this node is the last node in the chain
        // then we just delete the reference to it from the previous
        // node.
        if (!this.hasNext()) {
            this.previous.setPrevious(null);
            this.index.remove(idx);
        }
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

    public boolean hasNext() {
        // i feel like this is wrong. but i will look up the correct way to do it in a bit.
        return this.next != null;
    }

    public boolean hasPrevious() {
        // same here. something about using a comparator against null seems wrong.

        // if you're curious as to why i'm skeptical, it's because null is nothing. a black void.
        // you cant compare something to nothing because if something exists, then it obviously doesn't NOT exist.
        // again, i'm super rusty with java, so this may be okay. \shrug
        return this.previous != null;
    }

    // -------------------------
    // Getters and setters

    // encapsulate your stuff.
    public T getData() {
        return this.data;
    }

    public Node getNext() {
        return this.next;
    }

    public void setPrevious(Node node) {
        this.previous = node;
    }

    // -------------------------
    // Overrides

    @Override
    public String toString() {
        // Format the string representation of this object to look like {"previous" -> "this" -> "hasNext"}

        // Solution taken from https://stackoverflow.com/questions/63150/whats-the-best-way-to-build-a-string-of-delimited-items-in-java
        StringBuilder sb = new StringBuilder();
        // While a node, starting with this node, has a hasNext node, append the string representation of the node
        // to our string builder.
        Node n = this;
        sb.append(this.getData().toString());
        do {
            sb.append(" -> ");
            n = n.getNext();
            sb.append(n.getData().toString());
        } while (n.hasNext());
        return sb.toString();
    }
}
