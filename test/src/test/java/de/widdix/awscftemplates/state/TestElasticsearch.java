package de.nholuongut.awscftemplates.state;

import com.amazonaws.services.cloudformation.model.Parameter;
import de.nholuongut.awscftemplates.ACloudFormationTest;
import org.junit.Test;

public class TestElasticsearch extends ACloudFormationTest {

    @Test
    public void test() {
        final String vpcStackName = "vpc-2azs-" + this.random8String();
        final String clientStackName = "client-" + this.random8String();
        final String stackName = "elasticsearch-" + this.random8String();
        try {
            this.createStack(vpcStackName, "vpc/vpc-2azs.yaml");
            try {
                this.createStack(clientStackName,
                        "state/client-sg.yaml",
                        new Parameter().withParameterKey("ParentVPCStack").withParameterValue(vpcStackName)
                );
                try {
                    this.createStack(stackName,
                            "state/elasticsearch.yaml",
                            new Parameter().withParameterKey("ParentVPCStack").withParameterValue(vpcStackName),
                            new Parameter().withParameterKey("ParentClientStack").withParameterValue(clientStackName),
                            new Parameter().withParameterKey("DomainName").withParameterValue(stackName)
                    );
                    // TODO how can we check if this stack works? start a bastion host and try to connect?
                } finally {
                    this.deleteStack(stackName);
                }
            } finally {
                this.deleteStack(clientStackName);
            }
        } finally {
            this.deleteStack(vpcStackName);
        }
    }

}
