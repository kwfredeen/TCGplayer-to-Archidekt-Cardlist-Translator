package TranslatorUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TranslatorForm {
    private JCheckBox outputCollectorNumberCheckBox;
    private JButton translateButton;
    private JTextArea textArea2;

    public TranslatorForm() {
        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputString = textArea1.getText();
                textArea2.setText(TextProcessor.process(inputString, false, appendMaybeboardToEachCheckBox.isSelected()));
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e){
        }

        JFrame frame = new JFrame("TCGplayer-Archidekt Translator");
        frame.setContentPane(new TranslatorForm().RootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500,600);

        frame.setVisible(true);
    }

    private JTextArea textArea1;
    private JPanel RootPanel;
    private JCheckBox appendMaybeboardToEachCheckBox;
}
