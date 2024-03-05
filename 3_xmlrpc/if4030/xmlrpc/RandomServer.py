
import random

from xmlrpc.server import SimpleXMLRPCServer
from xmlrpc.server import SimpleXMLRPCRequestHandler

# Restrict to a particular path.
class RequestHandler( SimpleXMLRPCRequestHandler ):
    rpc_paths = ( '/IF4030', )

class RandomServer:
    def getData(self):
        return '42'

# Create server
with SimpleXMLRPCServer(( 'localhost', 8000 ),
                        requestHandler = RequestHandler ) as server:

    server.register_instance( RandomServer() )
    print( "server ready" )

    server.serve_forever()
