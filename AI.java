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
            world.pickHero( HeroName.BLASTER ) ;
        }
        if ( world.getCurrentTurn() == 1 )
        {
            world.pickHero( HeroName.BLASTER );
        }
        if ( world.getCurrentTurn() == 2 )
        {
            world.pickHero( HeroName.BLASTER );
        }
        if ( world.getCurrentTurn() == 3 )
        {
            world.pickHero( HeroName.BLASTER );
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

        Cell[] abjectiv_zone = world.getMap().getObjectiveZone() ;
        int index1 =  ( int )( Math.random() * abjectiv_zone.length ) ;
        int index2 =  ( int )( Math.random() * abjectiv_zone.length ) ;
        int index3 =  ( int )( Math.random() * abjectiv_zone.length ) ;
        int index4 =  ( int )( Math.random() * abjectiv_zone.length ) ;
        Direction[] dir_1 = world.getPathMoveDirections( my_heroes[0].getCurrentCell() , abjectiv_zone[index1] ) ;
        Direction[] dir_2 = world.getPathMoveDirections( my_heroes[1].getCurrentCell() , abjectiv_zone[index2] ) ;
        Direction[] dir_3 = world.getPathMoveDirections( my_heroes[2].getCurrentCell() , abjectiv_zone[index3] ) ;
        Direction[] dir_4 = world.getPathMoveDirections( my_heroes[3].getCurrentCell() , abjectiv_zone[index4] ) ;
        if ( !(my_heroes[0].getCurrentCell().isInObjectiveZone()) || !(my_heroes[1].getCurrentCell().isInObjectiveZone()) || !(my_heroes[2].getCurrentCell().isInObjectiveZone()) || !(my_heroes[3].getCurrentCell().isInObjectiveZone() ) )
        {
            if ( )
            {
                world.moveHero(my_heroes[0], dir_1[0]);
                world.moveHero(my_heroes[1], dir_2[0]);
                world.moveHero(my_heroes[2], dir_3[0]);
                world.moveHero(my_heroes[3], dir_4[0]);
            }
        }
    }

    public void actionTurn( World world )
    {
        System.out.println( "action started" ) ;
        Hero[] my_heroes = world.getMyHeroes();
        Hero[] opp_heroes = world.getOppHeroes() ;
        for ( int i = 0 ; i < world.getMap().getRowNum() ; ++i )
        {
            for ( int j = 0 ; j < world.getMap().getColumnNum() ; ++j )
            {
                if ( world.getOppHero( world.getMap().getCells()[i][j] ) != null )
                {
                     Hero locked_hero = world.getOppHero( world.getMap().getCells()[i][j] ) ;
                     Cell target_cell = locked_hero.getCurrentCell() ;
                     if ( my_heroes[0].getAbility( AbilityName.BLASTER_BOMB ).isReady() ) world.castAbility( my_heroes[0] , AbilityName.BLASTER_BOMB , target_cell );
                     if ( my_heroes[1].getAbility( AbilityName.BLASTER_BOMB ).isReady() ) world.castAbility( my_heroes[1] , AbilityName.BLASTER_BOMB , target_cell );
                     if ( my_heroes[2].getAbility( AbilityName.BLASTER_BOMB ).isReady() ) world.castAbility( my_heroes[2] , AbilityName.BLASTER_BOMB , target_cell );
                     if ( my_heroes[3].getAbility( AbilityName.BLASTER_BOMB ).isReady() ) world.castAbility( my_heroes[3] , AbilityName.BLASTER_BOMB , target_cell );
                     if ( my_heroes[0].getAbility( AbilityName.BLASTER_ATTACK ).isReady() ) world.castAbility( my_heroes[0] , AbilityName.BLASTER_ATTACK , target_cell );
                     if ( my_heroes[1].getAbility( AbilityName.BLASTER_ATTACK ).isReady() ) world.castAbility( my_heroes[1] , AbilityName.BLASTER_ATTACK , target_cell );
                     if ( my_heroes[2].getAbility( AbilityName.BLASTER_ATTACK ).isReady() ) world.castAbility( my_heroes[2] , AbilityName.BLASTER_ATTACK , target_cell );
                     if ( my_heroes[3].getAbility( AbilityName.BLASTER_ATTACK ).isReady() ) world.castAbility( my_heroes[3] , AbilityName.BLASTER_ATTACK , target_cell );
                }
            }
        }
    }

}
