package org.jboss.weld.tests.accessibility;

import javax.ejb.Local;

/**
 * @author Tomas Remes
 */
@Local
public interface TestLocalInterface {

    public String getMessage();
}
