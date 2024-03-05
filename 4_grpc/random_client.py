import sys
import logging
import grpc

import random_pb2
import random_pb2_grpc


def callNextRandom( hostname ):
    with grpc.insecure_channel('localhost:50051') as channel:
        stub = random_pb2_grpc.RandomServiceStub(channel)
        for i in range(10):
            response = stub.NextRandom(random_pb2.NextRandomRequest())
            print(response.random, end=" ")
        print()

def callSetBounds( hostname, _min, _max ):
    with grpc.insecure_channel('localhost:50051') as channel:
        stub = random_pb2_grpc.RandomServiceStub(channel)
        response = stub.SetBounds(random_pb2.SetBoundsRequest(min=_min, max=_max))


if __name__ == '__main__':
    logging.basicConfig()
    if len( sys.argv ) == 2:
        callNextRandom( sys.argv[1] )
    if len( sys.argv ) == 4:
        callSetBounds( sys.argv[1], int( sys.argv[2] ), int( sys.argv[3] ))

