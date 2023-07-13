import java.util.*;

class Symbol {
    private char character;
    private double probability;
    private  String code;

    Symbol(char character, double probability) {
        this.character = character;
        this.probability = probability;
        this.code = "";
    }

    public char getCharacter() {
        return character;
    }

    public double getProbability() {
        return probability;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code) {
        this.code +=code;
    }
}
class SymbolComparator implements Comparator<Symbol>{

    @Override
    public int compare(Symbol t1, Symbol t2) {
        if(t1.getProbability()< t2.getProbability())
            return 1;
        else if(t1.getProbability()>t2.getProbability())
            return -1;
        return 0;
    }
}
public class ShannoCoding {
    List<Symbol> symbols = new ArrayList<>();
    String str;

    public ShannoCoding(String str){
        this.str = str;
        this.calcProbability();
    }
    public String encode(){

        this.generateCodes(symbols, 0, symbols.size()-1);
//        for(Symbol s: symbols){
//            System.out.println("Character: "+s.getCharacter()+"- encoded: "+s.getCode());
//        }
        HashMap<Character, String> symbolMap = new HashMap<>();
        for (Symbol symbol : symbols) {
            symbolMap.put(symbol.getCharacter(), symbol.getCode());
        }
        StringBuilder builder = new StringBuilder();
        for (char c : str.toCharArray()) {
            builder.append(symbolMap.get(c));
        }
        return builder.toString();
    }
    public void calcProbability(){
        Map<Character, Integer> freqMap = new HashMap();
        int len = str.length();
        for (char c : str.toCharArray()) {
            if(!freqMap.containsKey(c)){
                freqMap.put(c, 1);
            }else{
                int count = freqMap.get(c);
                freqMap.put(c, count+1);
            }
        }
        for (Map.Entry<Character, Integer> i : freqMap.entrySet())
            symbols.add(new Symbol(i.getKey(), (double) i.getValue()/len));
        // Sort symbols in descending order of probabilities
        Collections.sort(symbols, new SymbolComparator());
    }

    public double calcEfficiency(){
        double average_length = 0;
        double entropy = 0;
        double efficiency = 0;

        for (Symbol s: symbols)
        {
            //tinh chieu dai trung binh tu ma
            average_length += s.getCode().length() * s.getProbability();
            //tinh entropy;
            entropy += -(s.getProbability() * (Math.log((double) s.getProbability()) / Math.log(2)));

            //tinh hieu suat ma hoa
            efficiency = entropy / average_length;
        }
        
        //System.out.println("chieu dai trung binh = " + average_length);
        //System.out.println("entropy = " + entropy);
        return efficiency;
    }


    public void generateCodes(List<Symbol> symbols, int start, int end) {
        if (start == end) {
            return;
        }

        // Calculate the sum of probabilities in the given range
        double totalProb = 0;
        for (int i = start; i <= end; i++) {
            totalProb += symbols.get(i).getProbability();
        }

        // Find the split point that minimizes the difference in probabilities
        int splitIndex = -1;
        double sum = 0;

        for (int i = start; i <= end; i++) {
            sum += symbols.get(i).getProbability();
            if(sum >= totalProb/2){
                splitIndex = i;
                break;
            }
        }
//        System.out.println(splitIndex);
        for(int i =start; i<=end; i++){
            if(i<=splitIndex){
                symbols.get(i).setCode("0");
            }else{
                symbols.get(i).setCode("1");
            }
        }
//        for(Symbol s: symbols){
//            System.out.println("Character: "+s.getCharacter()+"- encoded: "+s.getCode());
//        }

        // Recursively generate codes for left and right partitions
        generateCodes(symbols, start, splitIndex);
        generateCodes(symbols, splitIndex + 1, end);
    }

    
}
