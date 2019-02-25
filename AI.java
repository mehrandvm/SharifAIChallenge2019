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
            world.pickHero( HeroName.GUARDIAN ) ;
        }
        if ( world.getCurrentTurn() == 2 )
        {
            world.pickHero( HeroName.GUARDIAN ) ;
        }
        if ( world.getCurrentTurn() == 3 )
        {
            world.pickHero( HeroName.GUARDIAN ) ;
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
        int rowMax = Integer.MIN_VALUE ;
        int columnMax = Integer.MIN_VALUE ;
        int rowMin = Integer.MAX_VALUE ;
        int columnMin = Integer.MAX_VALUE ;
        Direction[] dir_1 ;
        Direction[] dir_2 ;
        Direction[] dir_3 ;
        Direction[] dir_4 ;
        dir_1 = world.getPathMoveDirections( my_heroes[0].getCurrentCell() , world.getMap().getCell( ( rowMin + rowMax ) / 2 , columnMin) ) ;
        dir_2 = world.getPathMoveDirections( my_heroes[1].getCurrentCell() , world.getMap().getCell( ( rowMin + rowMax ) / 2 , columnMax) ) ;
        dir_3 = world.getPathMoveDirections( my_heroes[2].getCurrentCell() , world.getMap().getCell( rowMax , ( columnMin + columnMax ) / 2 ) ) ;
        dir_4 = world.getPathMoveDirections( my_heroes[3].getCurrentCell() , world.getMap().getCell( rowMin , ( columnMin + columnMax ) / 2 ) ) ;
        for ( int i = 0 ; i < abjectiv_zone.length ; ++i )
        {
            if ( abjectiv_zone[i].getRow() > rowMax ) rowMax = abjectiv_zone[i].getRow() ;
            if ( abjectiv_zone[i].getColumn() > columnMax ) columnMax =abjectiv_zone[i].getColumn() ;
            if ( abjectiv_zone[i].getRow() < rowMin ) rowMin = abjectiv_zone[i].getRow() ;
            if ( abjectiv_zone[i].getColumn() < columnMin ) columnMin = abjectiv_zone[i].getColumn() ;
        }
        int opp_hero_HP = Integer.MAX_VALUE ;
        Cell target_cell ;
        if ( opp_heroes.length > 0 )
        {
            for ( int i = 0 ; i < opp_heroes.length ; ++i )
            {
                if ( opp_heroes[i].getCurrentHP() < opp_hero_HP && opp_heroes[i].getCurrentCell().isInObjectiveZone() )
                {
                    opp_hero_HP = opp_heroes[i].getCurrentHP() ;
                    target_cell = opp_heroes[i].getCurrentCell() ;
                    dir_2 = world.getPathMoveDirections( my_heroes[1].getCurrentCell() , target_cell ) ;
                    dir_3 = world.getPathMoveDirections( my_heroes[2].getCurrentCell() , target_cell ) ;
                    dir_4 = world.getPathMoveDirections( my_heroes[3].getCurrentCell() , target_cell ) ;
                }
            }
        }

        int currentHP = world.getAP() ;
        currentHP -= my_heroes[0].getMoveAPCost() ;
        if ( dir_2.length != 0 && currentHP - my_heroes[1].getMoveAPCost() > 0 ) world.moveHero(my_heroes[1], dir_2[0]) ;
        currentHP -= my_heroes[1].getMoveAPCost() ;
        if ( dir_3.length != 0 && currentHP - my_heroes[2].getMoveAPCost() > 0 ) world.moveHero(my_heroes[2], dir_3[0]) ;
        currentHP -= my_heroes[2].getMoveAPCost() ;
        if ( dir_4.length != 0 && currentHP - my_heroes[3].getMoveAPCost() > 0 ) world.moveHero(my_heroes[3], dir_4[0]) ;
    }

    public void actionTurn( World world ) {
        System.out.println("action started");
        Hero[] my_heroes = world.getMyHeroes();
        Hero[] opp_heroes = world.getOppHeroes();
        if ( opp_heroes.length != 0 )
        {
            Cell target_cell = null ;
            int oppHP = Integer.MAX_VALUE ;
            for( int i = 0 ; i < opp_heroes.length ; ++i  )
            {
                if ( opp_heroes[i].getCurrentHP() < oppHP )
                {
                    oppHP = opp_heroes[i].getCurrentHP() ;
                    target_cell = opp_heroes[i].getCurrentCell() ;
                }
            }
            if (my_heroes[1].getCurrentHP() < 81 && my_heroes[1].getAbility(AbilityName.GUARDIAN_FORTIFY).isReady() ) world.castAbility(my_heroes[1], AbilityName.GUARDIAN_FORTIFY , my_heroes[1].getCurrentCell() );
            if (my_heroes[2].getCurrentHP() < 81 && my_heroes[2].getAbility(AbilityName.GUARDIAN_FORTIFY).isReady() ) world.castAbility(my_heroes[2], AbilityName.GUARDIAN_FORTIFY , my_heroes[2].getCurrentCell() );
            if (my_heroes[3].getCurrentHP() < 81 && my_heroes[3].getAbility(AbilityName.GUARDIAN_FORTIFY).isReady() ) world.castAbility(my_heroes[3], AbilityName.GUARDIAN_FORTIFY , my_heroes[3].getCurrentCell() );
            if (my_heroes[1].getAbility(AbilityName.GUARDIAN_ATTACK ).isReady() && target_cell != null ) world.castAbility(my_heroes[1], AbilityName.GUARDIAN_ATTACK , target_cell );
            if (my_heroes[2].getAbility(AbilityName.GUARDIAN_ATTACK ).isReady() && target_cell != null ) world.castAbility(my_heroes[2], AbilityName.GUARDIAN_ATTACK , target_cell );
            if (my_heroes[3].getAbility(AbilityName.GUARDIAN_ATTACK ).isReady() && target_cell != null ) world.castAbility(my_heroes[3], AbilityName.GUARDIAN_ATTACK , target_cell );
        }
    }
    public Cell healerCell ( World world )
    {
        Hero[] myHero = world.getMyHeroes() ;
        for( int i = 0 ; i < myHero.length ; ++i )
        {
            if ( myHero[i].getName() == HeroName.HEALER && i != 0 )
            {
                Hero heroSwaper = myHero[i] ;
                m
            }
        }
    }
}
