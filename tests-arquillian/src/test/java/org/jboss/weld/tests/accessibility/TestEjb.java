package org.jboss.weld.tests.accessibility;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * @author Tomas Remes
 */

@Stateless
@Local(TestLocalInterface.class)
@Remote(TestRemoteInterface.class)
public class TestEjb implements TestRemoteInterface, TestLocalInterface {

    public String getMessage() {
        return message;
    }

    private String message = "hello from ejb";
    
    
}
