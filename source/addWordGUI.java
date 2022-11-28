import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addWordGUI implements ActionListener {
    public JTextField wordTF;
    public JTextField defTF;
    public JButton btnAdd;

    public SlangDictionary sd;
    // private SlangDictionary sd

    addWordGUI(SlangDictionary _sd) {
        Font font = new Font("Times New Roman", 0, 20);
        wordTF = new JTextField("", 20);
        wordTF.setFont(font);
        defTF = new JTextField("", 20);
        defTF.setFont(font);
        btnAdd = new JButton("Add new word");
        this.sd = _sd;

    }

    public JPanel createAndShowSearchWordGUI() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JPanel wordPanel = new JPanel();
        wordPanel.setBorder(new TitledBorder(new EtchedBorder(), "Word"));

        JPanel defPanel = new JPanel();
        defPanel.setBorder(new TitledBorder(new EtchedBorder(), "Definition"));
        Font font = new Font("Times New Roman", 0, 20);
        JLabel yourWord = new JLabel("Your word ");
        yourWord.setFont(font);
        JLabel yourDef = new JLabel("Your definition ");
        yourDef.setFont(font);
        wordPanel.add(yourWord);
        wordPanel.add(this.wordTF);
        defPanel.add(yourDef);
        defPanel.add(this.defTF);

        container.add(wordPanel);
        container.add(defPanel);
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(btnAdd);

        this.btnAdd.addActionListener(this);

        return container;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String word = wordTF.getText();
        String def = defTF.getText();
        Font font = new Font("Times New Roman", 0, 20);
        if (this.sd.getValuesOfWord().containsKey(word)) {

            JFrame option = new JFrame();

            JLabel noticed = new JLabel("Duplicated Word!");
            noticed.setFont(font);
            JPanel choose = new JPanel();
            noticed.setAlignmentX(Component.CENTER_ALIGNMENT);
            choose.setLayout(new BoxLayout(choose, BoxLayout.Y_AXIS));
            choose.add(Box.createRigidArea(new Dimension(10, 20)));
            choose.add(noticed);
            choose.add(Box.createRigidArea(new Dimension(10, 10)));
            JButton btnOverwrite = new JButton("Overwrite");
            btnOverwrite.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sd.overwriteSlangWord(word, def);
                    sd.saveDictionary(EnvVariable.CURRENT_SLANG_WORD_FILE_NAME);
                    option.dispose();
                    JFrame success = new JFrame();

                    JLabel s = new JLabel("Success!");
                    Font font = new Font("Times New Roman", 0, 20);
                    s.setFont(font);

                    JPanel p = new JPanel();
                    s.setAlignmentX(Component.CENTER_ALIGNMENT);
                    p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
                    p.add(Box.createRigidArea(new Dimension(10, 40)));
                    p.add(s);

                    JButton ok = new JButton("OK");
                    ok.setFont(font);
                    ok.setAlignmentX(Component.CENTER_ALIGNMENT);
                    p.add(Box.createRigidArea(new Dimension(10, 10)));
                    p.add(ok);
                    success.add(p);
                    ok.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            success.dispose();
                        }
                    });
                    success.setSize(300, 200);
                    success.setVisible(true);
                }
            });
            JButton btnDup = new JButton("Duplicate");
            btnDup.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sd.duplicateSlangWord(word, def);
                    sd.saveDictionary(EnvVariable.CURRENT_SLANG_WORD_FILE_NAME);
                    option.dispose();
                    JFrame success = new JFrame();

                    JLabel s = new JLabel("Success!");
                    Font font = new Font("Times New Roman", 0, 20);
                    s.setFont(font);

                    JPanel p = new JPanel();
                    s.setAlignmentX(Component.CENTER_ALIGNMENT);
                    p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
                    p.add(Box.createRigidArea(new Dimension(10, 40)));
                    p.add(s);

                    JButton ok = new JButton("OK");
                    ok.setFont(font);
                    ok.setAlignmentX(Component.CENTER_ALIGNMENT);
                    p.add(Box.createRigidArea(new Dimension(10, 10)));
                    p.add(ok);
                    success.add(p);
                    ok.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            success.dispose();
                        }
                    });
                    success.setSize(300, 200);
                    success.setVisible(true);

                }
            });
            btnOverwrite.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnDup.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnOverwrite.setFont(font);
            choose.add(btnOverwrite);
            choose.add(Box.createRigidArea(new Dimension(10, 10)));
            btnDup.setFont(font);
            choose.add(btnDup);
            option.add(choose);
            option.setSize(300, 200);
            option.setVisible(true);

        } else {

            this.sd.addNewSlangWord(word, def);
            this.sd.saveDictionary(EnvVariable.CURRENT_SLANG_WORD_FILE_NAME);

            JFrame success = new JFrame();

            JLabel s = new JLabel("Success!");
            // Font font = new Font("Times New Roman", 0, 20);
            s.setFont(font);

            JPanel p = new JPanel();
            s.setAlignmentX(Component.CENTER_ALIGNMENT);
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
            p.add(Box.createRigidArea(new Dimension(10, 40)));
            p.add(s);

            JButton ok = new JButton("OK");
            ok.setFont(font);
            ok.setAlignmentX(Component.CENTER_ALIGNMENT);
            p.add(Box.createRigidArea(new Dimension(10, 10)));
            p.add(ok);
            success.add(p);
            ok.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    success.dispose();
                }
            });
            success.setSize(300, 200);
            success.setVisible(true);
        }
    }
}
