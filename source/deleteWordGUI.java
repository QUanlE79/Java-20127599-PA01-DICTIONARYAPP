import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class deleteWordGUI implements ActionListener {
    public JTextField word;
    public JButton btnDel;

    public SlangDictionary sd;
    // private SlangDictionary sd

    deleteWordGUI(SlangDictionary _sd) {
        Font font = new Font("Times New Roman", 0, 20);
        word = new JTextField("", 20);
        word.setFont(font);
        btnDel = new JButton("DELETE");
        this.sd = _sd;

    }

    public JPanel createAndShowSearchWordGUI() {
        Font font = new Font("Times New Roman", 0, 20);
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());

        JPanel wordPanel = new JPanel();
        wordPanel.setBorder(new TitledBorder(new EtchedBorder(), "DELETE WORD"));

        JLabel yourWord = new JLabel("Word ");
        yourWord.setFont(font);
        wordPanel.add(yourWord);
        wordPanel.add(this.word);

        wordPanel.add(btnDel);

        container.add(wordPanel);

        this.btnDel.addActionListener(this);

        return container;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String delW = word.getText();
        Font font = new Font("Times New Roman", 0, 20);
        if (this.sd.getValuesOfWord().containsKey(delW)) {

            JFrame option = new JFrame();

            JLabel noticed = new JLabel("Are you sure!");
            noticed.setFont(font);
            JPanel choose = new JPanel();
            noticed.setAlignmentX(Component.CENTER_ALIGNMENT);
            choose.setLayout(new BoxLayout(choose, BoxLayout.Y_AXIS));
            choose.add(Box.createRigidArea(new Dimension(10, 20)));
            choose.add(noticed);
            choose.add(Box.createRigidArea(new Dimension(10, 10)));
            JButton btnOverwrite = new JButton("OK");
            btnOverwrite.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sd.deleteSlangWord(delW);
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
            JButton btnCancel = new JButton("Cancel");
            btnCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    option.dispose();

                }
            });
            btnOverwrite.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnOverwrite.setFont(font);
            choose.add(btnOverwrite);
            choose.add(Box.createRigidArea(new Dimension(10, 10)));
            btnCancel.setFont(font);
            choose.add(btnCancel);
            option.add(choose);
            option.setSize(300, 200);
            option.setVisible(true);
        }

        else {

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
