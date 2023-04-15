public class Player {
    int money = 100;
    int power = 100;
    int treasure_founded = 0;
    int x;
    int y;

    public int getMoney() {
        return money;
    }

    public int getPower() {
        return power;
    }

    public int getTreasure_founded() {
        return treasure_founded;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setTreasure_founded(int treasure_founded) {
        this.treasure_founded = treasure_founded;
    }
}
