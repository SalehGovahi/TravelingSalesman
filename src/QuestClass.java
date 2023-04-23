import java.util.Random;

public class QuestClass {
    public int pointer = 0;
    public String namme;
    public int[] arr = new int[10];
    int index = 0;
    public int quest = 0;

    Random rand = new Random();

    public String questName(){
        pointer = rand.nextInt((8)) + 21;
        System.out.println(pointer);
        if (pointer==21){
            namme = "Diamond ring";
            quest = 21;
        }
        if (pointer==22){
            namme = "Jeweled sword";
            quest = 22;
        }
        if(pointer==23){
            namme = "Golden glass";
            quest = 23;
        }
        if (pointer==24){
            namme = "Glass cup";
            quest = 24;
        }
        if (pointer==25){
            namme = "Wooden bow";
            quest = 25;
        }
        if (pointer==26){
            namme = "Steel shield";
            quest = 26;
        }
        if (pointer==27){
            namme = "Golden key";
            quest = 27;
        }
        if (pointer==28){
            namme = "Dragon scroll";
            quest = 28;
        }
        return namme;
    }
}
