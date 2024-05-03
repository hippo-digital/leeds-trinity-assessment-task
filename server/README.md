# leeds-trinity-assessment-task
We want to provide the students with an independent task that can be done in around 45 minutes and will be able to be easily marked afterwards.

## Deployment Instructions
The API is intended to be run in a Docker container running in EKS (Elastic Kubernetes Service), inside the Hippo AWS training account.

1. Install AWS CLI on your machine (instructions [here](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html))
2. Install eksctl on your machine (instructions [here](https://docs.aws.amazon.com/emr/latest/EMR-on-EKS-DevelopmentGuide/setting-up-eksctl.html))
3. Install kubectl on your machine (instruction [here](https://docs.aws.amazon.com/eks/latest/userguide/install-kubectl.html))
4. Ensure your AWS CLI is authenticated using SSO (instruction are on the AWS access portal [here](https://hippodigital.awsapps.com/start/#/?tab=accounts))
5. Clone this repo to your machine
6. Run the following command to create the Kubernetes namespace:
   ```
   kubectl create namespace hippodigital-dev-taskapi-1
   ```
7. From the server directory, run the following command:
   ```
   eksctl create cluster -f cluster.yml
   ```
   This will create an EKS cluster in AWS utilising the defaults, except for those in the cluster.yml file, which sets the EC2 instances to t3.small rather than the default m5.large
8. From the server directory, run the following command:
   ```
   kubectl apply -f deploy.yml
   ```
   This applies the deploy.yml file, which deploys the container with the API 
9. From the server directory, run the following command:
   ```
   kubectl apply -f service.yml
   ```
   This applies the service.yml file, which sets up the loadbalancer
10. From the server directory, run the following command:
    ```
    kubectl apply -f ingress.yml
    ```
    This applies the ingress.yml file, which sets up the ingress rules for thre load balancer, to allow access via port 8080.
11. In order to verify everything has been set up correctly (assuming there have been no error messages shown), run the following command:
    ```
    kubectl -n hippodigital-dev-taskapi-1 describe service task-api-service
    ```
    This will generate an output similar to the below:
    ```
    Name:                     task-api-service
    Namespace:                hippodigital-dev-taskapi-1
    Labels:                   <none>
    Annotations:              <none>
    Selector:                 app=task-api
    Type:                     LoadBalancer
    IP Family Policy:         SingleStack
    IP Families:              IPv4
    IP:                       10.100.155.81
    IPs:                      10.100.155.81
    LoadBalancer Ingress:     a35fcd54d118f47b8a72aa4325b8a675-697660010.eu-west-2.elb.amazonaws.com
    Port:                     <unset>  8081/TCP
    TargetPort:               8081/TCP
    NodePort:                 <unset>  30936/TCP
    Endpoints:                192.168.72.157:8081,192.168.90.15:8081
    Session Affinity:         None
    External Traffic Policy:  Cluster
    Events:                   <none>
    ```
    LoadBalancer Ingress gives the external IP address of the service, so if you have re-deployed the service, this will be different and will need to be updated in the student instructions (with the addition of the 8080 port number).
