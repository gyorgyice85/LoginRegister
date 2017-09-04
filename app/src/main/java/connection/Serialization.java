package connection;
package helper;

import helper.Corner;
import helper.User;
import helper.Node;

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


public class Serialization {

	protected static final int MAX_GR_PIC_IN_KB = 10;
	protected static final int STR_SEND_IMG = 1;
	protected static final int STR_SEND_NODE = 2;
	protected static final int RESERVED_BYTES_FOR_METHOD_CALL = 1;
	Corner topLeftCorner;
	Corner topRightCorner;
	Corner bottomLeftCorner;
	Corner bottomRightCorner;
	User user;
	int peersCount;
	Node node = new Node(topLeftCorner, topRightCorner, bottomLeftCorner, bottomRightCorner, user, peersCount);

	
	
	
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
	 * Methode, die ein ByteArray zurueck in ein Bild konvertiert und in einer File speichert
	 * 
	 * @param buffer							= das ByteArray
	 * @param destination						= ZielDatei, in die das Bild geschrieben werden soll
	 * @throws FileNotFoundException			= falls die File nicht existiert
	 * @throws IOException						= Fehler beim Input/Output
	 */
	public void imageDeSerializer(byte[] buffer, File destination)throws IOException{
			
			try(FileOutputStream fos = new FileOutputStream(destination)){
				fos.write(buffer);
				fos.close();
			}catch (Exception e){
				e.printStackTrace();
			}
			
	}
	
	
	
	/**
	 * Methode fillImageByteArray, zum Fuellen eines HilfsByteArrays
	 * 		Der Header (byte[0]) ist fuer den MethodenAufrufNamen reserviert
	 * 		Im Body (ab byte[1]) stehen die eigentlichen Nutzdaten
	 * 
	 * @param file 		= die File, die in das HilfsByteArray geschrieben werden soll
	 * @return			= das erzeugte HilfsByteArray
	 */
	
	protected byte[] fillImageByteArray(File file){
			
			byte methodName = (byte) STR_SEND_IMG;
			byte[] bufferHeader= new byte[1];
			bufferHeader[0] = methodName;
			
			byte[] bufferBody = imageSerializer(file);
			
			byte[] bufferTarget = new byte[RESERVED_BYTES_FOR_METHOD_CALL + bufferBody.length];
			
			System.arraycopy(bufferHeader, 0, bufferTarget, 0, 1);
			System.arraycopy(bufferBody, 0, bufferTarget, 1, bufferBody.length -1);
			
			return bufferTarget;
	}
	
	
	
	/**
	 * Methode fillNodeByteArray, zum Fuellen eines HilfsByteArrays
	 * 		Der Header (byte[0]) ist fuer den MethodenAufrufNamen reserviert
	 * 		Im Body (ab byte[1]) stehen die eigentlichen Nutzdaten
	 * 
	 * @param file		= die File, die in das HilfsByteArray geschrieben werden soll
	 * @return			= das erzeugte HilfsByteArray
	 */
	
	
	
	protected byte[] fillNodeByteArray(Node node){
		
		byte methodName = (byte) STR_SEND_NODE;
		byte[] bufferHeader= new byte[1];
		bufferHeader[0] = methodName;
		
		byte[] bufferBody = serializeNode(node);
		
		byte[] bufferTarget = new byte[RESERVED_BYTES_FOR_METHOD_CALL + bufferBody.length];
		
		System.arraycopy(bufferHeader, 0, bufferTarget, 0, 1);
		System.arraycopy(bufferBody, 0, bufferTarget, 1, bufferBody.length -1);
		
		return bufferTarget;
}

	
	
	/**
	 * Methode getByteHeader, die den Header eines HilfsByteArrays zurueckgibt
	 * 
	 * @param buffer		= das HilfsByteArray
	 * @return				= der Header (= die MethodenAufrufZiffer)
	 */

	protected int getByteHeader(byte[] buffer){
		int methodName = buffer[0]; 
		
		return methodName;
	}
	
	
	
	/**
	 * Methode getByteData, die den Body/Nutzdaten eines HilfsbyteArrays zurueckgibt
	 * 
	 * @param buffer  		= das HilfsByteArray
	 * @return				= den Body/die Nutzdaten als ByteArray
	 */
	
	protected byte[] getByteData(byte[] buffer){
		
		byte[] bufferBody = new byte[buffer.length -1];
		
		System.arraycopy(buffer, 1, bufferBody, 0, buffer.length -1);
		
		return bufferBody;
	}
}
	
