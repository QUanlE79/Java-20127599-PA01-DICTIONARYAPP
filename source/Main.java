import java.util.*;

public class Main {
    public static void main(String[] args) {
        SlangDictionary s = new SlangDictionary();
        if (s.loadData("slang.txt") != -1) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap slang word: ");
            String k = sc.nextLine();
            System.out.print("Definition: " + s.searchDefinitionByKey(k));

            System.out.println();

            System.out.print("Nhap definition: ");
            String key = sc.nextLine();
            System.out.print("Slang word: " + s.searchSlangWordByKey(key));

        }

    }
}