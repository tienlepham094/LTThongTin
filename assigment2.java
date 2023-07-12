import java.sql.SQLOutput;
import java.util.Scanner;
public class assigment2 {
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
            System.out.println("1. Huffman encode");
            System.out.println("2. Shannon encode");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());
            switch(choice){
                case 1:
                    HuffmanCoding huffman = new HuffmanCoding(str.toLowerCase());

                    String encoded = huffman.encode();
                    System.out.println("Encoded: " + encoded);
                    break;
                case 2:
                    ShannoCoding sn = new ShannoCoding(str.toLowerCase());
                    String encoded_sn = sn.encode();
                    System.out.println("Encoded: " + encoded_sn);
                    double efficiency = sn.calcProbability();
                    System.out.println("Hieu suat ma hoa: " + efficiency);
                    System.out.println("Tinh du thua: " + (1-efficiency));
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.exit(0);
            }
        }
    }
}
