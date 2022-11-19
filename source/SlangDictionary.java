import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class SlangDictionary {
    private HashMap<String, ArrayList<String>> valuesOfWord;
    private HashMap<String, ArrayList<String>> valuesOfDefinition;

    public SlangDictionary() {
        this.valuesOfWord = new HashMap<String, ArrayList<String>>();
        this.valuesOfDefinition = new HashMap<String, ArrayList<String>>();
    }

    public SlangDictionary(HashMap<String, ArrayList<String>> valuesOfWord,
            HashMap<String, ArrayList<String>> valuesOfDefinition) {
        this.valuesOfWord = valuesOfWord;
        this.valuesOfDefinition = valuesOfDefinition;
    }

    public void setValuesOfWord(HashMap<String, ArrayList<String>> valuesOfWord) {
        this.valuesOfWord = valuesOfWord;
    }

    public void setValuesOfDefinition(HashMap<String, ArrayList<String>> valuesOfDefinition) {
        this.valuesOfDefinition = valuesOfDefinition;
    }

    public HashMap<String, ArrayList<String>> getValuesOfWord() {
        return this.valuesOfWord;
    }

    public HashMap<String, ArrayList<String>> getValuesOfDefinition() {
        return this.valuesOfDefinition;
    }

    public int loadData(String fileName) {
        try {
            File fi = new File(fileName);
            Scanner myReader = new Scanner(fi);
            while (myReader.hasNextLine()) {

                String[] data = myReader.nextLine().split("`");
                if (data.length != 2)
                    continue;
                ArrayList<String> valuesOfDefinition = new ArrayList<String>(Arrays.asList(data[1].split("\\| ")));
                this.valuesOfWord.put(data[0], valuesOfDefinition);

                for (String i : valuesOfDefinition) {
                    if (this.valuesOfDefinition.containsKey(i)) {
                        this.valuesOfDefinition.get(i).add(data[0]);
                    } else {
                        ArrayList<String> word = new ArrayList<String>();
                        word.add(data[0]);
                        this.valuesOfDefinition.put(i, word);
                    }
                }
            }
            myReader.close();
            return 1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String searchDefinitionByKey(String k){
        if (this.valuesOfWord.get(k) == null) return "Slang word does not exist";
        ArrayList<String> def = this.valuesOfWord.get(k);
        String res = String.join("| ", def);
        return res;
    }
    public String searchSlangWordByKey(String k){
        if (this.valuesOfDefinition.get(k) == null) return "Definition does not exist";
        ArrayList<String> words = this.valuesOfDefinition.get(k);
        if (words.size() == 0) return "";
        String res = String.join("| ", words);
        return res;
    }
}
