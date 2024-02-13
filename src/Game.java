public class Game {
    public static void play (GameWheel g) {
        String lastColor;
        String currentColor = "";
        int streak = 0;
        int prize = 0;

        // do 3 rolls
        System.out.println("Spinning 3 times...");
        for (int i = 1; i <= 3; i++) {
            Slice slice = g.spinWheel();
            int money = slice.getPrizeAmount();
            lastColor = currentColor;
            currentColor = slice.getColor();

            if (lastColor.equals(currentColor)) { streak++; }
            prize += money;

            System.out.printf("Spin %d - Color: %s, Prize Amount: $%d\n", i, currentColor, money);
        }

        // double prize if needed, its >= in case you want to do more spins
        if (streak >= 3) {
            prize *= 2;
            System.out.printf("You got a streak of %d! Your money is now doubled!\n", 3);
        }
        System.out.printf("Total Prize Money: $%d\n", prize);
    }
}