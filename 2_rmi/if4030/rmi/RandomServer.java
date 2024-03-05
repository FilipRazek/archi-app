package if4030.rmi;
        
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
        
public class RandomServer implements IRandom {
    private int min = 0;
    private int max = 10;
        
    public void setBounds( int min, int max ) throws RemoteException {
        this.min = min;
        this.max = max;
    }
    
    public int nextRandom() throws RemoteException {
        return (int)Math.floor(Math.random() * (max - min + 1)) + min;
    }

    public static void main( String args[] ) {
        System.setProperty( "java.rmi.server.hostname", "localhost" );
        try {
            RandomServer random = new RandomServer();
            IRandom stub = ( IRandom ) UnicastRemoteObject.exportObject( random, 0 );
            
            LocateRegistry.createRegistry( 10099 );
            Registry registry = LocateRegistry.getRegistry( "localhost", 10099 );
            registry.bind( "Random", stub );

            System.err.println( "Server ready" );
        }
        catch( Exception e ) {
            System.err.println( "Server exception: " + e.toString() );
        }
    }
}
