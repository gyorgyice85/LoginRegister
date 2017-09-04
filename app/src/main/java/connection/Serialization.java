package connection;

/**
 * Created by Joshi on 03.09.2017.
 */
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import model.Corner;
import model.Node;
import model.Zone;


public class Serialization {

    protected static final int MAX_GR_PIC_IN_KB = 10;
    private long   uid;
    private double cornerTopRightX;
    private double cornerTopRightY;
    private double cornerTopLeftX;
    private double cornerTopLeftY;
    private double cornerBottomRightX;
    private double cornerBottomRightY;
    private double cornerBottomLeftX;
    private double cornerBottomLeftY;
    private double punktX;
    private double punktY;
    private String iP;
    private int    countPeers;
    private boolean checked;
    private Zone zone;
    /*
    Corner topLeftCorner;
    Corner topRightCorner;
    Corner bottomLeftCorner;
    Corner bottomRightCorner;
    User user;
    */
    Node node = new Node(uid,checked,cornerTopRightX,cornerTopLeftY, cornerTopLeftX,cornerTopLeftY,cornerBottomRightX,cornerBottomRightY,cornerBottomLeftX,cornerBottomLeftY,punktX, punktY,iP,countPeers,zone);
    int peersCount;
    //Node node = new Node(topLeftCorner, topRightCorner, bottomLeftCorner, bottomRightCorner, user, peersCount);




    /**
     * Methode um Objekt Knoten/Node in ein ByteArray zu lesen
     *
     * @param node 	= einzuspeisender Knoten/Node
     * @return		= ByteArray buffer, in den das Knoten/Node Objekt eingespeist wurde
     */

    protected byte[] serializeNode(Node node){

        byte[] buffer = null;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;

        try{
            out = new ObjectOutputStream(bos);
            out.writeObject(node);
            out.flush();
            buffer = bos.toByteArray();
            bos.close();

        } catch(IOException e){
            e.printStackTrace();
        }

        return buffer;

    }



    /**
     * Methode, um aus einem ByteArray ein Knoten/Node wiederherzustellen
     *
     * @param buffer	= ByteArray, aus dem Knoten/Node Objekt hergestellt werden soll
     * @return			= Knoten/Node
     */

    protected Node deserializdeNode(byte[] buffer){

        Node node = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
        ObjectInput in = null;

        try{
            in = new ObjectInputStream(bis);

            node = (Node) in.readObject();

        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return node;

    }



    /**
     * Methode, die eine File(/Image) in ein ByteArray liest.
     *
     * @param file 								= File(/Bild), die eingespeist werden soll
     * @return									= ByteArray, in dem das Bild "steht"
     * @throws FileNotFoundException			= falls die File nicht existiert
     * @throws IOException						= Fehler beim Input/Output
     */
    protected byte[] imageSerializer(File file){

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try{
            byte[] buffer = new byte[MAX_GR_PIC_IN_KB*1024];

            FileInputStream fis 	 = new FileInputStream(file);
            int read;

            while((read = fis.read(buffer)) != -1){
                os.write(buffer, 0, read);
            }

            fis.close();
            os.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();

        }catch (IOException e){
            e.printStackTrace();
        }

        return os.toByteArray();

    }



    /**
     * Methode, die ein ByteArray zurueck in ein Bild konvertiert
     *
     * @param buffer							= das ByteArray
     * @param destination						= ZielDatei, in die das Bild geschrieben werden soll
     * @throws FileNotFoundException			= falls die File nicht existiert
     * @throws IOException						= Fehler beim Input/Output
     */
    // was das
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void imageDeSerializer(byte[] buffer, File destination)throws IOException{

        try(FileOutputStream fos = new FileOutputStream(destination)){
            fos.write(buffer);
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}