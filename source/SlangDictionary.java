import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

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

    public void loadHistory(String filename) {
        try {
            File fi = new File(filename);
            Scanner sc = new Scanner(fi);
            this.history.clear();
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                this.history.add(data);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveHistory(String filename) {
        try {
            FileWriter fo = new FileWriter(filename);
            for (String i : this.history) {
                fo.write(i + "\n");
            }
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load data from file and store to structure class SlangDictionary
    public int loadData(String fileName) {
        try {
            File fi = new File(fileName);
            Scanner myReader = new Scanner(fi);
            this.valuesOfDefinition.clear();;
            this.valuesOfWord.clear();
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

    // Search with slang word: return an array list which have all the definition of
    // the k
    private ArrayList<String> searchDefinitionByKey(String k) {
        if (this.valuesOfWord.get(k) == null)
            return null;
        ArrayList<String> defs = this.valuesOfWord.get(k);
        if (defs.size() == 0)
            return null;
        return defs;
    }

    public Map<String, ArrayList<String>> searchByWord(String k) {
        Map<String, ArrayList<String>> res = new HashMap<String, ArrayList<String>>();
        for (String i : this.valuesOfWord.keySet()) {
            if (i.contains(k)) {
                res.put(i, this.searchDefinitionByKey(i));
            }
        }
        return res;
    }

    // Search with definition: return an array list which have all the slang words
    // have the definition k
    private ArrayList<String> searchSlangWordByKey(String k) {
        if (this.valuesOfDefinition.get(k) == null)
            return null;
        ArrayList<String> words = this.valuesOfDefinition.get(k);
        if (words.size() == 0)
            return null;
        return words;
    }

    public Map<String, ArrayList<String>> searchByDefinition(String k) {
        Map<String, ArrayList<String>> res = new HashMap<String, ArrayList<String>>();
       
       
        for (String i : this.valuesOfDefinition.keySet()) {
            if (i.contains(k)) {
                res.put(i, this.searchSlangWordByKey(i));
            }
        }
        return res;
    }

    // Add a slang word which searched before to history
    public void addListHisory(String k, String date) {
        for (String i: this.history){
            if (i.contains(k)){
                if (i.substring(24) == k){
                    System.out.println(i.substring(24));
                this.history.remove(i);
                this.history.add(0, date + "     " +k);
                return;
                }
            }
        }
        this.history.add(0, date + "     " +k);
    }
    public void clearHistory(String filename){
        try {
            FileWriter fo = new FileWriter(filename);
            fo.write("");
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void addDefinition(String definition, String word) {
        if (this.valuesOfDefinition.containsKey(definition)) {
            this.valuesOfDefinition.get(definition).add(word);
        } else {
            ArrayList<String> words = new ArrayList<String>();
            words.add(word);
            this.valuesOfDefinition.put(definition, words);
        }
    }

    private void addSlangWord(String word, String definition) {
        ArrayList<String> def = new ArrayList<String>();
        def.add(definition);
        this.valuesOfWord.put(word, def);
    }

    // Delete an available slang word
    public void deleteSlangWord(String word) {
        for (String i : this.valuesOfWord.get(word)) {
            this.valuesOfDefinition.remove(i);
        }
        this.valuesOfWord.remove(word);
    }

    // Add a new slang word with definition to Dictionary
    public void addNewSlangWord(String word, String definition) {
        addSlangWord(word, definition);
        addDefinition(definition, word);
    }

    // Overwrite an available slang word
    public void overwriteSlangWord(String word, String definition) {
        for (String i : this.valuesOfWord.get(word)) {
            this.valuesOfDefinition.remove(i);
        }
        this.valuesOfWord.get(word).clear();
        this.valuesOfWord.get(word).add(definition);
        addDefinition(definition, word);
    }

    // Duplicate (add definition) to an available slang word
    public void duplicateSlangWord(String word, String definition) {
        this.valuesOfWord.get(word).add(definition);
        addDefinition(definition, word);
    }

    // Edit an available slang word
    public void editSlangWord(String k, String replace) {
        ArrayList<String> tmp = this.valuesOfWord.remove(k);
        this.valuesOfWord.put(replace, tmp);
        for (String i : tmp) {
            this.valuesOfDefinition.get(i).remove(k);
            this.valuesOfDefinition.get(i).add(replace);
        }
    }

    public void resetSlangWord() {
        SlangDictionary root = new SlangDictionary();
        root.loadData(EnvVariable.ROOT_SLANG_WORD_FILE_NAME);
        root.saveDictionary(EnvVariable.CURRENT_SLANG_WORD_FILE_NAME);
        this.loadData(EnvVariable.CURRENT_SLANG_WORD_FILE_NAME);
    }

    public void saveDictionary(String filename) {
        try {
            FileWriter fo = new FileWriter(filename, false);
            for (String i : this.valuesOfWord.keySet()) {
                String item = String.join("| ", this.valuesOfWord.get(i));
                fo.write(i + "`" + item + "\n");
            }
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Random a slang word on dictionary: return a string definition
    public String randomSlangWord() {
        Object slangWord = this.valuesOfWord.keySet().toArray()[new Random()
                .nextInt(this.valuesOfWord.keySet().toArray().length)];
        return slangWord.toString();
    }

    // Random a definition on dictionary: return a string definition
    private String randomDefinition() {
        Object def = this.valuesOfDefinition.keySet().toArray()[new Random()
                .nextInt(this.valuesOfDefinition.keySet().toArray().length)];
        return def.toString();
    }

    // Make quiz: return a List that first item is question and 4 next items are
    // answer include exactly 1 correct
    public ArrayList<String> makeSlangWordQuiz() {
        ArrayList<String> res = new ArrayList<String>();
        String word = this.randomSlangWord();
        String ans = this.valuesOfWord.get(word).get(new Random().nextInt(this.valuesOfWord.get(word).size()));
        res.add(ans);
        int i = 0;
        while (i < 3) {
            String tmp = this.randomDefinition();
            if (this.valuesOfWord.get(word).contains(tmp))
                continue;
            res.add(tmp);
            i++;
        }
        Collections.shuffle(res);
        res.add(0, word);
        return res;
    }

    // Make quiz: return a List that first item is question and 4 next items are
    // answer include exactly 1 correct
    public ArrayList<String> makeDefinitionQuiz() {
        ArrayList<String> res = new ArrayList<String>();
        String def = this.randomDefinition();
        String ans = this.valuesOfDefinition.get(def)
                .get(new Random().nextInt(this.valuesOfDefinition.get(def).size()));
        res.add(ans);
        int i = 0;
        while (i < 3) {
            String tmp = this.randomSlangWord();
            if (this.valuesOfDefinition.get(def).contains(tmp))
                continue;
            res.add(tmp);
            i++;
        }
        Collections.shuffle(res);
        res.add(0, def);
        return res;
    }

    public String listWord() {
        String res = "";
        for (String i : this.valuesOfWord.keySet()) {
            res += i + ": " + String.join(", ", this.valuesOfWord.get(i)) + "\n";
        }
        return res;
    }

    public String listDef() {
        String res = "";
        for (String i : this.valuesOfDefinition.keySet()) {
            res += i + ": " + String.join(", ", this.valuesOfDefinition.get(i)) + "\n";
        }
        return res;
    }
}
