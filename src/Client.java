/*
Author - Rushith Karunaratne
Student number - 964678

 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;



public class Client {
    private static int port;
    private static String ip;
    private static Socket socket;
    private static String currentWord;
    private JFrame frame;
    private JTextField textField;
    private JTextField textField2;
    private JTextField textField3;


    public Client() {
        initialize();
    }

    public static void main(String[] args) throws IOException {


        port = Integer.parseInt(args[1]);
        ip = args[0];
        try {
            socket = new Socket(ip, port);
        }
        catch (Exception e) {
            System.out.println("Server unavailable");
            System.exit(0);
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Client window = new Client();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            String var4 = "I want to connect";
            outputStream.writeUTF(var4);
            System.out.println("Data sent to Server--> " + var4);
            outputStream.flush();

            while(inputStream.available() > 0) {
                String var5 = inputStream.readUTF();
                System.out.println(var5);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        //socket.close();
    }

    private void initialize() {

        frame = new JFrame();
        frame.setTitle("Client GUI");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        frame.getContentPane().setLayout(gridBagLayout);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(5, 5, 5, 5);
        gbc_textField.weightx = 0.5;
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 0;
        gbc_textField.gridy = 0;
        frame.getContentPane().add(textField, gbc_textField);
        textField.setColumns(10);

        JLabel jLabel = new JLabel("Check");
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.insets = new Insets(0,0,5,0);
        gbcLabel.gridx = 1;
        gbcLabel.gridy = 0;
        frame.getContentPane().add(jLabel,gbcLabel);


        JLabel jLabel2 = new JLabel("Add or Update");
        GridBagConstraints gbcLabel2 = new GridBagConstraints();
        gbcLabel2.insets = new Insets(0,0,5,0);
        gbcLabel2.gridx = 1;
        gbcLabel2.gridy = 1;
        frame.getContentPane().add(jLabel2,gbcLabel2);

        JLabel jLabel3 = new JLabel("Delete");
        GridBagConstraints gbcLabel3 = new GridBagConstraints();
        gbcLabel3.insets = new Insets(0,0,5,0);
        gbcLabel3.gridx = 1;
        gbcLabel3.gridy = 2;
        frame.getContentPane().add(jLabel3,gbcLabel3);


        JButton btnNewButton = new JButton("Submit");

        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton.gridx = 2;
        gbc_btnNewButton.gridy = 0;
        frame.getContentPane().add(btnNewButton, gbc_btnNewButton);

        JButton btnNewButton2 = new JButton("Submit");
        GridBagConstraints gbc_btnNewButton2 = new GridBagConstraints();
        gbc_btnNewButton2.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton2.gridx = 2;
        gbc_btnNewButton2.gridy = 1;
        frame.getContentPane().add(btnNewButton2, gbc_btnNewButton2);

        JButton btnNewButton3 = new JButton("Submit");
        GridBagConstraints gbc_btnNewButton3 = new GridBagConstraints();
        gbc_btnNewButton3.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton3.gridx = 2;
        gbc_btnNewButton3.gridy = 2;
        frame.getContentPane().add(btnNewButton3, gbc_btnNewButton3);

        textField2 = new JTextField();
        GridBagConstraints gbc_textField2 = new GridBagConstraints();
        gbc_textField2.insets = new Insets(0, 5, 0, 5);
        gbc_textField2.weightx = 0.5;
        gbc_textField2.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField2.gridx = 0;
        gbc_textField2.gridy = 1;
        frame.getContentPane().add(textField2, gbc_textField2);
        textField2.setColumns(10);

        textField3 = new JTextField();
        GridBagConstraints gbc_textField3 = new GridBagConstraints();
        gbc_textField3.insets = new Insets(0, 5, 0, 5);
        gbc_textField3.weightx = 0.5;
        gbc_textField3.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField3.gridx = 0;
        gbc_textField3.gridy = 2;
        frame.getContentPane().add(textField3, gbc_textField3);
        textField3.setColumns(10);



        JTextArea textArea = new JTextArea();
        GridBagConstraints gbc_textArea = new GridBagConstraints();
        gbc_textArea.insets = new Insets(0, 5, 5, 5);
        gbc_textArea.fill = GridBagConstraints.BOTH;
        gbc_textArea.gridx = 0;
        gbc_textArea.gridy = 3;
        frame.getContentPane().add(textArea, gbc_textArea);

        //Check Word
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Button clicked!" + textField.getText());
                //textArea.setText(textField.getText());

                try {
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                    outputStream.writeUTF("Check/"+textField.getText());
                    outputStream.flush();
                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                    String meaning  = inputStream.readUTF();
                    System.out.println(meaning);
                    textArea.setText("Meaning "+meaning);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //textArea.append(textField.getText()+"\n");
            }
        });

        //Add word
        btnNewButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Button clicked!" + textField2.getText());
                //textArea.setText(textField.getText());

                try {
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                    outputStream.writeUTF("Add/" + textField2.getText());
                    outputStream.flush();
                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                    String status  = inputStream.readUTF();
                    System.out.println(status);
                    textArea.setText(status);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //textArea.append(textField.getText()+"\n");
            }
        });

        //Delete Word
        btnNewButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Button clicked!" + textField3.getText());
               // textArea.setText(textField.getText());

                try {
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                    outputStream.writeUTF("Delete/" + textField3.getText());
                    outputStream.flush();
                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                    String status  = inputStream.readUTF();
                    System.out.println(status);
                    textArea.setText(status);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                //textArea.append(textField.getText()+"\n");
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {

                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                    outputStream.writeUTF("Client is closing");
                    outputStream.flush();
                    outputStream.close();

                    socket.close();
                    System.exit(0);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

    }

}

