package Commands.Games.Gambling;

import Commands.Command;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.*;

public class Coinflip extends Command {
    private final String name = "coinflip";
    private final String description = "If the coin lands on tails, you win. Simple.";
    // private final String[] t = {"!coinflip", "!coinflip balance", "!coinflip bet <amount>", "!coinflip reset"};

    private HashMap usage = new HashMap<String, String>();
    private void addUsage() {
        usage.put("!coinflip", "Basic flip of a coin (No wager)");
        usage.put("!coinflip balance", "Check your bank account balance");
        usage.put("!coinflip bet <amount>", "Place an amount to bet.\n!coinflip bet 1000");
        usage.put("!coinflip reset", "Get out of jail free card. Use it while it exists ;)");
    }

    @Override
    public HashMap<String, String> getUsage() {
        return usage;
    }

    public Coinflip() {
        addUsage();
    }


    private List<Player> playerList = new ArrayList<>();
    private Player player;

    private MessageReceivedEvent event;

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

    private String flip() {
        Random random = new Random();
        int range = random.nextInt(0, 50);
        int choice = 0;
        if(range >= 25) {
            choice = 1;
        }
        String[] coin = {"Heads", "Tails"};
        return coin[choice];
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }



    @Override
    public void execute(MessageReceivedEvent event) {
        this.event = event;
        User user = event.getAuthor();
        String[] msgArr = event.getMessage().getContentDisplay().split(" ");
        if(msgArr.length <= 1) {
            event.getMessage().getTextChannel().sendMessage("The coin landed on: " + flip()).queue();
        } else {
            if(!playerAlreadyExists()){
                playerList.add(new Player(user));
            } else {
                switch(msgArr[1])
                {
                    case "reset" -> {
                        player.resetBalance();
                        event.getMessage().getTextChannel()
                                .sendMessage("Your balance has been reset").queue();
                    }
                    case "balance" -> {
                        int bal = player.getBalance();
                        event.getMessage().getTextChannel()
                                .sendMessage("Your balance is: " + bal).queue();
                    }
                    case "bet" -> {
                        if(msgArr.length <= 2) {
                            event.getMessage().getTextChannel()
                                    .sendMessage("Please enter your bet: !coinflip bet 1000").queue();
                        } else {
                            try {
                                if(!player.placeBet(Integer.parseInt(msgArr[2]))){
                                    event.getMessage().getTextChannel().sendMessage("Check your !coinflip balance").queue();
                                } else {
                                    if(flip().equals("Heads")) {
                                        event.getMessage().getTextChannel()
                                                .sendMessage("Coin landed on: Heads!\nGimme dem monies").queue();
                                    } else {
                                        player.winner();
                                        event.getMessage().getTextChannel().sendMessage("Coin landed on: Tails!\nWinner winner chicken dinner!").queue();

                                    }
                                }

                            } catch (NumberFormatException nfe) {
                                event.getMessage().getTextChannel()
                                        .sendMessage("Please enter your bet: !coinflip bet 1000").queue();
                            }
                        }
                    }
                }
            }
        }
    }
}
