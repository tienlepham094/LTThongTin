import java.util.*;

class Symbol {
    char symbol;
    double probability;
    String code;

    Symbol(char symbol, double probability) {
        this.symbol = symbol;
        this.probability = probability;
        this.code = "";
    }
}
public class ShannoCoding {
    String str;
    List<Symbol> symbols = new ArrayList<>();
    public ShannoCoding(String str){
        this.str = str;
        this.calcProbability();
    }
    public void encode(){


    }
    public void calcProbability(){
        int len = str.length();
        Map<Character, Integer> strProb = new HashMap();

        for(int i=0; i<len; i++){
            if(strProb.containsKey(str.charAt(i))){
                strProb.put(str.charAt(i), 1);
            }else{
                int count = strProb.get(str.charAt(i));
                strProb.put(str.charAt(i), count+1);
            }
        }
        strProb.forEach((key, value) -> {
//            symbols.add(new Symbol(key, (double) value /len));
            symbols.add(new Symbol('A', 0.15));
            symbols.add(new Symbol('B', 0.25));
            symbols.add(new Symbol('C', 0.2));
            symbols.add(new Symbol('D', 0.1));
            symbols.add(new Symbol('E', 0.3));
        });

    }

    
}
