import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class SlangDictionary {
    private HashMap<String, ArrayList<String>> valuesOfWord;
    private HashMap<String, ArrayList<String>> valuesOfDefinition;
    private ArrayList<String> history;

    public SlangDictionary() {
        this.valuesOfWord = new HashMap<String, ArrayList<String>>();
        this.valuesOfDefinition = new HashMap<String, ArrayList<String>>();
        this.history = new ArrayList<String>();
    }

    public SlangDictionary(HashMap<String, ArrayList<String>> valuesOfWord,
            HashMap<String, ArrayList<String>> valuesOfDefinition, ArrayList<String> history) {
        this.valuesOfWord = valuesOfWord;
        this.valuesOfDefinition = valuesOfDefinition;
        this.history = history;
    }    

    public void setValuesOfWord(HashMap<String, ArrayList<String>> valuesOfWord) {
        this.valuesOfWord = valuesOfWord;
    }

    public void setValuesOfDefinition(HashMap<String, ArrayList<String>> valuesOfDefinition) {
        this.valuesOfDefinition = valuesOfDefinition;
    }

    public void setHistory(ArrayList<String> history) {
        this.history = history;
    }

    public HashMap<String, ArrayList<String>> getValuesOfWord() {
        return this.valuesOfWord;
    }

    public HashMap<String, ArrayList<String>> getValuesOfDefinition() {
        return this.valuesOfDefinition;
    }

    public ArrayList<String> getHistory() {
        return history;
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

    public String searchDefinitionByKey(String k) {
        if (this.valuesOfWord.get(k) == null)
            return "Slang word does not exist";
        ArrayList<String> def = this.valuesOfWord.get(k);
        String res = String.join("| ", def);
        return res;
    }

    public String searchSlangWordByKey(String k) {
        if (this.valuesOfDefinition.get(k) == null)
            return "Definition does not exist";
        ArrayList<String> words = this.valuesOfDefinition.get(k);
        if (words.size() == 0)
            return "";
        String res = String.join("| ", words);
        return res;
    }

    public void addListHisory(String k){
        this.history.add(k);
    }
    
    public void addDefinition(String definition, String word) {
        if (this.valuesOfDefinition.containsKey(definition)) {
            this.valuesOfDefinition.get(definition).add(word);
        } else {
            ArrayList<String> words = new ArrayList<String>();
            words.add(word);
            this.valuesOfDefinition.put(definition, words);
        }
    }

    public void addSlangWord(String word, String definition) {
        ArrayList<String> def = new ArrayList<String>();
        def.add(definition);
        this.valuesOfWord.put(word, def);
    }

    public void deleteSlangWord(String word){
        for(String i: this.valuesOfWord.get(word)){
            this.valuesOfDefinition.remove(i);
        }
        this.valuesOfWord.remove(word);
    }

    public void addNewSlangWord(String word, String definition){
        addSlangWord(word, definition);
        addDefinition(definition, word);
    }

    public void overwriteSlangWord(String word, String definition) {
        for (String i: this.valuesOfWord.get(word)){
            this.valuesOfDefinition.remove(i);
        }
        this.valuesOfWord.get(word).clear();
        this.valuesOfWord.get(word).add(definition);
        addDefinition(definition, word);
    }

    public void duplicateSlangWord(String word, String definition) {
        this.valuesOfWord.get(word).add(definition);
        addDefinition(definition, word);
    }

    public void editSlangWord(String k, String replace){
        ArrayList<String> tmp = this.valuesOfWord.remove(k);
        this.valuesOfWord.put(replace, tmp);
        for(String i: tmp){
            this.valuesOfDefinition.get(i).remove(k);
            this.valuesOfDefinition.get(i).add(replace);
        }
    }
}
