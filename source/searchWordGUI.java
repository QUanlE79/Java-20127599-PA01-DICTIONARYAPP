import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
public class searchWordGUI implements ActionListener {
    public JTextField wordTF;
    public JButton btnSearch;
    public JTextArea display;
   // private SlangDictionary sd

    searchWordGUI(){
        wordTF = new JTextField("", 20);
        btnSearch = new JButton("Search");
        display = new JTextArea(16, 58);
    }
    public JPanel createAndShowSearchWordGUI() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JPanel readerKeyPanel = new JPanel();
        
        readerKeyPanel.setBorder(new TitledBorder(new EtchedBorder(), "Your word"));
        
        
       
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
        this.display.setText("A");
    }
}
