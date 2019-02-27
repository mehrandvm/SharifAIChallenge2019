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
            world.pickHero( HeroName.HEALER ) ;
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
    {System.out.println("move started");
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
        for ( int i = 0 ; i < abjectiv_zone.length ; ++i )
        {
            if ( abjectiv_zone[i].getRow() > rowMax ) rowMax = abjectiv_zone[i].getRow() ;
            if ( abjectiv_zone[i].getColumn() > columnMax ) columnMax =abjectiv_zone[i].getColumn() ;
            if ( abjectiv_zone[i].getRow() < rowMin ) rowMin = abjectiv_zone[i].getRow() ;
            if ( abjectiv_zone[i].getColumn() < columnMin ) columnMin = abjectiv_zone[i].getColumn() ;
        }
        Direction[] dir_1 ;
        Direction[] dir_2 ;
        Direction[] dir_3 ;
        Direction[] dir_4 ;
        dir_1 = world.getPathMoveDirections( my_heroes[0].getCurrentCell() , world.getMap().getCell( ( rowMin + rowMax ) / 2 , columnMin) ) ;
        dir_2 = world.getPathMoveDirections( my_heroes[1].getCurrentCell() , world.getMap().getCell( ( rowMin + rowMax ) / 2 , columnMax) ) ;
        dir_3 = world.getPathMoveDirections( my_heroes[2].getCurrentCell() , world.getMap().getCell( rowMax , ( columnMin + columnMax ) / 2 ) ) ;
        dir_4 = world.getPathMoveDirections( my_heroes[3].getCurrentCell() , world.getMap().getCell( rowMin , ( columnMin + columnMax ) / 2 ) ) ;
        
        if ( opp_heroes.length > 0 )
        {
            int minDis = Integer.MAX_VALUE ;
            for ( int i = 0 ; i < opp_heroes.length ; ++i )
            {
                if ( world.manhattanDistance( my_heroes[1].getCurrentCell() , opp_heroes[i].getCurrentCell() ) < minDis )
                {
                    minDis = world.manhattanDistance( my_heroes[1].getCurrentCell() , opp_heroes[i].getCurrentCell() ) ;
                    dir_2 = world.getPathMoveDirections( my_heroes[1].getCurrentCell() , opp_heroes[i].getCurrentCell() ) ;
                }
                if ( world.manhattanDistance( my_heroes[2].getCurrentCell() , opp_heroes[i].getCurrentCell() ) < minDis )
                {
                    minDis = world.manhattanDistance( my_heroes[2].getCurrentCell() , opp_heroes[i].getCurrentCell() ) ;
                    dir_3 = world.getPathMoveDirections( my_heroes[2].getCurrentCell() , opp_heroes[i].getCurrentCell() ) ;
                }
                if ( world.manhattanDistance( my_heroes[3].getCurrentCell() , opp_heroes[i].getCurrentCell() ) < minDis )
                {
                    minDis = world.manhattanDistance( my_heroes[3].getCurrentCell() , opp_heroes[i].getCurrentCell() ) ;
                    dir_4 = world.getPathMoveDirections( my_heroes[3].getCurrentCell() , opp_heroes[i].getCurrentCell() ) ;
                }
            }
        }
        Hero lowHPHero = null ;
        int HP = Integer.MAX_VALUE ;
        for ( int i = 1 ; i < my_heroes.length ; ++i )
        {
            if ( my_heroes[i].getCurrentHP() < HP && my_heroes[i].getCurrentHP() != 0 )
            {
                HP = my_heroes[i].getCurrentHP();
                lowHPHero = my_heroes[i] ;
            }
        }
        dir_1 = world.getPathMoveDirections( my_heroes[0].getCurrentCell() , lowHPHero.getCurrentCell() ) ;
        if ( dir_1.length != 0 ) world.moveHero(my_heroes[0], dir_1[0] );
        if ( dir_2.length != 0 ) world.moveHero(my_heroes[1], dir_2[0]) ;
        if ( dir_3.length != 0 ) world.moveHero(my_heroes[2], dir_3[0]) ;
        if ( dir_4.length != 0 ) world.moveHero(my_heroes[3], dir_4[0]) ;
    }

    public void actionTurn( World world ) {
        System.out.println("action started");
        Hero[] my_heroes = world.getMyHeroes();
        Hero[] opp_heroes = world.getOppHeroes();
        boolean sign=false;
        for( int i = 0 ; i < my_heroes.length ; ++i )
        {
            if ( my_heroes[i].getName() == HeroName.HEALER && i != 0 )
            {
                Hero heroSwaper = my_heroes[0] ;
                my_heroes[0] = my_heroes[i] ;
                my_heroes[i] = heroSwaper ;
            }
        }
        for( int i = 0 ; i < my_heroes.length ; ++i ) {
            if (my_heroes[i].getName() == HeroName.HEALER) {
                //Do Healer_Heal
                Hero iNeedHeal = my_heroes[1];
                if (my_heroes[2].getCurrentHP() < my_heroes[1].getCurrentHP() && my_heroes[2].getCurrentHP() < my_heroes[3].getCurrentHP())
                    iNeedHeal = my_heroes[2];
                if (my_heroes[3].getCurrentHP() < my_heroes[1].getCurrentHP() && my_heroes[3].getCurrentHP() < my_heroes[2].getCurrentHP())
                    iNeedHeal = my_heroes[3];
                if (my_heroes[0].getAbility(AbilityName.HEALER_HEAL).isReady())
                    world.castAbility(my_heroes[0], AbilityName.HEALER_HEAL, iNeedHeal.getCurrentCell());

                    //Do Healer_Attack
                else {
                    int min = Integer.MAX_VALUE;
                    Cell target_cell = null;

                    for (int j = 0; j < 4; j++) {
                        if (!opp_heroes[j].getCurrentCell().isInVision()) continue;
                        int distance = world.manhattanDistance(my_heroes[i].getCurrentCell(), opp_heroes[j].getCurrentCell());
                        if (distance < min) {
                            min = distance;
                            target_cell = opp_heroes[j].getCurrentCell();
                        }

                        world.castAbility(my_heroes[0], AbilityName.HEALER_ATTACK, target_cell);
                    }
                }
            }
            if(my_heroes[i].getName() == HeroName.GUARDIAN){
                int healer_index=0;
                Cell target_cell_a=null;
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
                        int distance=world.manhattanDistance(my_heroes[i].getCurrentCell(),opp_heroes[j].getCurrentCell());
                        if(distance < min){
                            min=distance;
                            target_cell_a=opp_heroes[j].getCurrentCell();
                        }
                    }
                    world.castAbility(my_heroes[i],AbilityName.GUARDIAN_ATTACK,target_cell_a);
                }

            }
        }
    }
}