import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class editWordGUI implements ActionListener {
    public JTextField oldWord;
    public JTextField newWord;
    public JButton btnChange;

    public SlangDictionary sd;
    // private SlangDictionary sd

    editWordGUI(SlangDictionary _sd) {
        Font font = new Font("Times New Roman", 0, 20);
        oldWord = new JTextField("", 20);
        oldWord.setFont(font);
        newWord = new JTextField("", 20);
        newWord.setFont(font);
        btnChange = new JButton("Change word");
        this.sd = _sd;

    }

    public JPanel createAndShowSearchWordGUI() {
        Font font = new Font("Times New Roman", 0, 20);
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JPanel wordPanel = new JPanel();
        wordPanel.setBorder(new TitledBorder(new EtchedBorder(), "Word"));

        JPanel defPanel = new JPanel();
        defPanel.setBorder(new TitledBorder(new EtchedBorder(), "New word"));

        JLabel yourWord = new JLabel("Your word ");
        yourWord.setFont(font);
        JLabel yourDef = new JLabel("Your new word");
        yourDef.setFont(font);
        wordPanel.add(yourWord);
        wordPanel.add(this.oldWord);
        defPanel.add(yourDef);
        defPanel.add(this.newWord);

        container.add(wordPanel);
        container.add(defPanel);
        container.add(btnChange);

        this.btnChange.addActionListener(this);

        return container;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String oldW = oldWord.getText();
        String newW = newWord.getText();
        if (this.sd.getValuesOfWord().containsKey(oldW)) {
            this.sd.editSlangWord(oldW, newW);
            this.sd.saveDictionary(EnvVariable.CURRENT_SLANG_WORD_FILE_NAME);

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
        } else {
            Font font = new Font("Times New Roman", 0, 20);
            JFrame fail = new JFrame();

            JLabel f = new JLabel("Word not found!");

            JPanel p = new JPanel();
            f.setAlignmentX(Component.CENTER_ALIGNMENT);
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

            p.add(Box.createRigidArea(new Dimension(10, 40)));
            f.setFont(font);
            p.add(f);

            JButton ok = new JButton("OK");
            ok.setAlignmentX(Component.CENTER_ALIGNMENT);
            p.add(Box.createRigidArea(new Dimension(10, 10)));
            ok.setFont(font);
            p.add(ok);
            fail.add(p);
            ok.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    fail.dispose();
                }
            });
            fail.setSize(300, 200);
            fail.setVisible(true);
        }
    }
}
