package org.jboss.weld.tests;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.as.controller.client.OperationBuilder;
import org.jboss.as.controller.client.helpers.ClientConstants;
import org.jboss.dmr.ModelNode;
import org.jboss.dmr.Property;

public class JBossASMessaging {
    private static final Logger LOGGER = Logger.getLogger(JBossASEEResourceManager.class.getName());

    final private ModelControllerClient client;

    public JBossASMessaging(ModelControllerClient client){
        this.client = client;
    }

    void checkJmsQueue() throws IOException {
        ModelNode request = new ModelNode();
        request.get(ClientConstants.OP).set(ClientConstants.READ_RESOURCE_OPERATION);

        ModelNode address = request.get(ClientConstants.OP_ADDR);
        address.add(getSubsystem().getName(), getSubsystem().getValue());
        address.add(getJmsServer().getName(), getJmsServer().getValue());
        address.add("jms-queue", "testQueue");

        ModelNode response = client.execute(new OperationBuilder(request).build());
        if (response.get(ClientConstants.OUTCOME).asString().equals(ClientConstants.SUCCESS)) {
            return;
        }

        request = new ModelNode();
        request.get(ClientConstants.OP).set(ClientConstants.ADD);

        address = request.get(ClientConstants.OP_ADDR);
        address.add(getSubsystem().getName(), getSubsystem().getValue());
        address.add(getJmsServer().getName(), getJmsServer().getValue());
        address.add("jms-queue", "testQueue");

        ModelNode entries = request.get("entries");
        entries.add("queue/test");
        response = client.execute(new OperationBuilder(request).build());

        if (!response.get(ClientConstants.OUTCOME).asString().equals(ClientConstants.SUCCESS)) {
            throw new RuntimeException("Test JMS queue was not found and could not be created automatically: " + response);
        }
        LOGGER.log(Level.INFO, "Test JMS queue added");
    }

    void checkJmsTopic() throws IOException {
        ModelNode request = new ModelNode();
        request.get(ClientConstants.OP).set(ClientConstants.READ_RESOURCE_OPERATION);

        ModelNode address = request.get(ClientConstants.OP_ADDR);
        address.add(getSubsystem().getName(), getSubsystem().getValue());
        address.add(getJmsServer().getName(), getJmsServer().getValue());
        address.add("jms-topic", "testTopic");

        ModelNode response = client.execute(new OperationBuilder(request).build());

        if (response.get(ClientConstants.OUTCOME).asString().equals(ClientConstants.SUCCESS)) {
            return;
        }

        request = new ModelNode();
        request.get(ClientConstants.OP).set(ClientConstants.ADD);

        address = request.get(ClientConstants.OP_ADDR);
        address.add(getSubsystem().getName(), getSubsystem().getValue());
        address.add(getJmsServer().getName(), getJmsServer().getValue());
        address.add("jms-topic", "testTopic");

        ModelNode entries = request.get("entries");

        entries.add("topic/test");
        response = client.execute(new OperationBuilder(request).build());

        if (!response.get(ClientConstants.OUTCOME).asString().equals(ClientConstants.SUCCESS)) {
            throw new RuntimeException("Test JMS topic was not found and could not be created automatically: " + response);
        }
        LOGGER.log(Level.INFO, "Test JMS topic added");
    }

    private static final String SUBSYSTEM_NAME = "messaging";
    private static final String SERVER_ATTRIBUTE_NAME = "hornetq-server";
    private static final String SERVER_NAME = "default";

    Property getSubsystem() {
        return new Property(ClientConstants.SUBSYSTEM, new ModelNode(SUBSYSTEM_NAME));
    }

    Property getJmsServer() {
        return new Property(SERVER_ATTRIBUTE_NAME, new ModelNode(SERVER_NAME));

    }
}