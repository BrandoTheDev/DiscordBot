package Commands.Games.Gambling;

import net.dv8tion.jda.api.entities.User;

public class Player {
    private final long id;
    private int balance;
    private int lastBet;
    private int gamesPlayed = 0;

    public Player(User user) {
        this.id = user.getIdLong();
        this.balance = 2000;
    }

    public int getBalance() {
        return balance;
    }

    public long getId() {
        return id;
    }
    
    public void resetBalance() {
        this.balance = 2000;
    }

    public int getLastBet() {
        return lastBet;
    }

    public boolean placeBet(int amount) {
        if(amount > balance || amount <= 0) {
            return false;
        } else {
            this.lastBet = amount;
            return true;
        }
    }

    public void loser() {
        balance -= lastBet;
        lastBet = 0;
    }

    public void winner() {
        balance += (lastBet * 2);
        lastBet = 0;
    }
}
