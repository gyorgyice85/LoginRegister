package connection;

/**
 * Created by Joshi on 03.09.2017.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings("unused")
public class Server {
    ServerSocket serverSocket = null;
    Serialization serialization = new Serialization();



    /**
     * Methode zum Empfangen eines ByteArrays des Senders
     *
     * @param serverSocket 		= ServerSocket, auf dem Empfangen wird
     * @return					= ByteArray, das gesendet wurde
     * @throws IOException		= Fehler bei Input/Output
     */

    protected byte[] receiveByteArray(ServerSocket serverSocket, Socket socket) throws IOException{

        byte[] buffer = null;

        try{

            DataInputStream dIn = new DataInputStream(socket.getInputStream());

            int length = dIn.readInt();

            if(length>0) {
                buffer = new byte[length];
                dIn.readFully(buffer, 0, buffer.length);
            }

            try{
                if(serverSocket!= null){
                    serverSocket.close();
                }
            } catch(IOException e){
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return buffer;
    }



    /**
     * Methode, um ein ByteArray in ein Bild zu konvertieren
     *
     * @param buffer 			= das ByteArray, das konvertiert werden soll
     * @return 					= die File/das Image
     */

    protected void saveFileFromByteArray(byte[] buffer, File file){

        try{
            serialization.imageDeSerializer(buffer, file);

        } catch(IOException e){
            e.printStackTrace();
        }


    }



    /**
     * Methode um eine File zu speichern
     * @param file		= die File, die gespeichert werden soll
     * @param path		= das Path zum Directory, in dem sie erstellt werden soll
     */

    protected void saveFile(File file, String path){
        String destination = (path + file.getName());
        Writer output = null;
        File newFile = new File(destination);

    }










    /**
     * HilfsMethoden zum Cutten (wird noch ersetzt)
     *
     */
    private static String returnMethodName(String longterm){

        int index = longterm.indexOf(',');

        String methodName = longterm.substring(0,index);
        return ("MethodName: " + methodName);
    }

    private static String returnSecondString(String longterm){

        int indexOfKomma	 = longterm.indexOf(',');
        indexOfKomma 	+= 1;
        int indexOfAt 	 = longterm.indexOf('@');




        String secondString = longterm.substring(indexOfKomma, indexOfAt);
        return ("SecondString: " + secondString + "index of N" + indexOfAt );
    }


    private static String returnXCoordinate(String longterm){

        int indexOfAt 	 = longterm.indexOf('@');
        indexOfAt	+= 1;
        int indexOfX 		 = longterm.indexOf('X');


        String x = longterm.substring(indexOfAt, indexOfX);
        double x1 = Double.parseDouble(x);
        return (x1 + "index of X" + indexOfX);
    }

    private static double returnYCoordinate(String longterm){

        int indexOfKomma 	 = longterm.indexOf('X');
        indexOfKomma 	+= 1;
        int indexOfY 		 = longterm.indexOf('Y');


        String y = longterm.substring(indexOfKomma, indexOfY);
        double y1 = Double.parseDouble(y);
        return y1;

    }

    private static int returnInteger(String longterm){

        int indexOfKomma 	 = longterm.indexOf('Y');
        indexOfKomma 	+= 1;
        int indexOfInteger   = longterm.indexOf('I');


        String i = longterm.substring(indexOfKomma, indexOfInteger);
        int i1   = Integer.parseInt(i);

        return i1;
    }

    private static double beweis (double wert){
        wert += 0.25;
        return wert;
    }
}