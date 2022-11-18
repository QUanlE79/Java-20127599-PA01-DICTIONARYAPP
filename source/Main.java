
import java.util.*;
public class Main {
    public static void main(String[] args) {
        SlangWord s = new SlangWord();
        if (s.loadData("slang.txt") != -1){
            HashMap<String,ArrayList<String>> slangWord = s.getSlangWord();
            HashMap<String,ArrayList<String>> definition = s.getDefinition();
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhap slang word: ");
            String k = sc.nextLine();
            System.out.print("Definition: ");
            ArrayList<String> t= slangWord.get(k);
            for(String i: t){
                System.out.print(i);
            }

            System.out.println("Nhap definition: ");
            String key = sc.nextLine();
            System.out.print("Slang word: ");
            ArrayList<String> tmp= definition.get(key);
            for(String i: tmp){
                System.out.print(i);
            }
        }

    }
}