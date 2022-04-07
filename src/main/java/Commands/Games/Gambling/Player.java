package Commands.Games.Gambling;

import net.dv8tion.jda.api.entities.User;

public class Player {
    private final long id;
    private int balance; // TODO: Probably change this to currency or long to avoid overflow.
    private int lastBet;

    public Player(User user) {
        this.id = user.getIdLong();
        this.balance = 2000;
    }

    public int getBalance() {
        return balance;
    }

    /**
     * Using User.id because it will never change
     * Users on Discord can change their name freely.
     *
     * @return long id
     */
    public long getId() {
        return id;
    }

    /**
     * Simple reset hard coded to 2000 (int)
     * TODO: Make it admin command and change hard coded 2k into a given amount
     */
    public void resetBalance() {
        this.balance = 2000;
    }

    /**
     * Used to determine if the player has enough to place their bet
     * TODO: Might allow debt bets to a certain limit.
     *
     * @param amount
     * @return boolean if they have enough to cover the bet
     *
     */
    public boolean placeBet(int amount) {
        if(amount > balance || amount <= 0) {
            return false;
        } else {
            this.lastBet = amount;
            balance -= amount;
            return true;
        }
    }

    /**
     * Simple subtraction of their last bet from their account.
     */
    public void loser() {
        lastBet = 0;
    }

    /**
     * If the player wins give them twice their bet
     * TODO: Allow them to change multiplier of winnings based on game
     */
    public void winner() {
        balance += (lastBet * 2);
        lastBet = 0;
    }
}
