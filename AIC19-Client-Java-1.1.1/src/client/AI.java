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
        Cell[] respwnZone = world.getMap().getMyRespawnZone() ;
        Cell firstCell = null ;
        Cell secondCell = null ;
        Cell thirdCell = null ;
        Cell fourthCell = null ;
        int minDis = Integer.MAX_VALUE ;
        for ( int i = 0 ; i < objZone.length ; ++i )
        {
            if ( world.manhattanDistance( respwnZone[0] , objZone[i] ) < minDis )
            {
                firstCell = objZone[i] ;
                minDis = world.manhattanDistance( respwnZone[0] , objZone[i] ) ;
            }
        }
        minDis = Integer.MAX_VALUE ;
        for ( int i = 0 ; i < objZone.length ; ++i )
        {
            if ( world.manhattanDistance( respwnZone[1] , objZone[i] ) < minDis )
            {
                secondCell = objZone[i] ;
                minDis = world.manhattanDistance( respwnZone[1] , objZone[i] ) ;
            }
        }
        minDis = Integer.MAX_VALUE ;
        for ( int i = 0 ; i < objZone.length ; ++i )
        {
            if ( world.manhattanDistance( respwnZone[2] , objZone[i] ) < minDis )
            {
                thirdCell = objZone[i] ;
                minDis = world.manhattanDistance( respwnZone[2] , objZone[i] ) ;
            }
        }
        minDis = Integer.MAX_VALUE ;
        for ( int i = 0 ; i < objZone.length ; ++i )
        {
            if ( world.manhattanDistance( respwnZone[3] , objZone[i] ) < minDis )
            {
                fourthCell = objZone[i] ;
                minDis = world.manhattanDistance( respwnZone[3] , objZone[i] ) ;
            }
        }
        Direction[] dir_1 = world.getPathMoveDirections( myHeroes[0].getCurrentCell() , firstCell ) ;
        Direction[] dir_2 = world.getPathMoveDirections( myHeroes[1].getCurrentCell() , secondCell ) ;
        Direction[] dir_3 = world.getPathMoveDirections( myHeroes[2].getCurrentCell() , thirdCell ) ;
        Direction[] dir_4 = world.getPathMoveDirections( myHeroes[3].getCurrentCell() , fourthCell ) ;
        if ( dir_1.length > 0 ) world.moveHero( myHeroes[0] , dir_1[0] ) ;
        if ( dir_2.length > 0 ) world.moveHero( myHeroes[1] , dir_1[0] ) ;
        if ( dir_3.length > 0 ) world.moveHero( myHeroes[2] , dir_1[0] ) ;
        if ( dir_4.length > 0 ) world.moveHero( myHeroes[3] , dir_1[0] ) ;
    }

    public void actionTurn(World world) {
        System.out.println("action started");
        Hero[] my_heroes = world.getMyHeroes();
        Hero[] opp_heros = world.getOppHeroes();
        Cell[] targetCell = new Cell[4];

        //Map map = world.getMap();

        /* Specific Cells */
        int minDistance=Integer.MAX_VALUE;
        int distance=0;
        for(int i =0;i < my_heroes.length;i++){
            for (int j=0 ; j <opp_heros.length;j++){
                distance = world.manhattanDistance(my_heroes[i].getCurrentCell(),opp_heros[j].getCurrentCell());
                if(distance < minDistance){
                    minDistance=distance;
                    targetCell[i]=opp_heros[j].getCurrentCell();
                }

            }
        }
        for(int i=0 ;i<my_heroes.length;i++){
            /* If blaster_bomb is ready*/
            if(my_heroes[i].getAbility(AbilityName.BLASTER_BOMB).isReady()){
                world.castAbility(my_heroes[i],AbilityName.BLASTER_BOMB,targetCell[i]);
            }
            //else do blaster_attack
            else{
                world.castAbility(my_heroes[i],AbilityName.BLASTER_ATTACK,targetCell[i]);
            }
        }




        /* ------- Action Logic Section ------- */

        /* Special Ability Actions */

        /* Defending & Dodging Actions */

        /* Attacking Actions */

        /* ---- End of Action Logic Section ---- */

    }
    static  Cell validCell( Cell cell , World world  , int count )
    {
        if ( cell.isInObjectiveZone() && cell.isWall() && count < 2 )
        {
            int row = cell.getRow() ;
            int column = cell.getColumn() ;
            validCell( world.getMap().getCell( row + 1 , column ) , world , count + 1 ) ;
            validCell( world.getMap().getCell( row - 1 , column ) , world , count + 1 ) ;
            validCell( world.getMap().getCell( row , column + 1 ) , world , count + 1 ) ;
            validCell( world.getMap().getCell( row , column - 1 ) , world , count + 1 ) ;
        }
        return  cell ;
    }
}
