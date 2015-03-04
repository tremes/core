package org.jboss.weld.tests.accessibility;

import javax.ejb.Remote;

/**
 * @author Tomas Remes
 */
@Remote
public interface TestRemoteInterface {

    public String getMessage();
}
