/*
Author - Rushith Karunaratne
Student number - 964678

 */
import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;
import java.util.TreeMap;


public class Server {
    //private static HashMap<String,String> dict = new HashMap<>();
    private static Map<String, String>   dict =
            new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private static BufferedReader dataL;
    private static JFrame frame;
    private static JTextField textField;
    private static ServerGui serverGui;
    private static String fileName;


    private static int counter = 0;

    public Server() throws IOException {

    }

    public static void main(String[] args) throws IOException {

        int port = Integer.parseInt(args[0]);
        fileName = args[1];
        try {
            dataL = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        serverGui = new ServerGui();
        Thread t1 = new Thread(serverGui);
        t1.start();


        String row;
        while ((row = dataL.readLine()) != null) {
            String[] data = row.split(",");
            dict.put(data[0],data[1]);
        }
        dataL.close();

        ServerSocket serverSocket = null;
        //Socket clientSocket = null;
        try {

            serverSocket = new ServerSocket(port);
            System.out.println("Waiting for client connection-");
            while (true){
                Socket newClient = serverSocket.accept();
                ++counter;
                System.out.println(counter);
                Thread thread = new Thread(() -> {
                    try {
                        serveClient(newClient,counter);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                thread.start();
            }
        }
        catch (SocketException e){
            e.printStackTrace();
        }

    }
    private static void serveClient(Socket newClient,int clientNum ) throws IOException {

        //System.out.println(serverGui.getText());
        int number = clientNum;
        serverGui.setText(clientNum + " clients connected");
        DataInputStream inputStream= new DataInputStream(newClient.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(newClient.getOutputStream());
        System.out.println("CLIENT: " + inputStream.readUTF());
        //outputStream.writeUTF("Server: Hi Client " + counter + " !!!");
        //System.out.println("Name "+ dict.get(inputStream.readUTF()));
        while (true) {
            //inputStream.readUTF();
            try{
                String[] stream;
                String response = null;
                String message = inputStream.readUTF();
                System.out.println("Message From " + number +" " + message);
                stream = message.split("/");
                if(stream.length>1) {
                    System.out.println(stream[0]);
                    if (stream[0].equals("Check")) {
                        response = checkWord(stream[1]);
                    }
                    else if(stream[0].equals("Add")){
                        if (stream.length>2) {
                            response = addWord(stream[1], stream[2]);
                        }
                        else{
                            response = "Incorrect format";
                        }
                    }
                    else if(stream[0].equals("Delete")){
                        response = deleteWord(stream[1]);
                    }
                }
                if(response != null){
                    outputStream.writeUTF(response);
                }
                else{
                    outputStream.writeUTF("Not in dictionary");
                }
                outputStream.flush();

            }
            catch (EOFException ignored) {

            }
        }


    }

    private synchronized static String checkWord(String message){
        serverGui.setRecentAction("Word Checked - " + message);
        if (dict.containsKey(message)){
            System.out.println("Word exists");
            return dict.get(message);
        }
        else {
            System.out.println("Word not in dictionary");
            return null;
        }
    }

    private synchronized static String addWord(String word,String definition){
        serverGui.setRecentAction("Word Added - " + word);
        if (dict.containsKey(word)){
            dict.remove(word);
            dict.put(word,definition);
            return "Word updated";
        }
        else{
            dict.put(word,definition);
            return "Word added";
        }
    }
    private synchronized static String deleteWord(String message){
        serverGui.setRecentAction("Word Deleted - " + message);
        if(dict.containsKey(message)){
            System.out.println("Word deleted");
            dict.remove(message);
            return "Word deleted";
        }
        else{
            System.out.println("Word not found");
            return "Word not found";
        }
    }



}


