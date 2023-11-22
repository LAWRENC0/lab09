package it.unibo.mvc;

import java.util.List;
import java.util.LinkedList;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI { 
    private final static int PROPORTION = 2;
    private final JFrame frame = new JFrame();
    private final Controller controller = new SimpleController();

    public SimpleGUI(){
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTextField textField = new JTextField();
        final JTextArea textArea = new JTextArea();
        final JPanel bpanel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(bpanel, BorderLayout.SOUTH);
        final JButton bprint = new JButton("Print");
        bprint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setNextString(textField.getText());
                controller.printCurrentString();
            }
        });
        final JButton bhistory = new JButton("Show history");
        bhistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final List<String> list = new LinkedList<>(controller.getStringHistory());
                String text = "";
                for(String s: list){
                    text = text + s + "\n";
                }
                textArea.setText(text);
            }
        });
        panel.add(textField, BorderLayout.NORTH);
        panel.add(textArea, BorderLayout.CENTER);
        bpanel.add(bprint, BorderLayout.WEST);
        bpanel.add(bhistory, BorderLayout.LINE_END);
    }

    private void display(){
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        //frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleGUI().display();
    }

}
