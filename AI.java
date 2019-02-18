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
        world.moveHero( my_heroes[1] , dir_2[0]);
        world.moveHero( my_heroes[2] , dir_3[0]);
        world.moveHero( my_heroes[3] , dir_4[0]);
    }

    public void actionTurn( World world )
    {
        System.out.println( "action started" ) ;
        Hero[] my_heroes = world.getMyHeroes();
        Hero[] opp_heroes = world.getOppHeroes() ;
        for ( int i = 0 ; i < opp_heroes.length ; ++i )
        {
            Ability[] att = my_heroes[0].getOffensiveAbilities() ;
            for ( int j = 0 ; j < att.length ; ++j )
            {
                if ( att[i].getRange() >= world.manhattanDistance( my_heroes[0].getCurrentCell() , opp_heroes[0].getCurrentCell() ) ) world.castAbility( my_heroes[0] , att[i] , opp_heroes[0].getCurrentCell() ) ;
                if ( att[i].getRange() >= world.manhattanDistance( my_heroes[0].getCurrentCell() , opp_heroes[1].getCurrentCell() ) ) world.castAbility( my_heroes[0] , att[i] , opp_heroes[1].getCurrentCell() ) ;
                if ( att[i].getRange() >= world.manhattanDistance( my_heroes[0].getCurrentCell() , opp_heroes[2].getCurrentCell() ) ) world.castAbility( my_heroes[0] , att[i] , opp_heroes[2].getCurrentCell() ) ;
                if ( att[i].getRange() >= world.manhattanDistance( my_heroes[0].getCurrentCell() , opp_heroes[3].getCurrentCell() ) ) world.castAbility( my_heroes[0] , att[i] , opp_heroes[3].getCurrentCell() ) ;
            }
        }
        for ( int i = 0 ; i < opp_heroes.length ; ++i )
        {
            Ability[] att = my_heroes[1].getOffensiveAbilities() ;
            for ( int j = 0 ; j < att.length ; ++j )
            {
                if ( att[i].getRange() >= world.manhattanDistance( my_heroes[1].getCurrentCell() , opp_heroes[0].getCurrentCell() ) ) world.castAbility( my_heroes[1] , att[i] , opp_heroes[0].getCurrentCell() ) ;
                if ( att[i].getRange() >= world.manhattanDistance( my_heroes[1].getCurrentCell() , opp_heroes[1].getCurrentCell() ) ) world.castAbility( my_heroes[1] , att[i] , opp_heroes[1].getCurrentCell() ) ;
                if ( att[i].getRange() >= world.manhattanDistance( my_heroes[1].getCurrentCell() , opp_heroes[2].getCurrentCell() ) ) world.castAbility( my_heroes[1] , att[i] , opp_heroes[2].getCurrentCell() ) ;
                if ( att[i].getRange() >= world.manhattanDistance( my_heroes[1].getCurrentCell() , opp_heroes[3].getCurrentCell() ) ) world.castAbility( my_heroes[1] , att[i] , opp_heroes[3].getCurrentCell() ) ;
            }
        }
        for ( int i = 0 ; i < opp_heroes.length ; ++i )
        {
            Ability[] att = my_heroes[2].getOffensiveAbilities() ;
            for ( int j = 0 ; j < att.length ; ++j )
            {
                if ( att[i].getRange() >= world.manhattanDistance( my_heroes[2].getCurrentCell() , opp_heroes[0].getCurrentCell() ) ) world.castAbility( my_heroes[2] , att[i] , opp_heroes[0].getCurrentCell() ) ;
                if ( att[i].getRange() >= world.manhattanDistance( my_heroes[2].getCurrentCell() , opp_heroes[1].getCurrentCell() ) ) world.castAbility( my_heroes[2] , att[i] , opp_heroes[1].getCurrentCell() ) ;
                if ( att[i].getRange() >= world.manhattanDistance( my_heroes[2].getCurrentCell() , opp_heroes[2].getCurrentCell() ) ) world.castAbility( my_heroes[2] , att[i] , opp_heroes[2].getCurrentCell() ) ;
                if ( att[i].getRange() >= world.manhattanDistance( my_heroes[2].getCurrentCell() , opp_heroes[3].getCurrentCell() ) ) world.castAbility( my_heroes[2] , att[i] , opp_heroes[3].getCurrentCell() ) ;
            }
        }
        for ( int i = 0 ; i < opp_heroes.length ; ++i )
        {
            Ability[] att = my_heroes[3].getOffensiveAbilities() ;
            for ( int j = 0 ; j < att.length ; ++j )
            {
                if ( att[i].getRange() >= world.manhattanDistance( my_heroes[3].getCurrentCell() , opp_heroes[0].getCurrentCell() ) ) world.castAbility( my_heroes[3] , att[i] , opp_heroes[0].getCurrentCell() ) ;
                if ( att[i].getRange() >= world.manhattanDistance( my_heroes[3].getCurrentCell() , opp_heroes[1].getCurrentCell() ) ) world.castAbility( my_heroes[3] , att[i] , opp_heroes[1].getCurrentCell() ) ;
                if ( att[i].getRange() >= world.manhattanDistance( my_heroes[3].getCurrentCell() , opp_heroes[2].getCurrentCell() ) ) world.castAbility( my_heroes[3] , att[i] , opp_heroes[2].getCurrentCell() ) ;
                if ( att[i].getRange() >= world.manhattanDistance( my_heroes[3].getCurrentCell() , opp_heroes[3].getCurrentCell() ) ) world.castAbility( my_heroes[3] , att[i] , opp_heroes[3].getCurrentCell() ) ;
            }
        }
    }

}
