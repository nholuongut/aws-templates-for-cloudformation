<iframe src="https://ghbtns.com/github-btn.html?user=nholuongut&repo=aws-cf-templates&type=star&count=true&size=large" frameborder="0" scrolling="0" width="160px" height="30px"></iframe>

> **New**: Manage Free Templates for AWS CloudFormation with the [nholuongut CLI](./cli/)

[Fargate](https://aws.amazon.com/fargate/) runs highly scalable containers scheduled by the [EC2 Container Service (ECS)](https://aws.amazon.com/ecs/). To run an application on Fargate you need the following components:

* Docker image published to [Docker Hub](https://hub.docker.com/) or [EC2 Container Registry (ECR)](https://aws.amazon.com/ecr/)
* Fargate cluster
* Fargate service

We provide you templates for the Fargate cluster and the service. You need to publish the Docker image.

# Fargate cluster
This template describes a fault tolerant and scalable Fargate cluster on AWS.

## Installation Guide
1. This templates depends on one of our [`vpc-*azs.yaml`](./vpc/) templates. [![Launch Stack](./img/launch-stack.png)](https://console.aws.amazon.com/cloudformation/home#/stacks/create/review?templateURL=https://s3-eu-west-1.amazonaws.com/nholuongut-aws-cf-templates-releases-eu-west-1/__VERSION__/vpc/vpc-2azs.yaml&stackName=vpc)
1. [![Launch Stack](./img/launch-stack.png)](https://console.aws.amazon.com/cloudformation/home#/stacks/create/review?templateURL=https://s3-eu-west-1.amazonaws.com/nholuongut-aws-cf-templates-releases-eu-west-1/__VERSION__/fargate/cluster.yaml&stackName=fargate-cluster&param_ParentVPCStack=vpc)
1. Click **Next** to proceed with the next step of the wizard.
1. Specify a name and all parameters for the stack.
1. Click **Next** to proceed with the next step of the wizard.
1. Click **Next** to skip the **Options** step of the wizard.
1. Check the **I acknowledge that this template might cause AWS CloudFormation to create IAM resources.** checkbox.
1. Click **Create** to start the creation of the stack.
1. Wait until the stack reaches the state **CREATE_COMPLETE**

### Dependencies
* `vpc/vpc-*azs.yaml` (**required**)
* `operations/alert.yaml` (recommended)
* `security/auth-proxy-*.yaml`
* `vpc/zone-*.yaml`
* `state/s3.yaml*`

# Fargate service
This template describes a fault tolerant and scalable Fargate service on AWS. The service scales based on CPU utilization.

We provide three service templates:
* `service-cluster-alb.yaml` uses the cluster's load balancer and path and/or host based routing.
* `service-dedicated-alb.yaml` includes a dedicated load balancer (ALB).
* `service-cloudmap.yaml` uses service discovery via Cloud Map instead of a load balancer.

## Using the cluster's load balancer and path and/or host based routing
This template describes a fault tolerant and scalable Fargate service that uses the cluster's load balancer and path and/or host based routing for incoming traffic.

### Installation Guide
1. This templates depends on one of our [`vpc-*azs.yaml`](./vpc/) templates. [![Launch Stack](./img/launch-stack.png)](https://console.aws.amazon.com/cloudformation/home#/stacks/create/review?templateURL=https://s3-eu-west-1.amazonaws.com/nholuongut-aws-cf-templates-releases-eu-west-1/__VERSION__/vpc/vpc-2azs.yaml&stackName=vpc)
1. This templates depends on our `cluster.yaml` template. [![Launch Stack](./img/launch-stack.png)](https://console.aws.amazon.com/cloudformation/home#/stacks/create/review?templateURL=https://s3-eu-west-1.amazonaws.com/nholuongut-aws-cf-templates-releases-eu-west-1/__VERSION__/fargate/cluster.yaml&stackName=fargate-cluster&param_ParentVPCStack=vpc)
1. [![Launch Stack](./img/launch-stack.png)](https://console.aws.amazon.com/cloudformation/home#/stacks/create/review?templateURL=https://s3-eu-west-1.amazonaws.com/nholuongut-aws-cf-templates-releases-eu-west-1/__VERSION__/fargate/service-cluster-alb.yaml&stackName=fargate-service&param_ParentVPCStack=vpc&param_ParentClusterStack=fargate-cluster)
1. Click **Next** to proceed with the next step of the wizard.
1. Specify a name and all parameters for the stack.
1. Click **Next** to proceed with the next step of the wizard.
1. Click **Next** to skip the **Options** step of the wizard.
1. Check the **I acknowledge that this template might cause AWS CloudFormation to create IAM resources.** checkbox.
1. Click **Create** to start the creation of the stack.
1. Wait until the stack reaches the state **CREATE_COMPLETE**

### Dependencies
* `vpc/vpc-*azs.yaml` (**required**)
* `fargate/cluster.yaml` (**required**)
* `operations/alert.yaml` (recommended)
* `vpc/zone-*.yaml`
* `state/client-sg.yaml`

## Using a dedicated load balancer for the service
This template describes a fault tolerant and scalable Fargate service that uses a dedicated load balancer for incoming traffic.

### Installation Guide
1. This templates depends on one of our [`vpc-*azs.yaml`](./vpc/) templates. [![Launch Stack](./img/launch-stack.png)](https://console.aws.amazon.com/cloudformation/home#/stacks/create/review?templateURL=https://s3-eu-west-1.amazonaws.com/nholuongut-aws-cf-templates-releases-eu-west-1/__VERSION__/vpc/vpc-2azs.yaml&stackName=vpc)
1. This templates depends on our `cluster.yaml` template. [![Launch Stack](./img/launch-stack.png)](https://console.aws.amazon.com/cloudformation/home#/stacks/create/review?templateURL=https://s3-eu-west-1.amazonaws.com/nholuongut-aws-cf-templates-releases-eu-west-1/__VERSION__/fargate/cluster.yaml&stackName=fargate-cluster&param_ParentVPCStack=vpc)
1. [![Launch Stack](./img/launch-stack.png)](https://console.aws.amazon.com/cloudformation/home#/stacks/create/review?templateURL=https://s3-eu-west-1.amazonaws.com/nholuongut-aws-cf-templates-releases-eu-west-1/__VERSION__/fargate/service-dedicated-alb.yaml&stackName=fargate-service&param_ParentVPCStack=vpc&param_ParentClusterStack=fargate-cluster)
1. Click **Next** to proceed with the next step of the wizard.
1. Specify a name and all parameters for the stack.
1. Click **Next** to proceed with the next step of the wizard.
1. Click **Next** to skip the **Options** step of the wizard.
1. Check the **I acknowledge that this template might cause AWS CloudFormation to create IAM resources.** checkbox.
1. Click **Create** to start the creation of the stack.
1. Wait until the stack reaches the state **CREATE_COMPLETE**

### Dependencies
* `vpc/vpc-*azs.yaml` (**required**)
* `fargate/cluster.yaml` (**required**)
* `operations/alert.yaml` (recommended)
* `security/auth-proxy-*.yaml`
* `vpc/zone-*.yaml`
* `state/s3.yaml*`
* `state/client-sg.yaml`


## Using service discovery (aka. Cloud Map)
This template describes a fault tolerant and scalable Fargate service that registers tasks at the service discovery registry (aka. Cloud Map). Allows inter-service communication without any load balancer in between.

### Installation Guide
1. This templates depends on one of our [`vpc-*azs.yaml`](./vpc/) templates. [![Launch Stack](./img/launch-stack.png)](https://console.aws.amazon.com/cloudformation/home#/stacks/create/review?templateURL=https://s3-eu-west-1.amazonaws.com/nholuongut-aws-cf-templates-releases-eu-west-1/__VERSION__/vpc/vpc-2azs.yaml&stackName=vpc)
1. This templates depends on our `cluster.yaml` template. [![Launch Stack](./img/launch-stack.png)](https://console.aws.amazon.com/cloudformation/home#/stacks/create/review?templateURL=https://s3-eu-west-1.amazonaws.com/nholuongut-aws-cf-templates-releases-eu-west-1/__VERSION__/fargate/cluster.yaml&stackName=fargate-cluster&param_ParentVPCStack=vpc)
1. This templates depends on our [`cloudmap-*.yaml`](./vpc/) template. [![Launch Stack](./img/launch-stack.png)](https://console.aws.amazon.com/cloudformation/home#/stacks/create/review?templateURL=https://s3-eu-west-1.amazonaws.com/nholuongut-aws-cf-templates-releases-eu-west-1/__VERSION__/vpc/cloudmap-private.yaml&stackName=cloudmap&param_ParentVPCStack=vpc)
1. This templates depends on our [`client-sg.yaml`](./state/) template. [![Launch Stack](./img/launch-stack.png)](https://console.aws.amazon.com/cloudformation/home#/stacks/create/review?templateURL=https://s3-eu-west-1.amazonaws.com/nholuongut-aws-cf-templates-releases-eu-west-1/__VERSION__/state/client-sg.yaml&stackName=client-sg&param_ParentVPCStack=vpc)
1. [![Launch Stack](./img/launch-stack.png)](https://console.aws.amazon.com/cloudformation/home#/stacks/create/review?templateURL=https://s3-eu-west-1.amazonaws.com/nholuongut-aws-cf-templates-releases-eu-west-1/__VERSION__/fargate/service-cloudmap.yaml&stackName=fargate-service&param_ParentVPCStack=vpc&param_ParentClusterStack=fargate-cluster&param_ParentCloudMapStack=cloudmap&param_ParentClientStack=client-sg)
1. Click **Next** to proceed with the next step of the wizard.
1. Specify a name and all parameters for the stack.
1. Click **Next** to proceed with the next step of the wizard.
1. Click **Next** to skip the **Options** step of the wizard.
1. Check the **I acknowledge that this template might cause AWS CloudFormation to create IAM resources.** checkbox.
1. Click **Create** to start the creation of the stack.
1. Wait until the stack reaches the state **CREATE_COMPLETE**

### Dependencies
* `vpc/vpc-*azs.yaml` (**required**)
* `fargate/cluster.yaml` (**required**)
* `vpc/cloudmap-private.yaml` (**required**)
* `state/client-sg.yaml` (**required**)
* `operations/alert.yaml` (recommended)
* `vpc/ssh-bastion.yaml`
