import java.io.*;
import java.net.Socket;
import java.util.regex.Pattern;

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
    protected String responseFileName;


    public ClientListener(Socket as) throws IOException
    {
        socket = as;
        inputStream = as.getInputStream();
        outputStream = as.getOutputStream();
    }

    protected void readInputHeaders() throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        Pattern getPattern = Pattern.compile("GET /.*");
        Pattern getFaviconPattern = Pattern.compile("GET /favicon.ico");
        String get = "";
        while (true)
        {
            String ss = bufferedReader.readLine();
            if (getPattern.matcher(ss).matches() && get == "") //&& !(getFaviconPattern.matcher(ss).matches()))
                get = ss;
            System.out.println(ss);
            if (ss == null || ss.trim().length() == 0)
                break;
        }
        //getPattern.matcher(get).end();
        Pattern httpPattern = Pattern.compile("HTTP/1.1");

        responseFileName = get.substring(get.indexOf('/') + 1, get.lastIndexOf(' '));
        System.out.println(responseFileName);
    }

    protected void writeResponse(String pageName) throws IOException
    {
        File page = null;
        if (pageName.isEmpty())
            page = new File("index.html");
        else
            page = new File(pageName);

        if (!page.exists())
        {
            page = new File("404.html");
        }

        //page
        FileReader fileReader = new FileReader(page);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder toBuild = new StringBuilder();
        String somes;
        while ((somes = bufferedReader.readLine()) != null)
        {
            toBuild.append(somes);
        }

        somes = toBuild.toString();

        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: SimpleServer/2012-03-12\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + somes.length() + "\r\n" +
                "Connection: close\r\n\r\n";
        String result = response + somes;
        outputStream.write(result.getBytes());
        outputStream.flush();
    }

    @Override
    public void run()
    {
        try
        {
            readInputHeaders();
            writeResponse(responseFileName);

        } catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally
        {
            try
            {
                socket.close();
            } catch (IOException e)
            {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

}
