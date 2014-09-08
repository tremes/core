package org.jboss.weld.examples.dices;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class RandomGenerator {

    private Random random = new Random(System.currentTimeMillis());

    public Integer generate() {
        return random.nextInt(6) + 1;
    }

}
