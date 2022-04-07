package Commands.Games.Gambling;

import Commands.Command;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.*;

/**
 * This class represents a simple flip of a coin psuedo choosing if it lands on heads or tails
 * Adding our Player class we can introduce gambling aspects to allow bets, balance, and future features
 * such as giving away currency and challenging other players!
 */
public class Coinflip extends Command {

    private List<Player> playerList = new ArrayList<>(); // Everyone who is playing coinflip
    private Player player;
    private User user;
    private MessageReceivedEvent event;

    private final HashMap<String, String> usage = new HashMap<String, String>();

    public Coinflip() {
        addUsage();
    }

    /**
     * Add usage examples to commands to help the user friendly experience
     * Decided to put them in their own function to not clutter the constructor
     * Each usage gets dynamically cycled and put onto the commands help card.
     */
    private void addUsage() {
        usage.put("!coinflip", "Basic flip of a coin (No wager)");
        usage.put("!coinflip balance", "Check your bank account balance");
        usage.put("!coinflip bet <amount>", "Place an amount to bet.\n!coinflip bet 1000");
        usage.put("!coinflip reset", "Get out of jail free card. Use it while it exists ;)");
    }

    /**
     * TODO: Not sure why I have the game checking if the player exists
     *
     * Checks if player is already setup
     * if they exist set this player to their account
     *
     * @return boolean based on if player found
     */
    private boolean playerAlreadyExists() {
        long userId = event.getMessage().getAuthor().getIdLong();
        for(Player acc : playerList) {
            if(userId == acc.getId()) {
                this.player = acc;
                return true;
            }
        }
        return false;
    }

    /**
     * Our basic implementation of flipping a coin
     * using bounds 0-1 was giving Heads almost every time!
     *
     * @return String "Heads" or "Tails" based on psuedo rng
     */
    private String flip() {
        Random random = new Random(System.currentTimeMillis());
        int range = random.nextInt(0, 50);
        int choice = 0;
        if(range >= 25) {
            choice = 1;
        }
        String[] coin = {"Heads", "Tails"};
        return coin[choice];
    }

    private void setEventAndUser(MessageReceivedEvent event, User user) {
        this.event = event;
        this.user = user;
    }

    private void sendMsg(String toSend) {
        this.event.getMessage().getTextChannel().sendMessage(toSend).queue();
    }

    @Override
    public HashMap<String, String> getUsage() {
        return usage;
    }

    @Override
    public String getName() {
        return "coinflip";
    }

    @Override
    public String getDescription() {
        return "If the coin lands on tails, you win. Simple.";
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        String[] msgArr = event.getMessage().getContentDisplay().split(" ");

        // If they give only the input of !bet. Return only Heads or Tails
        if(msgArr.length <= 1) {
            sendMsg("The coin landed on: " + flip());
        }
        else
        {
            do {
                setEventAndUser(event, event.getAuthor());
                // TODO: let the Player class handle if they exist.
                if(!playerAlreadyExists()){
                    playerList.add(new Player(user));
                }
            } while (this.player == null);

            switch(msgArr[1])
            {
                case "reset" -> {
                    player.resetBalance();
                    sendMsg("Your balance has been reset");
                }
                case "balance" -> {
                    sendMsg("Your balance is: " + player.getBalance());
                }
                case "bet" -> {
                    if(msgArr.length <= 2) {
                        sendMsg("Please enter your bet: !coinflip bet 1000");
                    } else {
                        try {
                            if(!player.placeBet(Integer.parseInt(msgArr[2]))) {
                                sendMsg("Check your balance with: !coinflip balance");
                            }
                            else {
                                if(flip().equals("Heads")) {
                                    player.loser();
                                    sendMsg("Coin landed on: Heads!\nGimme dem monies");
                                }
                                else {
                                    player.winner();
                                    sendMsg("Coin landed on: Tails!\nWinner winner chicken dinner!");

                                }
                            }
                        }
                        // TODO: See what the real exception thrown is if they enter something goofy.
                        catch (NumberFormatException nfe) {
                            sendMsg("Please enter your bet: !coinflip bet 1000");
                        }
                    }
                }
                default -> sendMsg("I think you need to try !help coinflip");
            }
        }
    }
}


