package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private final static String TITLE = "My first graphical interface";
    private final static int PROPORTION = 2;

    private final JFrame frame = new JFrame(TITLE);
    private final Controller controller = new Controller();

    public SimpleGUIWithFileChooser() {
        final JPanel outerCanvas = new JPanel();
        outerCanvas.setLayout(new BorderLayout());
        frame.setContentPane(outerCanvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel ILowerCanvas = new JPanel();
        ILowerCanvas.setLayout(new BorderLayout());
        outerCanvas.add(ILowerCanvas, BorderLayout.SOUTH);
        final JTextArea textInput = new JTextArea();
        final JButton bsave = new JButton("Save");
        ILowerCanvas.add(textInput, BorderLayout.NORTH);
        ILowerCanvas.add(bsave, BorderLayout.SOUTH);
        bsave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.printString(textInput.getText());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        final JPanel IUpperCanvas = new JPanel();
        IUpperCanvas.setLayout(new BorderLayout());
        outerCanvas.add(IUpperCanvas, BorderLayout.NORTH);
        final JTextField textOutput = new JTextField();
        textOutput.setEditable(false);
        final JButton bBrouse = new JButton("Browse...");
        IUpperCanvas.add(textOutput, BorderLayout.CENTER);
        IUpperCanvas.add(bBrouse, BorderLayout.LINE_END);
        bBrouse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser();
                int b = fileChooser.showSaveDialog(bBrouse);
                if(b == JFileChooser.APPROVE_OPTION){
                    final File chosenFile = fileChooser.getSelectedFile();
                    controller.setFile(chosenFile);
                    textOutput.setText(controller.getPath());
                    List<String> lines = new LinkedList<>();
                    final Path FILE_PATH = FileSystems.getDefault().getPath("", controller.getPath());
                    try {
                        lines = java.nio.file.Files.readAllLines(FILE_PATH, StandardCharsets.UTF_8);
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    }
                    String fileText = "";
                    for(String line: lines){
                        fileText = "" + fileText + line + "\n";
                    }
                    textInput.setText(fileText);
                }else if(b == JFileChooser.CANCEL_OPTION){

                }else{
                    JOptionPane.showMessageDialog(frame, "An error with the file chooser has occurred");
                }
            } 
        });
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        //frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleGUIWithFileChooser().display();
    }
}
