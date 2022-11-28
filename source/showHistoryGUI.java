import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;

public class showHistoryGUI implements ActionListener {
    public JButton btnClear;
    public JButton btnRefresh;
    public JTextArea display;
    public SlangDictionary sd;
    // private SlangDictionary sd

    showHistoryGUI(SlangDictionary _sd) {
        Font font = new Font("Times New Roman", 0, 20);
        btnClear = new JButton("Clear");
        btnRefresh = new JButton("Refresh");
        
        display = new JTextArea(20, 30);
        display.setFont(font);
        this.sd = _sd;
    }

    public JPanel createAndShowSearchWordGUI() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        // container.setBorder(new TitledBorder(new EtchedBorder(), "Searching
        // history"));

        container.add(this.btnClear);

        JPanel historyPanel = new JPanel();
        historyPanel.setBorder(new TitledBorder(new EtchedBorder(), "Searching history"));

        display.setEditable(false); // set textArea non-editable
        JScrollPane scroll = new JScrollPane(display);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        scroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        historyPanel.add(scroll);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));

        btnClear.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRefresh.setAlignmentX(Component.CENTER_ALIGNMENT);
       
        btnPanel.add(btnRefresh);
        btnPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        btnPanel.add(btnClear);

        historyPanel.add(btnPanel);

        btnClear.setActionCommand("clear");
        btnRefresh.setActionCommand("refresh");
        this.btnClear.addActionListener(this);
        
        this.btnRefresh.addActionListener(this);
        container.add(historyPanel);

        this.displayHistory();
        return container;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd == "clear") {
            this.sd.clearHistory(EnvVariable.HISTOTY_FILE_NAME);
            this.display.setText("");
        } else if (cmd == "refresh") {
            this.displayHistory();
        }
    }
    public void displayHistory(){
        this.sd.loadHistory("history.txt");
            String history = "";
            ArrayList<String> h = this.sd.getHistory();
            
            for (String i : h) {
                history += i + "\n\n";
            }
            this.display.setText(history);
    }
}
