import java.awt.*;
import javax.swing.*;

public class MainGUI {
    public static void gui(Container container, SlangDictionary sd) {
        // JFrame.setDefaultLookAndFeelDecorated(true);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

        resetWordGUI reset = new resetWordGUI(sd);
        searchWordGUI swGui = new searchWordGUI(sd);
        searchDefinitionGUI sdGui = new searchDefinitionGUI(sd);
        showHistoryGUI shGui = new showHistoryGUI(sd);
        addWordGUI awGui = new addWordGUI(sd);
        editWordGUI edwGui = new editWordGUI(sd);
        deleteWordGUI delwGui = new deleteWordGUI(sd);
        randomWordGUI randwGui = new randomWordGUI(sd);
        quizWordGUI qwGui = new quizWordGUI(sd);
        quizDefGUI qdGui = new quizDefGUI(sd);

        Font font = new Font("Times New Roman", 0, 20);
        tabbedPane.setFont(font);

        tabbedPane.setForeground(Color.black);

        // tabbedPane.addTab("List word", lwGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("Search by slang word", swGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("Search by definition", sdGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("Show search history", shGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("Add a new slang word", awGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("Edit a slang word", edwGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("Delete a slang word",delwGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("On this day slang word",randwGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("Quiz word", qwGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("Quiz definition", qdGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("RESET", reset.createAndShowSearchWordGUI());

        JButton btn = new JButton();
        JButton btn2 = new JButton();
        container.add(tabbedPane, BorderLayout.CENTER);
        container.add(btn, BorderLayout.WEST);
        container.add(btn2, BorderLayout.NORTH);
    }
}
