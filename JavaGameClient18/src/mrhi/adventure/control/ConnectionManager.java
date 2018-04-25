package mrhi.adventure.control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import mrhi.adventure.model.packet.MyPacket;

public class ConnectionManager {

	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	public ConnectionManager(Socket socket) {

		this.socket = socket;
		try {
			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean send(MyPacket packet)
	{
		boolean retbool = false;
		try {
			oos.writeObject(packet);
			retbool = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retbool;
	}

	public MyPacket receive() throws ClassNotFoundException, IOException
	{
		MyPacket retPacket = null;

		retPacket = (MyPacket)ois.readObject();
		return retPacket;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
