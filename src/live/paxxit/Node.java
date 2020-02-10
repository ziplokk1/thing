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
    private int size;

    public Node(T data) {
        this.size = 1;
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    /**
     *
     * @param data The node contents.
     * @param idx The starting index of this node.
     */
    public Node(T data, int idx) {
        this.size = idx;
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public int size() {
        return this.size;
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
     */
    public boolean add(Node node) {
        this.size += 1;

        // from the perspective of the root node,
        // when calling add, it should recursively go to
        // the next node until it reaches the end,
        // then add the node there and update the index.
        if (this.hasNext()) {
            return this.next.add(node);
        } else {
            this.next = node;
        }
        this.next.setPrevious(this);
        return true;
    }

    private void setNext(Node n) {
        this.next = n;
    }

    private void unlink() {
        if (!this.hasNext()) {
            this.previous.setNext(null);
            return;
        }
        // 82% line coverage is good enough.
        this.previous.setNext(this.getNext());
    }

    public void remove(int idx) {
        if (idx > this.size() - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Node n = this;
        for (int i = 0; i < idx; i++) {
            n = n.getNext();
        }
        n.unlink();
        this.size -= 1;
    }

    public boolean hasNext() {
        // i feel like this is wrong. but i will look up the correct way to do it in a bit.
        return this.next != null;
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

// nice