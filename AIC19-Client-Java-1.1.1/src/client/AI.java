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
        Hero[] myHeroes = world.getMyHeroes();
        Hero[] oppHeroes = world.getOppHeroes() ;
        Cell[] objZone = world.getMap().getObjectiveZone() ;
        int rowMax = Integer.MIN_VALUE ;
        int rowMin = Integer.MAX_VALUE ;
        int columnMax = Integer.MIN_VALUE ;
        int columnMin = Integer.MAX_VALUE ;
        // finding max & min of row & column
        for ( int i = 0 ; i < objZone.length ; ++i )
        {
            if ( objZone[i].getRow() > rowMax ) rowMax = objZone[i].getRow() ;
            if ( objZone[i].getRow() < rowMin ) rowMin = objZone[i].getRow() ;
            if ( objZone[i].getColumn() > columnMax ) columnMax = objZone[i].getColumn() ;
            if ( objZone[i].getColumn() < columnMin ) columnMin = objZone[i].getColumn() ;
        }
        // default initialization
        Direction[][] dirs = new  Direction[4][];
        dirs[0] = world.getPathMoveDirections( myHeroes[0].getCurrentCell() , world.getMap().getCell( rowMax , ( columnMax + columnMin ) / 2 ) ) ;
        dirs[1] = world.getPathMoveDirections( myHeroes[1].getCurrentCell() , world.getMap().getCell( rowMin , ( columnMax + columnMin ) / 2 ) ) ;
        dirs[2] = world.getPathMoveDirections( myHeroes[2].getCurrentCell() , world.getMap().getCell( ( rowMax + rowMin ) / 2 , columnMax ) ) ;
        dirs[3] = world.getPathMoveDirections( myHeroes[2].getCurrentCell() , world.getMap().getCell( ( rowMax + rowMin ) / 2 , columnMin ) ) ;
        // dynamic replacement
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
