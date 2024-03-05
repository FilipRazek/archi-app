package if4030.xmlrpc;

import java.net.URL;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class RandomClient {

    public static void main( String[] args ) {
        if( args.length == 0 ) return;
        
        try {
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL( new URL( "http://" + args[0] + ":8000/IF4030" ));
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig( config );
        
            if (args.length == 1) {
                Object[] params = new Object[]{};
                for (int i = 0; i < 10; i++) {
                    System.out.printf("%d ", client.execute("getRandom", params));
                }
                System.out.println("");
            } else if (args.length == 3) {
                int min = Integer.parseInt(args[1]);
                int max = Integer.parseInt(args[2]);
                Object[] params = new Object[]{min, max};
                client.execute("setBounds", params);
            } else {
                System.out.printf("Expected 1 or 3 arguments, got %d%n", args.length);
            }
        }
        catch ( Exception e ) {
            System.err.println( "Client exception: " + e.toString() );
            e.printStackTrace();
        }
    }
}
