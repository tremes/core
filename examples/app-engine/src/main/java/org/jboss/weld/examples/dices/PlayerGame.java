package org.jboss.weld.examples.dices;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@SessionScoped
public class PlayerGame implements Serializable {

    @Inject
    RandomGenerator generator;

    public List<Dice> getDices() {
        return dices;
    }

    List<Dice> dices = new ArrayList<Dice>();

    public void throwDices() {

        for (Dice dice : dices) {
            if (!dice.isHold()) {
                dice.setValue(generator.generate());
            }
        }
    }

    public void hold(int index) {
        Dice d = dices.get(index - 1);
        if (d.isHold()) {
            d.setHold(false);
        } else {
            d.setHold(true);
        }
    }

    public void start() {

        dices = new ArrayList<Dice>();
        for (int i = 0; i < 6; i++) {
            // angularjs has some problem with id value 0
            dices.add(new Dice(i + 1, generator.generate()));
        }
    }

}
