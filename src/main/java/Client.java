import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;



public class Client extends Thread{
	Socket socketClient;
	ObjectOutputStream out;
	ObjectInputStream in;
	private Consumer<Serializable> callback;
	private String ipString;
	private int portInt;

	Client(Consumer<Serializable> callback, String ipString, int portInt){
		this.callback = callback;
		this.ipString = ipString;
		this.portInt = portInt;
	}

	public void run() {

		try {
			socketClient= new Socket(ipString, portInt);
			out = new ObjectOutputStream(socketClient.getOutputStream());
			in = new ObjectInputStream(socketClient.getInputStream());
			socketClient.setTcpNoDelay(true);
		}
		catch(Exception e) {}

		while(true) {
			try {
				String message = in.readObject().toString();
				callback.accept(message);
			}
			catch(Exception e) {}
		}

	}

	public void send(CFourInfo data) {
		try {
			out.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}