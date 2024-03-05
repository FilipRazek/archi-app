package if4030.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RandomClient {

    public static void main( String[] args ) {
        if( args.length == 0 ) return;
        try {
            Registry registry = LocateRegistry.getRegistry( args[0], 10099 );
            IRandom random = ( IRandom ) registry.lookup( "Random" );
            if (args.length == 1) {
                for (int i = 0; i < 10; i++) {
                    System.out.printf("%d ", random.nextRandom());
                }
                System.out.println("");
            } else if (args.length == 3) {
                int min = Integer.parseInt(args[1]);
                int max = Integer.parseInt(args[2]);
                random.setBounds(min, max);
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
