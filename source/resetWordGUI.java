import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class resetWordGUI implements ActionListener {
    public JButton btnReset;
    public JTextArea display;
    public SlangDictionary sd;

    // private SlangDictionary sd

    resetWordGUI(SlangDictionary _sd) {
        Font font = new Font("Times New Roman", 0, 20);
        btnReset = new JButton("Reset");
        
        display = new JTextArea(20, 30);
        display.setFont(font);
        this.sd = _sd;
    }

    public JPanel createAndShowSearchWordGUI() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        // container.setBorder(new TitledBorder(new EtchedBorder(), "Searching
        // history"));

        JPanel listWordPanel = new JPanel();
        listWordPanel.setBorder(new TitledBorder(new EtchedBorder(), "Dictionary"));

        display.setEditable(false); // set textArea non-editable
        JScrollPane scroll = new JScrollPane(display);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        scroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        listWordPanel.add(scroll);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));

        btnReset.setAlignmentX(Component.CENTER_ALIGNMENT);
       
        btnPanel.add(btnReset);

        listWordPanel.add(btnPanel);

        this.btnReset.addActionListener(this);
        
        container.add(listWordPanel);

        this.displayWord();

        return container;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EnvVariable.sd.resetSlangWord();
        displayWord();     
    }
    public void displayWord(){
        String data = "";
        for (String i : sd.getValuesOfWord().keySet()) {
            data += i + "\t: \t" + String.join(", ", sd.getValuesOfWord().get(i)) + "\n\n";
        }
        this.display.setText(data);
    }
}
