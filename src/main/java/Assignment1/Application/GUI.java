package Assignment1.Application;


import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;


public class GUI {


    static boolean isSearchBtnClicked = false;

    public GUI() throws IOException {
        Backend backend = new Backend();
        JFrame frame = new JFrame("Assignment 1");
        // Panel
        JPanel panel = new JPanel();

        //Frame Stuff
        frame.setSize(800, 300);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //url stuff
        JLabel urlLabel = new JLabel("Enter Wiki URL:  ");
        JTextField urlField = new JTextField(40);

        //Output stuff
        JLabel output1 = new JLabel("Output 1 ");
        JLabel output2 = new JLabel("Output 2 ");
        JLabel output3 = new JLabel("Output 3 ");
        JLabel output4 = new JLabel("Output 4 ");
        JLabel output5 = new JLabel("Output 5 ");

        //Search Button Stuff
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(ae -> {


            Scanner urlScan = new Scanner(urlField.getText());
            if (urlScan.hasNext() & urlField.getText().contains("https://")) {
                // Put link to url list array list
                try {
                  backend.inputUrl(urlField.getText());
                  output1.setText(backend.getResult(1));
                  output2.setText(backend.getResult(2));
                  output3.setText(backend.getResult(3));
                  output4.setText(backend.getResult(4));
                  output5.setText(backend.getResult(5 ));
                  frame.revalidate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Enter A Valid Link");
            }
        });

        Box box = Box.createVerticalBox();
        panel.add(box);

        urlLabel.setAlignmentX(JLabel.CENTER);
        box.add(urlLabel);

        urlField.setAlignmentX(JLabel.CENTER);
        box.add(urlField);

        searchButton.setAlignmentY(JLabel.CENTER);
        box.add(searchButton);

        output1.setHorizontalAlignment(JLabel.CENTER);
        box.add(output1);

        output2.setHorizontalAlignment(JLabel.CENTER);
        box.add(output2);

        output3.setHorizontalAlignment(JLabel.CENTER);
        box.add(output3);

        output4.setHorizontalAlignment(JLabel.CENTER);
        box.add(output4);

        output5.setHorizontalAlignment(JLabel.CENTER);
        box.add(output5);

        frame.add(panel);
        frame.revalidate();
    }

    public static boolean getSearchBtnState() {
        System.out.println(" is button clicked " + isSearchBtnClicked);
        return isSearchBtnClicked;
    }


}

