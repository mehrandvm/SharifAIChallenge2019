package client;

import client.model.*;

import java.util.Random;

public class AI
{

    private Random random = new Random();

    public void preProcess( World world )
    {
        System.out.println("pre process started") ;
        for ( int i = 0 ; i < world.getMap().getRowNum() ; ++i ) {
            for (int j = 0; j < world.getMap().getColumnNum(); ++j) {
                if (world.getMap().getCell(i,j).isWall()){
                    System.out.print("#");
                }
                else if (world.getMap().getCell(i,j).isInMyRespawnZone()){
                    System.out.print("-");
                }
                else if (world.getMap().getCell(i,j).isInOppRespawnZone()){
                    System.out.print("~");
                }
                else if (world.getMap().getCell(i,j).isInObjectiveZone()){
                    System.out.print("×");
                }
                else if (world.getMap().getCell(i,j).isInVision()){
                    System.out.print("O");
                }
                else {
                    System.out.print("Q");
                }
            }
            System.out.println(" ");
        }
    }

    public void pickTurn( World world )
    {
        System.out.println("pick started");
        if ( world.getCurrentTurn() == 0 )
        {
            world.pickHero( HeroName.SENTRY ) ;
        }
        if ( world.getCurrentTurn() == 1 )
        {
            world.pickHero( HeroName.SENTRY );
        }
        if ( world.getCurrentTurn() == 2 )
        {
            world.pickHero( HeroName.SENTRY );
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
//        if ( !( my_heroes[0].getCurrentCell().isInObjectiveZone() ) || !( my_heroes[1].getCurrentCell().isInObjectiveZone() ) || !( my_heroes[2].getCurrentCell().isInObjectiveZone() ) || !( my_heroes[3].getCurrentCell().isInObjectiveZone() ) )
//        {
//            for ( int i = 0 ; i < trg_cell.length ; ++i )
//            {
//                trg_cell[i] = obj_cell[i*3] ;
//            }
//            Direction[] dir_1 = world.getPathMoveDirections( my_heroes[0].getCurrentCell() , trg_cell[0] ) ;
//            Direction[] dir_2 = world.getPathMoveDirections( my_heroes[1].getCurrentCell() , trg_cell[1] ) ;
//            Direction[] dir_3 = world.getPathMoveDirections( my_heroes[2].getCurrentCell() , trg_cell[2] ) ;
//            Direction[] dir_4 = world.getPathMoveDirections( my_heroes[3].getCurrentCell() , trg_cell[3] ) ;
//            world.moveHero( my_heroes[0] , dir_1[0]);
//            world.moveHero( my_heroes[1] , dir_2[0]);
//            world.moveHero( my_heroes[2] , dir_3[0]);
//            world.moveHero( my_heroes[3] , dir_4[0]);
//        }
//        if ( world.getCurrentTurn() > 0 )
//        {
            Cell[] opp_respawn_zone = new Cell[4] ;
            for ( int i = 0 , k = 0 ; i < world.getMap().getRowNum() ; ++i )
            {
                for ( int j = 0 ; j < world.getMap().getColumnNum() ; ++j )
                {
                    if (world.getMap().getCells()[i][j].isInOppRespawnZone())
                    {
                        opp_respawn_zone[k] = world.getMap().getCells()[i][j];
                        ++k;
                    }
                }
            }

            Direction[] dir_1 = world.getPathMoveDirections( my_heroes[0].getCurrentCell() , opp_respawn_zone[0] ) ;
            Direction[] dir_2 = world.getPathMoveDirections( my_heroes[1].getCurrentCell() , opp_respawn_zone[1] ) ;
            Direction[] dir_3 = world.getPathMoveDirections( my_heroes[2].getCurrentCell() , opp_respawn_zone[2] ) ;
            Direction[] dir_4 = world.getPathMoveDirections( my_heroes[3].getCurrentCell() , opp_respawn_zone[3] ) ;
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
        for ( int i = 0 ; i < world.getMap().getRowNum() ; ++i )
        {
            for ( int j = 0 ; j < world.getMap().getColumnNum() ; ++j )
            {
                if ( world.getOppHero( world.getMap().getCells()[i][j] ) != null )
                {
                     Hero locked_hero = world.getOppHero( world.getMap().getCells()[i][j] ) ;
                     Cell target_cell = locked_hero.getCurrentCell() ;
                     world.castAbility( my_heroes[0] , AbilityName.SENTRY_RAY , target_cell );
                     world.castAbility( my_heroes[1] , AbilityName.SENTRY_RAY , target_cell );
                     world.castAbility( my_heroes[2] , AbilityName.SENTRY_RAY , target_cell );
                     world.castAbility( my_heroes[3] , AbilityName.SENTRY_RAY , target_cell );
                }
            }
        }
    }

}
