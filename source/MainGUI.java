import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class MainGUI {
    public static void gui(Container container, SlangDictionary sd) {
        // JFrame.setDefaultLookAndFeelDecorated(true);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

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

        tabbedPane.addTab("Search by slang word", swGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("Search by definition", sdGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("Show search history", shGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("Add a new slang word", awGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("Edit a slang word", edwGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("Delete a slang word",delwGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("On this day slang word",randwGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("Quiz word", qwGui.createAndShowSearchWordGUI());
        tabbedPane.addTab("Quiz definition", qdGui.createAndShowSearchWordGUI());
        //tabbedPane.addTab("RESET", reset.createAndShowSearchWordGUI());

        JButton btn = new JButton();
        JButton btn2 = new JButton();
        container.add(tabbedPane, BorderLayout.CENTER);
        container.add(btn, BorderLayout.WEST);
        container.add(btn2, BorderLayout.NORTH);
        JButton btnReset = new JButton("<html><div> R <br/> E <br/> S <br/> E <br/> T <br/></div></html>");
        btnReset.setFont(font);
        btnReset.addActionListener(new ActionListener(){

            @Override
                public void actionPerformed(ActionEvent e) {
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
                            SlangDictionary root = new SlangDictionary();
                            root.loadData(EnvVariable.ROOT_SLANG_WORD_FILE_NAME);
                            root.saveDictionary(EnvVariable.CURRENT_SLANG_WORD_FILE_NAME);
                            sd.resetSlangWord();
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
                
        });
        container.add(btnReset, BorderLayout.EAST);
    }
}
