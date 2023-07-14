import java.util.Scanner;
public class assignment2 {
    public static String inputString(){
        Scanner sc = new Scanner(System.in);
        String str;
        while(true){
            System.out.println("Enter your string: ");
            str = sc.nextLine();
            if(str!=null) break;
        }
        return str;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        String str = inputString();
        while(true){
            System.out.println("1. Huffman coding");
            System.out.println("2. Shannon-Fano coding");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());
            switch(choice){
                case 1:
                    HuffmanCoding huffman = new HuffmanCoding(str.toLowerCase());

                    String encoded_h = huffman.encode();
                    System.out.println("Huffman coded string: " + encoded_h);
                    break;
                case 2:
                    ShannoCoding sn = new ShannoCoding(str.toLowerCase());
                    String encoded_sn = sn.encode();
                    System.out.println("Shannon-Fano coded string: " + encoded_sn);
                    double efficiency = sn.calcEfficiency();
                    System.out.println("Coding efficiency: " + efficiency);
                    System.out.println("Redundancy: " + (1-efficiency));
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.exit(0);
            }
        }
    }
}
