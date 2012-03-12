import java.io.InputStream;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: stvad
 * Date: 12.03.12
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */
public class ClientListener implements Runnable
{
    protected Socket mySocket;
    protected InputStream myInputStream;

    @Override
    public void run()
    {

    }
}
