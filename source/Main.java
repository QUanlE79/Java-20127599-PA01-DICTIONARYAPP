import javax.swing.JFrame;

public class Main {

    public static void createAndShowGUI(SlangDictionary sd) {
        JFrame frame = new JFrame("Slang Word Dictionary");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainGUI.gui(frame.getContentPane(), sd);


        frame.setSize(1170, 576);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            EnvVariable.sd = new SlangDictionary();
            EnvVariable.sd.loadData(EnvVariable.CURRENT_SLANG_WORD_FILE_NAME);
            createAndShowGUI(EnvVariable.sd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}