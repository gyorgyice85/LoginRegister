package connection;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;

import model.Corner;
import model.Node;


public class ClientTest {
	
	protected static final int portNr = 9797;
	
	public static void main (String args[])throws NoSuchElementException, InterruptedException{

		Client client = new Client();
		
		Socket socket;
		
		
		try {
			socket = new Socket("127.0.0.1", portNr);
			
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//////////Send Image ////////////////////////////////////////////////////////////////////////////////////////////
			
			/**
			String path = "C://Users/Cedric/Pictures/test/placeholder.jpg";
			
			File file = new File(path);
			client.sendImageAsByteArray(socket, file);   */
			
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//////////Send Node //////////////////////////////////////////////////////////////////////////////////////////////
			
			// Initialisiere und befülle Node
			
			/*Corner topLeft = new Corner (1,1);
			Corner topRight = new Corner (2,2);
			Corner bottomLeft = new Corner (3,3);
			Corner bottomRight = new Corner (4,4);*/
			//User user = new User(1, "102.1.2.3");
			int kp = 9;
			//Node node1 = new Node(topLeft,topRight,bottomLeft,bottomRight, user, kp);
			
			//client.sendNodeAsByteArray(socket, node1);
			
			socket.close();
			
		} catch (UnknownHostException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
}