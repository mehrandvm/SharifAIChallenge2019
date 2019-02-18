package client;

import client.model.*;

import java.util.Random;

public class AI
{

    private Random random = new Random();

    public void preProcess( World world )
    {
        System.out.println("pre process started") ;
    }

    public void pickTurn( World world )
    {
        System.out.println("pick started");
        if ( world.getCurrentTurn() == 0 )
        {
            world.pickHero( HeroName.GUARDIAN ) ;
        }
        if ( world.getCurrentTurn() == 1 )
        {
            world.pickHero( HeroName.HEALER );
        }
        if ( world.getCurrentTurn() == 2 )
        {
            world.pickHero( HeroName.BLASTER );
        }
        if ( world.getCurrentTurn() == 3 )
        {
            world.pickHero( HeroName.SENTRY);
        }
    }

    public void moveTurn( World world )
    {
        System.out.println("move started");
        Cell[] obj_cell = world.getMap().getObjectiveZone() ;
        Hero[] my_heroes = world.getMyHeroes();
        Hero[] opp_heroes = world.getOppHeroes() ;
        Cell[] trg_cell = new Cell[4] ;
        int BLASTER = 0 , GUARDIAN = 0 , HEALER = 0 , SENTARY = 0 ;
        for ( int i = 0 ; i < opp_heroes.length ; ++i )
        {
            if ( opp_heroes[i].getName() == HeroName.BLASTER ) BLASTER++ ;
            if ( opp_heroes[i].getName() == HeroName.GUARDIAN ) GUARDIAN++ ;
            if ( opp_heroes[i].getName() == HeroName.HEALER ) HEALER++ ;
            if ( opp_heroes[i].getName() == HeroName.SENTRY ) SENTARY++ ;
        }
        for ( int i = 0 ; i < trg_cell.length ; ++i )
        {
            trg_cell[i] = obj_cell[(int)( Math.random()*trg_cell.length )] ;
        }
        Direction[] dir_1 = world.getPathMoveDirections( my_heroes[0].getCurrentCell() , trg_cell[0] ) ;
        Direction[] dir_2 = world.getPathMoveDirections( my_heroes[1].getCurrentCell() , trg_cell[1] ) ;
        Direction[] dir_3 = world.getPathMoveDirections( my_heroes[2].getCurrentCell() , trg_cell[2] ) ;
        Direction[] dir_4 = world.getPathMoveDirections( my_heroes[3].getCurrentCell() , trg_cell[3] ) ;
        world.moveHero( my_heroes[0] , dir_1[0]);
        world.moveHero( my_heroes[1] , dir_1[0]);
        world.moveHero( my_heroes[2] , dir_1[0]);
        world.moveHero( my_heroes[3] , dir_1[0]);
    }

    public void actionTurn( World world )
    {
        System.out.println( "action started" ) ;
        Hero[] my_heroes = world.getMyHeroes();
        Hero[] opp_heroes = world.getOppHeroes() ;
        Hero SENTRY ;
        Hero GUARDIAN ;
        Hero HEALER ;
        Hero BLASTER ;
        for ( int i = 0 ; i < my_heroes.length ; ++i )
        {
            if ( HeroName.SENTRY == my_heroes[i].getName() ) SENTRY = my_heroes[i] ;
            if ( HeroName.HEALER == my_heroes[i].getName() ) HEALER = my_heroes[i] ;
            if ( HeroName.GUARDIAN == my_heroes[i].getName() ) GUARDIAN = my_heroes[i] ;
            if ( HeroName.BLASTER == my_heroes[i].getName() ) BLASTER = my_heroes[i] ;
        }
        for ( int i = 0 ; i < opp_heroes.length ; ++i )
        {
            
        }
    }

}
