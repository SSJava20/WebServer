import java.io.InputStream;
import java.io.OutputStream;
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
    protected Socket socket;
    protected InputStream inputStream;
    protected OutputStream outputStream;


    @Override
    public void run()
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
