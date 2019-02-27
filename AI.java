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
        int healer_index=0 ;
        boolean sign=false;
        for( int i = 0 ; i < my_heroes.length ; ++i ) {
            if (my_heroes[i].getName() == HeroName.HEALER) {
                //Do Healer_Heal

                //Do Healer_Dodge


                //Do Healer_Attack
            }
            if(my_heroes[i].getName() == HeroName.GUARDIAN){
                Cell target_cell=null;
                for(int j=0;j<4;j++){
                    if(my_heroes[j].getName()== HeroName.HEALER)
                        healer_index=j;
                }
                //Do Guardian_fortify
                if(my_heroes[i].getAbility(AbilityName.GUARDIAN_FORTIFY).isReady() &&
                        my_heroes[healer_index].getCurrentHP()<101 && sign==false ){
                    sign=true;
                    world.castAbility(my_heroes[i],AbilityName.GUARDIAN_FORTIFY,my_heroes[healer_index].getCurrentCell());

                }

                //Do Guardian_Attack
                else {
                    int min=Integer.MAX_VALUE;
                    for(int j=0;j<4;j++){
                        if(!opp_heroes[j].getCurrentCell().isInVision()) continue;
                        int distace=world.manhattanDistance(my_heroes[i].getCurrentCell(),opp_heroes[j].getCurrentCell());
                        if(distace < min){
                            min=distace;
                            target_cell=opp_heroes[j].getCurrentCell();
                        }
                    }
                    world.castAbility(my_heroes[i],AbilityName.GUARDIAN_ATTACK,target_cell);
                }




            }
        }

    }
}
