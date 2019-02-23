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
            world.pickHero( HeroName.GUARDIAN );
        }
        if ( world.getCurrentTurn() == 2 )
        {
            world.pickHero( HeroName.GUARDIAN );
        }
        if ( world.getCurrentTurn() == 3 )
        {
            world.pickHero( HeroName.GUARDIAN );
        }
    }

    public void moveTurn( World world )
    {
        System.out.println("move started");
       Cell[] zone=world.getMap().getObjectiveZone();
       Hero[] my_hero=world.getMyHeroes();
       Hero[] opp_hero=world.getOppHeroes();
       int i,j,min;
       Cell[] target_cell=new Cell[4];
            for(i=0;i<4;i++) {
                if(my_hero[i].getName() == HeroName.HEALER)
                    continue;
                min = Integer.MAX_VALUE;
                if(!my_hero[i].getCurrentCell().isInObjectiveZone()) {
                    int index = (int)(Math.random()*(zone.length));
                    target_cell[i]=zone[ index];
                }
                else {
                    for(j=0;j<4;j++) {

                        int distance = world.manhattanDistance(my_hero[i].getCurrentCell(), opp_hero[j].getCurrentCell());
                        if (distance < min && opp_hero[j].getCurrentCell().isInObjectiveZone()) {
                            min = distance;
                            target_cell[i] = opp_hero[j].getCurrentCell();
                        }
                    }
                }
            }

            for(i=0;i<4;i++){
                if(my_hero[i].getName()==HeroName.HEALER){
                    int row=(int)((target_cell[(i+1)%4].getRow() + target_cell[(i+2)%4].getRow()
                            +target_cell[(i+3)%4].getRow())/3);
                    int col=(int)((target_cell[(i+1)%4].getColumn() + target_cell[(i+2)%4].getColumn()
                            +target_cell[(i+3)%4].getColumn())/3);
                    target_cell[i]=world.getMap().getCell(row,col);
                }
                Direction[] dirs=world.getPathMoveDirections(my_hero[i].getCurrentCell(),target_cell[i]);
                if(dirs.length == 0) continue;
                world.moveHero(my_hero[i],dirs[0]);
            }
        }

    public void actionTurn( World world )
    {
        System.out.println( "action started" ) ;
        Hero[] my_heroes = world.getMyHeroes();
        Hero[] opp_heroes = world.getOppHeroes() ;
        Cell[] target_attack =new Cell[4];
        int i,j,min,hp;
        for(i=0;i<4;i++){
            min=Integer.MAX_VALUE;
            if(my_heroes[i].getName() == HeroName.HEALER &&
                    my_heroes[i].getAbility(AbilityName.HEALER_HEAL).isReady() ) {
                for (j = 0; j < 4; j++) {
                    if (i == j) continue;
                        hp=my_heroes[i].getCurrentHP();
                    if( hp < min ){
                        min=hp;
                        target_attack[i]=my_heroes[i].getCurrentCell();
                    }
                }
            }
            else {
                for (j = 0; j < 4; j++) {
                    int distance = world.manhattanDistance(my_heroes[i].getCurrentCell(), opp_heroes[j].getCurrentCell());
                    if (distance < min) {
                        min = distance;
                        target_attack[i] = opp_heroes[j].getCurrentCell();
                    }
                }
            }
        }
        for (i=0;i<4;i++){
            if(my_heroes[i].getName() == HeroName.HEALER &&
                    my_heroes[i].getAbility(AbilityName.HEALER_HEAL).isReady()){
                world.castAbility(my_heroes[i],AbilityName.HEALER_HEAL,target_attack[i]);
            }
            else if(my_heroes[i].getName() == HeroName.HEALER){
                world.castAbility(my_heroes[i],AbilityName.HEALER_ATTACK,target_attack[i]);
            }
            else{
                world.castAbility(my_heroes[i],AbilityName.GUARDIAN_ATTACK,target_attack[i]);
            }
        }
    }
}
