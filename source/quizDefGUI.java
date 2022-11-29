import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
public class quizDefGUI implements ActionListener {
    public JButton btnA;
    public JButton btnB;
    public JButton btnC;
    public JButton btnD;
    public SlangDictionary sd;
    ArrayList<String> quiz;
   // private SlangDictionary sd

   quizDefGUI(SlangDictionary _sd){
    
    this.sd = _sd;
    quiz = sd.makeDefinitionQuiz();
    }
    public JPanel createAndShowSearchWordGUI() {
        Font font = new Font("Times New Roman", 0, 20);
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new TitledBorder(new EtchedBorder(), "QUIZ"));
        
       
        JLabel question = new JLabel(quiz.get(0));
        question.setFont(new Font("Times New Roman",0,40));
        question.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(Box.createRigidArea(new Dimension(100, 100)));

        JPanel AB = new JPanel();
        btnA = new JButton(quiz.get(1));
        btnB = new JButton(quiz.get(2));
        btnA.setFont(font);
        btnB.setFont(font);
        AB.add(btnA);
        AB.add(btnB);
        

        JPanel CD = new JPanel();
        btnC = new JButton(quiz.get(3));
        btnD = new JButton(quiz.get(4));
        btnC.setFont(font);
        btnD.setFont(font);
        CD.add(btnC);
        CD.add(btnD);

        container.add(question);

        container.add(Box.createRigidArea(new Dimension(100, 100)));

        container.add(AB);
        container.add(CD);

        btnA.addActionListener(this);
        btnB.addActionListener(this);
        btnC.addActionListener(this);
        btnD.addActionListener(this);

        return container;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
        String res = btn.getText();
        if (sd.getValuesOfDefinition().get(quiz.get(0)).contains(res)){
            JFrame success = new JFrame();

            JLabel s = new JLabel("CORRECT");
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
            success.setLocationRelativeTo(null);
            success.setVisible(true);
        } else {
            Font font = new Font("Times New Roman", 0, 20);
            JFrame fail = new JFrame();

            JLabel f = new JLabel("INCORRECT");

            JPanel p = new JPanel();
            f.setAlignmentX(Component.CENTER_ALIGNMENT);
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

            p.add(Box.createRigidArea(new Dimension(10, 40)));
            f.setFont(font);
            p.add(f);

            JButton ok = new JButton("TRY AGAIN");
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
