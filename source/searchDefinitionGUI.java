import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.util.*;
public class searchDefinitionGUI implements ActionListener {
    public JTextField wordTF;
    public JButton btnSearch;
    public JTextArea display;
    public SlangDictionary sd;
   // private SlangDictionary sd

   searchDefinitionGUI(SlangDictionary _sd){
    Font font = new Font("Times New Roman", 0, 20);
    wordTF = new JTextField("", 20);
    wordTF.setFont(font);
    btnSearch = new JButton("Search");
    display = new JTextArea(16, 58);
    display.setFont(font);
    this.sd = _sd;
    }
    public JPanel createAndShowSearchWordGUI() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JPanel readerKeyPanel = new JPanel();
        
        readerKeyPanel.setBorder(new TitledBorder(new EtchedBorder(), "Your definition"));
        
        
       
        readerKeyPanel.add(this.wordTF);
        readerKeyPanel.add(this.btnSearch);

        JPanel searchingResultsPanel = new JPanel();
        searchingResultsPanel.setBorder(new TitledBorder(new EtchedBorder(), "Searching results"));

        

        
        display.setEditable(false); // set textArea non-editable
        JScrollPane scroll = new JScrollPane(display);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        
        searchingResultsPanel.add(scroll);

        container.add(readerKeyPanel);
        container.add(searchingResultsPanel);

        this.btnSearch.addActionListener(this);

        return container;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String key = this.wordTF.getText();
        if (key.length() != 0) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            this.sd.addListHisory(key, dtf.format(now));
            this.sd.saveHistory(EnvVariable.HISTOTY_FILE_NAME);
        }
        Map<String, ArrayList<String>> res = sd.searchByDefinition(key);
        String data = "";
        for (String i : res.keySet()) {
            data += i + ": " + String.join(", ", res.get(i)) + "\n\n";
        }
        this.display.setText(data);
    }
}
