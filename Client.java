import java.net.URISyntaxException;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.*;
public class Client
{
	public static void main(String[] args)
	{
		try {
			final Socket socket = IO.socket("http://localhost:3000");
			socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
				
				@Override
				public void call(Object... args) {
					socket.emit("client message", "hi");
				}
			}).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
				
				@Override
				public void call(Object... args) {
					
				}
			});
			socket.connect();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
