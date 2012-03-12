import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA. User: stvad Date: 12.03.12 Time: 20:55 To change
 * this template use File | Settings | File Templates.
 */
public class WebServer
{
    public static void main(String[] args) throws Throwable
    {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true)
        {
            Socket newClient = serverSocket.accept();
            new Thread(new ClientListener(newClient)).start();
        }
    }
}
