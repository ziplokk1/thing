package live.paxxit;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class TestNode {

    @Before
    public void before() {

    }

    /**
     * Ensure that when a node is added to our root node,
     * that the size is correct.
     */
    @Test
    public void testSizeWithTwoNodes() {
        Node<String> root = new Node<String>("Root Node");
        root.add(new Node<String>("Node 2"));
        Assert.assertEquals(2, root.size());
    }

    /**
     * Ensure that when a node is added to our root node,
     * that the size is correct.
     */
    @Test
    public void testSizeWithSevenNodes() {
        // list size is 1 here because the root node is the
        // first element.
        Node<String> root = new Node<String>("Root Node");
        // we add 6 more nodes to the "list"
        for (int i = 0; i < 6; i++) {
            root.add(new Node<String>(String.format("Node %d", i+2)));
        }
        // should be root + 6 more nodes.
        Assert.assertEquals(7, root.size());
    }

    @Test
    public void testToString() {
        Node<String> root = new Node<String>("Root Node");
        root.add(new Node<String>("Node 2"));
        Assert.assertEquals("Root Node -> Node 2", root.toString());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveThrows() {
        Node<String> root = new Node<String>("Root Node");
        root.remove(5);
    }

    @Test
    public void testRemoveLastNode() {
        Node<String> root = new Node<String>("Root Node");
        for (int i = 0; i < 6; i++) {
            root.add(new Node<String>(String.format("Node %d", i+1)));
        }
        root.remove(6);
        Assert.assertEquals(6, root.size());
        Assert.assertEquals("Root Node -> Node 1 -> Node 2 -> Node 3 -> Node 4 -> Node 5", root.toString());
    }
}
