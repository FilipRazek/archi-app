from concurrent import futures
import logging
import random

import grpc
import random_pb2
import random_pb2_grpc

class RandomServer( random_pb2_grpc.RandomServiceServicer ):
    def __init__(self):
        self.min = 0
        self.max = 10

    def SetBounds( self, request, context ):
        self.min = request.min
        self.max = request.max
        return random_pb2.SetBoundsResponse()

    def NextRandom( self, request, context ):
        random_response = int(random.random() * (self.max - self.min + 1)) + self.min
        return random_pb2.NextRandomResponse(random=random_response)

def serve():
    server = grpc.server( futures.ThreadPoolExecutor( max_workers=10 ))
    random_pb2_grpc.add_RandomServiceServicer_to_server( RandomServer(), server )
    server.add_insecure_port( '[::]:50051' )
    server.start()
    print( "server ready" )
    server.wait_for_termination()


if __name__ == '__main__':
    logging.basicConfig()
    serve()

