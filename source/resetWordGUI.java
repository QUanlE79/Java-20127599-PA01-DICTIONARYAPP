import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class resetWordGUI implements ActionListener {
    public JButton btnReset;

    public SlangDictionary sd;
    // private SlangDictionary sd

    resetWordGUI(SlangDictionary _sd) {
        Font font = new Font("Times New Roman", 0, 20);
        btnReset = new JButton("RESET");
        btnReset.setFont(font);
        this.sd = _sd;

    }

    public JPanel createAndShowSearchWordGUI() {
        Font font = new Font("Times New Roman", 0, 20);
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());

        container.add(btnReset);
        btnReset.addActionListener(this);
        return container;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sd.loadData(EnvVariable.ROOT_SLANG_WORD_FILE_NAME);
    }
}
