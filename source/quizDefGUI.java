import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    Font font = new Font("Times New Roman", 0, 20);
    this.sd = _sd;
    quiz = sd.makeSlangWordQuiz();
    }
    public JPanel createAndShowSearchWordGUI() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new TitledBorder(new EtchedBorder(), "Your definition"));
        
       
        JLabel question = new JLabel(quiz.get(0));

        JPanel AB = new JPanel();
        btnA = new JButton(quiz.get(1));
        btnB = new JButton(quiz.get(2));

        JPanel CD = new JPanel();
        btnC = new JButton(quiz.get(1));
        btnD = new JButton(quiz.get(2));

        container.add(AB);
        container.add(CD);



        return container;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
