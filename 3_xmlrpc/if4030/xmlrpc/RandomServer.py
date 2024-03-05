
import random

from xmlrpc.server import SimpleXMLRPCServer
from xmlrpc.server import SimpleXMLRPCRequestHandler

# Restrict to a particular path.
class RequestHandler( SimpleXMLRPCRequestHandler ):
    rpc_paths = ( '/IF4030', )

class RandomServer:
    def __init__(self):
        self.min = 0
        self.max = 10
    
    def setBounds(self, min, max):
        self.min = min
        self.max = max
        return 0
    
    def getRandom(self):
        return int(random.random() * (self.max - self.min + 1)) + self.min

# Create server
with SimpleXMLRPCServer(( 'localhost', 8000 ),
                        requestHandler = RequestHandler ) as server:

    server.register_instance( RandomServer() )
    print( "server ready" )

    server.serve_forever()
