package connection;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import model.Node;

public class ParserTest {

	public static void main (String [] args) throws Exception{

		Server server 	= new Server();
		Serialization serialization = new Serialization();
		ServerSocket ss = null;
		Node node = null;

		try{
			System.out.println("Server is started");
			ss = new ServerSocket(9797);

			System.out.println("Server is waiting for request");
			Socket s = ss.accept();

			System.out.println("Cliet Connected");

			byte[] buffer = server.receiveByteArray(ss, s);

			System.out.println("BufferBytes: " + buffer.length);

			System.out.println("Received ByteArray");

			int methodName = serialization.getByteHeader(buffer);



			switch(methodName){

				case 1: {
					System.out.println("File Transfer Request");

					String pathDestination = "C://Users/Cedric/Pictures/test/placeholderNew1.jpg";

					File newFile = new File(pathDestination);

					byte[] bufferBody = serialization.getByteData(buffer);

					server.saveFileFromByteArray(bufferBody, newFile);

					System.out.println("Converted ByteArray");

					System.out.println("Saved File to: " + pathDestination);

					break;
				}

				case 2: {
					System.out.println("Node Transfer Request");

					byte[] bufferBody = serialization.getByteData(buffer);

					node = serialization.deserializdeNode(bufferBody);

					System.out.println(node);

					break;
				}
			}

			ss.close();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(ss != null)
					ss.close();
				System.out.println("ServerSocket closed");
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}

}
