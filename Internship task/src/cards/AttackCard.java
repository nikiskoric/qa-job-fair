package cards;

public class AttackCard implements Card {
    private int number; // number for attack card is 3 to 7 and it represents damage that it gives

    public AttackCard(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void effect() {
        System.out.println("Attack card effect activated!\r\n");
    }

    @Override
    public String description(){
        return "Attack card";
    }
}
