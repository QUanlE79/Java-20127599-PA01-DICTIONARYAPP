import java.awt.Component;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class randomWordGUI {
    public SlangDictionary sd;
    // private SlangDictionary sd

    randomWordGUI(SlangDictionary _sd) {
        this.sd = _sd;
    }

    public JPanel createAndShowSearchWordGUI() {
        Font font = new Font("Times New Roman", 0, 30);
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.setBorder(new TitledBorder(new EtchedBorder(), "SLANG WORD"));

        JLabel txt = new JLabel("On this day slang word");
        txt.setAlignmentX(Component.CENTER_ALIGNMENT);
        txt.setFont(font);
        container.add(txt);

        container.add(new JLabel(" "));

        String rWord = sd.randomSlangWord();

        JLabel word = new JLabel(rWord);
        word.setAlignmentX(Component.CENTER_ALIGNMENT);
        word.setFont(font);
        container.add(word);

        JLabel divideL = new JLabel("-----------");
        divideL.setFont(font);
        divideL.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(divideL);

        JLabel defL = new JLabel("DEFINITION");
        defL.setAlignmentX(Component.CENTER_ALIGNMENT);
        defL.setFont(font);
        container.add(defL);

        container.add(new JLabel(" "));

        for (String i : sd.getValuesOfWord().get(rWord)) {
            JLabel def = new JLabel(i);
            def.setAlignmentX(Component.CENTER_ALIGNMENT);
            def.setFont(font);
            container.add(def);
        }

        return container;
    }
}
