package org.jboss.weld.examples.dices;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/dices/computer")
@RequestScoped
public class ComputerGame {

    @Inject
    RandomGenerator generator;

    Logger log = Logger.getLogger(this.getClass().getSimpleName());

    List<Dice> dices = new ArrayList<Dice>();

    private void throwDices() {
        log.info("Computer throws:");

        for (Dice dice : dices) {
            if (!dice.isHold()) {
                dice.setValue(generator.generate());
                log.info(String.valueOf(dice.getValue()));
            }
        }
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dice> start() {
        log.info("Computer throws:");
        for (int i = 0; i < 6; i++) {
            Dice dice = new Dice(i, generator.generate());
            dices.add(dice);
            log.info(String.valueOf(dice.getValue()));

        }

       keepHighest();
       for(int i =0 ; i<2;i++){
           throwDices();
           keepHighest();
       }
       return dices;
    }

    private void keepHighest(){

        for (Dice dice : dices) {
            if(dice.getValue()>=5){
                dice.setHold(true);
                log.info("Computer holds "+dice.getValue());
            }
        }
    }



}
