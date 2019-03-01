package client;

import client.model.*;

import java.util.Random;

public class AI
{

    private Random random = new Random();

    public void preProcess(World world)
    {
        System.out.println("pre process started");
    }

    public void pickTurn(World world)
    {
        System.out.println("pick started");
        //world.pickHero(HeroName.values()[world.getCurrentTurn()]);
        System.out.println("pick started");
        if ( world.getCurrentTurn() == 0 )
        {
            world.pickHero( HeroName.BLASTER ) ;
        }
        if ( world.getCurrentTurn() == 1 )
        {
            world.pickHero( HeroName.BLASTER ) ;
        }
        if ( world.getCurrentTurn() == 2 )
        {
            world.pickHero( HeroName.BLASTER ) ;
        }
        if ( world.getCurrentTurn() == 3 )
        {
            world.pickHero( HeroName.BLASTER ) ;
        }
    }

    public void moveTurn(World world)
    {
        System.out.println("move started");
        //Hero[] heroes = world.getMyHeroes();

        /* Specific Cells */


        /* Directions to Targets */


        /* Move logic for heroes */

        /* Additional Dynamic Moves */

    }

    public void actionTurn(World world) {
        System.out.println("action started");
        //Hero[] heroes = world.getMyHeroes();
        //Map map = world.getMap();

        /* Specific Cells */


        /* Directions to Targets */


        /* ------- Action Logic Section ------- */

        /* Special Ability Actions */

        /* Defending & Dodging Actions */

        /* Attacking Actions */

        /* ---- End of Action Logic Section ---- */

    }

}
