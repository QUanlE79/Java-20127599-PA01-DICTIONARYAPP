
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
public class SlangWord {
    private HashMap<String,ArrayList<String>> slangWord;
    private HashMap<String,ArrayList<String>> definition;
    
    public SlangWord(){
        this.slangWord = new HashMap<String,ArrayList<String>>();
        this.definition = new HashMap<String,ArrayList<String>>();
    }
    public HashMap<String,ArrayList<String>> getSlangWord(){
        return this.slangWord;
    }
    public HashMap<String,ArrayList<String>> getDefinition(){
        return this.definition;
    }
    public int loadData(String fileName){
        try{
            File fi = new File(fileName);
            Scanner myReader = new Scanner(fi);
            while (myReader.hasNextLine()) {

                String[] data = myReader.nextLine().split("`");
                if (data.length != 2) continue;
                ArrayList<String> definition = new ArrayList<String>(Arrays.asList(data[1].split("\\| ")));
                this.slangWord.put(data[0], definition);

                for (String i: definition){
                    if (this.definition.containsKey(i)){
                        this.definition.get(i).add(data[0]);
                    }else{
                        ArrayList<String>word = new ArrayList<String>();
                        word.add(data[0]);
                        this.definition.put(i, word);
                    }
                }
            }
            myReader.close();
            return 1;
            
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
