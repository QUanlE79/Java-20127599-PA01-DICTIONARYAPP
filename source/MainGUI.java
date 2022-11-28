import java.awt.*;
import javax.swing.*;

public class MainGUI {
    public static void gui(Container container) {
        // JFrame.setDefaultLookAndFeelDecorated(true);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

        listWordGUI lwGui = new listWordGUI();
        searchWordGUI swGui = new searchWordGUI();
        searchDefinitionGUI sdGui = new searchDefinitionGUI();
        showHistoryGUI shGui = new showHistoryGUI();
        addWordGUI awGui = new addWordGUI();
        editWordGUI edwGui = new editWordGUI();
        deleteWordGUI delwGui = new deleteWordGUI();
        randomWordGUI randwGui = new randomWordGUI();
        quizWordGUI qwGui = new quizWordGUI();
        quizDefGUI qdGui = new quizDefGUI();

        Font font = new Font("Times New Roman", 0, 20);
        tabbedPane.setFont(font);

        tabbedPane.setForeground(Color.black);

        // tabbedPane.addTab("List word", lwGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("Search by slang word", swGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("Search by definition", sdGui.createAndShowSearchWordGUI());
        // tabbedPane.addTab("Show search history", shGui.createAndShowSearchWordGUI());
        // tabbedPane.addTab("Add a new slang word",
        // awGui.createAndShowSearchWordGUI());
        // tabbedPane.addTab("Edit a slang word", edwGui.createAndShowSearchWordGUI());
        // tabbedPane.addTab("Delete a slang word",
        // delwGui.createAndShowSearchWordGUI());
        // tabbedPane.addTab("On this day slang word",
        // randwGui.createAndShowSearchWordGUI);
        // tabbedPane.addTab("Quiz word", qwGui.createAndShowSearchWordGUI());
        // tabbedPane.addTab("Quiz definition", qdGui.createAndShowSearchWordGUI());

        JButton btn = new JButton();
        JButton btn2 = new JButton();
        container.add(tabbedPane, BorderLayout.CENTER);
        container.add(btn, BorderLayout.WEST);
        container.add(btn2, BorderLayout.NORTH);
    }
}
