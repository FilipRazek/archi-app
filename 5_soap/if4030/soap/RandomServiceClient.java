package if4030.soap;

public class RandomServiceClient {

    public static void main( String args[] ) {
        if( args.length == 0 ) return;
        try {
            RandomServiceStub random = new RandomServiceStub( "http://" + args[0] + ":8080/axis2/services/RandomService" );
            if( args.length == 1 ) {
                RandomServiceStub.NextRandom request = new RandomServiceStub.NextRandom();
                for (int i = 0; i < 10; i++) {
                    int randomResult = random.nextRandom(request).get_return();
                    System.out.printf("%d ", randomResult);
                }
                System.out.println("");
            }
            if( args.length == 3 ) {
                RandomServiceStub.SetBounds request = new RandomServiceStub.SetBounds();
                request.setMin(Integer.parseInt(args[1]));
                request.setMax(Integer.parseInt(args[2]));
                random.setBounds(request);
             }
        }
        catch ( Exception e ) {
            System.err.println( "Client exception: " + e.toString() );
            e.printStackTrace();
        }
    }
}
