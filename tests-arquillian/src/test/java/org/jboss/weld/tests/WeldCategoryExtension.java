package org.jboss.weld.tests;

import org.jboss.arquillian.container.spi.client.container.DeploymentExceptionTransformer;
import org.jboss.arquillian.container.test.spi.client.deployment.AuxiliaryArchiveAppender;
import org.jboss.arquillian.core.spi.LoadableExtension;

public class WeldCategoryExtension implements LoadableExtension {

    private static final String MANAGED_CONTAINER_CLASS = "org.jboss.as.arquillian.container.managed.ManagedDeployableContainer";

    public void register(ExtensionBuilder builder) {
        builder.service(AuxiliaryArchiveAppender.class, CategoryArchiveAppender.class);
        builder.service(DeploymentExceptionTransformer.class, WeldExceptionTransformer.class);

        if (Validate.classExists(MANAGED_CONTAINER_CLASS)) {
            builder.observer(JBossASEEResourceManager.class);
        }
    }

}
