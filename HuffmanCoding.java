import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// node cua cay
class Node {
    private int value; // binary format
    private char character;
    private Node left;
    private Node right;
    
    public Node(int value, char character) {
        this.value = value;
        this.character = character;
        this.left = null;
        this.right = null;
    }
    
    public Node(int value, Node left, Node right) {
        this.value = value;
        this.character = '\0';
        this.left = left;
        this.right = right;
    }
    
    public int getValue() { return value; }
    public char getCharacter() { return character; }
    public Node getLeft() { return left; }
    public Node getRight() { return right; }
    
    public boolean isLeaf() {
        return left == null && right == null && character != '\0';
    }
}
class NodeComparator implements Comparator<Node>{

    @Override
    public int compare(Node t1, Node t2) {
        if(t1.getValue()<t2.getValue())
            return -1;
        else if(t1.getValue()> t2.getValue())
            return 1;
        return 0;
    }
}


// huffman
public class HuffmanCoding {
    private Node root = null;
    private HashMap<Character, Integer> freqMap;
    private HashMap<Character, String> codeMap;
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
        PriorityQueue<Node> q = new PriorityQueue<>(freqMap.size(), new NodeComparator());
        for (Map.Entry<Character, Integer> i : freqMap.entrySet())
            q.add(new Node(i.getValue(), i.getKey()));

        Node root = null;
        while (q.size() > 1) {
            Node x = q.poll();
            Node y = q.poll();
            root = new Node(x.getValue() + y.getValue(), x, y);
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
            if(!freqMap.containsKey(c)){
                freqMap.put(c, 1);
            }else{
                int count = freqMap.get(c);
                freqMap.put(c, count+1);
            }
        }
    }
}