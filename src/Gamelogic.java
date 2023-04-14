import java.util.Random;

public class Gamelogic {
    public static int[][] logicCreator(int size){
        int logicBoard[][]=new int[size][size];
        Random rand = new Random();
        int center = size/2;

        int treasureCounter = 8;
        int wallCounter = 5;
        int marketCounter = 4;
        int lootThingCounter = 13;
        int trapCounter = 1+rand.nextInt(5);
        int treasureCounter2 = 0;
        int wallCounter2 = 0;
        int marketCounter2 = 0;
        int lootThingCounter2 = 0;
        int trapCounter2 = 0;

        for (int i = 0 ; i<size ; i++){
            for (int j = 0 ; j<size ; j++){
                logicBoard[i][j] = 0;
            }
        }

        logicBoard[0][0] = 44;
        logicBoard[0][1] = 44;
        logicBoard[0][size-1] = 44;
        logicBoard[0][size-2] = 44;

        if (size%2==0){
            logicBoard[center-1][center]=1;
        }
        else {
            logicBoard[center][center]=1;
        }

        logicBoard[1][1]= 4;
        logicBoard[size-2][size-2] = 4;
        logicBoard[size-2][1] = 4;
        logicBoard[1][size-2] = 4;

        while (treasureCounter2 != treasureCounter){
            int row = rand.nextInt(size);
            int col = rand.nextInt(size);
            if (logicBoard[col][row] ==0){
                if (treasureCounter2==0){
                    logicBoard[col][row] = 21;
                }
                if (treasureCounter2==1){
                    logicBoard[col][row] = 22;
                }
                if (treasureCounter2==2){
                    logicBoard[col][row] = 23;
                }
                if (treasureCounter2==3){
                    logicBoard[col][row] = 24;
                }
                if (treasureCounter2==4){
                    logicBoard[col][row] = 25;
                }
                if (treasureCounter2==5){
                    logicBoard[col][row] = 26;
                }
                if (treasureCounter2==6){
                    logicBoard[col][row] = 27;
                }
                if (treasureCounter2==7){
                    logicBoard[col][row] = 28;
                }
                treasureCounter2+=1;
                System.out.printf("%d %d\n" , col , row);
            }
        }
        while (wallCounter2 != wallCounter){
            int row = rand.nextInt(size);
            int col = rand.nextInt(size);
            if (logicBoard[col][row] ==0){
                logicBoard[col][row] = 3;
                wallCounter2 += 1;
            }
        }
        while (lootThingCounter2 != lootThingCounter){
            int row = rand.nextInt(size);
            int col = rand.nextInt(size);
            if (logicBoard[col][row] ==0){
                logicBoard[col][row] = 5;
                lootThingCounter2 += 1;
            }
        }
        while (trapCounter2 != trapCounter){
            int row = rand.nextInt(size);
            int col = rand.nextInt(size);
            if (logicBoard[col][row] ==0){
                logicBoard[col][row] = 6;
                trapCounter2 += 1;
            }
        }
        logicBoard[0][0] = 0;
        logicBoard[0][1] = 0;
        logicBoard[0][size-1] = 0;
        logicBoard[0][size-2] = 0;
        return logicBoard;
    }

    public static void main(String[] args) {
        logicCreator(5);
    }
}
