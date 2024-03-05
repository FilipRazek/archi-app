#include <iostream>
#include <memory>
#include <string>

#include <cstdlib>

#include <grpc++/grpc++.h>

#include "random.grpc.pb.h"
#include "random.pb.h"

using grpc::Channel;
using grpc::ClientContext;
using grpc::Status;

using if3040::grpc::NextRandomRequest;
using if3040::grpc::NextRandomResponse;
using if3040::grpc::RandomService;
using if3040::grpc::SetBoundsRequest;
using if3040::grpc::SetBoundsResponse;

int main(int argc, char *argv[]) {
  if (argc == 1)
    return -1;
  std::string host{argv[1]};

  std::shared_ptr<Channel> channel = grpc::CreateChannel(
      host + ":50051", grpc_impl::InsecureChannelCredentials());
  std::unique_ptr<RandomService::Stub> stub(RandomService::NewStub(channel));

  if (argc == 2) {
    for (int i = 0; i < 10; i++) {
        NextRandomRequest request;
        NextRandomResponse response;
        ClientContext context;
        stub->NextRandom(&context, request, &response);
        printf("%d ", response.random());
	}
    printf("\n");
  }

  if (argc == 4) {
    SetBoundsRequest request;
    SetBoundsResponse response;
    ClientContext context;

    request.set_min(atoi(argv[2]));
    request.set_max(atoi(argv[3]));

    stub->SetBounds(&context, request, &response);
  }

  return 0;
}
