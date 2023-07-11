import java.util.Scanner;
public class assigment2 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        String str;
        while(true){
            System.out.println("Enter your string: ");
            str = sc.nextLine();
            if(str!=null) break;
        }
        while(true){
            System.out.println("1. Huffman encode");
            System.out.println("2. Shannon encode");
            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());
            switch(choice){
                case 1:
                    HuffmanCoding huffman = new HuffmanCoding(str);

                    String encoded = huffman.encode();
                    System.out.println("Encoded: " + encoded);
                    break;
                case 2:
                    ShannoCoding sn = new ShannoCoding(str);
                    sn.encode();
                    break;
                default:
                    System.exit(0);
            }
        }
    }
}
