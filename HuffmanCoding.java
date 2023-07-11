import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// node cua cay
class Node {
    private int data; // binary format
    private char character;
    private Node left;
    private Node right;
    
    public Node(int data, char character) {
        this.data = data;
        this.character = character;
        this.left = null;
        this.right = null;
    }
    
    public Node(int data, Node left, Node right) {
        this.data = data;
        this.character = '\0';
        this.left = left;
        this.right = right;
    }
    
    public int getData() { return data; }
    public char getCharacter() { return character; }
    public Node getLeft() { return left; }
    public Node getRight() { return right; }
    
    public boolean isLeaf() {
        return left == null && right == null && character != '\0';
    }
    }

// huffman
public class HuffmanCoding {
    private Node root = null;
    private Map<Character, Integer> freqMap;
    private Map<Character, String> codeMap;
    private String str;
    public HuffmanCoding(String str){
        this.str = str;
        countFreq(this.str);
        this.root = buildTree(freqMap);
        this.codeMap = new HashMap<>();
        buildCode(root, "");
    }

    public String encode() {
        if (this.codeMap == null || this.codeMap.isEmpty()) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (char c : str.toCharArray()) {
            builder.append(codeMap.get(c));
        }
        return builder.toString();
    }

    private Node buildTree(Map<Character, Integer> freqMap) {
        PriorityQueue<Node> q = new PriorityQueue<>(freqMap.size(), Comparator.comparingInt(Node::getData));
        for (Map.Entry<Character, Integer> i : freqMap.entrySet())
            q.add(new Node(i.getValue(), i.getKey()));

        Node root = null;
        while (q.size() > 1) {
            Node x = q.poll();
            Node y = q.poll();
            root = new Node(x.getData() + y.getData(), x, y);
            q.add(root);
        }
        return root;
    }

    private void buildCode(Node node, String code) {
        if (node.isLeaf()) {
            codeMap.put(node.getCharacter(), code);
            return;
        }
        buildCode(node.getLeft(), code + "0");
        buildCode(node.getRight(), code + "1");
    }

    private void countFreq(String str) {
        this.freqMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            int rs = this.freqMap.getOrDefault(c, 0);
            this.freqMap.put(c, rs + 1);
        }
    }
}