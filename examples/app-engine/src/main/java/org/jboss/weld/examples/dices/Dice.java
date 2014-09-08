package org.jboss.weld.examples.dices;

import java.io.Serializable;

public class Dice implements Serializable {

    private boolean hold = false;
    private int value;
    private int id;

    public Dice(int id, int value) {
        this.value = value;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isHold() {
        return hold;
    }

    public void setHold(boolean hold) {
        this.hold = hold;
    }

}
