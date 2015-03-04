package org.jboss.weld.tests.accessibility;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;

/**
 * @author Tomas Remes
 */
@Model
public class ReqTestBean2 {

    @EJB
    TestRemoteInterface  testEjb;


}
