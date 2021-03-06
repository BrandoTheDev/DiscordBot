# JARVIS (Discord Bot)

Java Automatically Responds Verbally In Server.
`` (I just made this up before uploading it.)
``

  [![Heroku](https://heroku-badge.herokuapp.com/?app=heroku-badge)]()

## Tools & Frameworks

```
Java    - Our programming language of choice.
Maven   - Build automation tool.
JDA*    - Java Discord API (Discord API Wrapper)
Jsoup   - HTML Parser, used for webscraping sites.
```

## Commands

| Category   | Command              | Arguments          | Result                                                  | Example Usage        |
|------------|----------------------|--------------------|---------------------------------------------------------|----------------------|
| API        | !joke                |                    | Have the bot tell you a knee-slappin dad joke!          | !joke                |
| API        | !whats               | Search term        | Get the dictionary definition for a term                | !whats butane        |
| API        | !weather             | Zipcode            | Get a current weather forecast!                         | !weather 96793       |
|            |                      |                    |                                                         |                      |
| Games      | !coinflip            |                    | Flip a coin and see if its Heads or Tails!              | !coinflip            |
| Games      | !coinflip bet        | Amount to wager    | Place a wager on it landing Tails.                      | !coinflip bet 1000   |
| Games      | !coinflip balance    |                    | Check your bank balance                                 | !coinflip balance    |
| Games      | !coinflip reset      |                    | Restart your balance if you go broke.                   | !coinflip reset      |
|            |                      |                    |                                                         |                      |
| Info       | !help                |                    | A help card displaying all commands                     | !help                |
| Info       | !help                | Command name       | Usage details on how a command works.                   | !help ping           |
|            |                      |                    |                                                         |                      |
| Misc       | !ping                |                    | Have the bot respond with a Pong!                       | !ping                |
| Misc       | !whois               |                    | Displays info about user in a card.                     | !whois @John         |
|            |                      |                    |                                                         |                      |
| Moderation | !timeout             | @Mentioned User(s) | Disables the user(s) from participating in any channel. | !timeout @Tom @John  |
| Moderation | !mute or !unmute     | @Mentioned User(s) | Disables/Enables user(s) to speak in any channel.       | !mute @Tom @John     |
| Moderation | !deafen or !undeafen | @Mentioned User(s) | Disables/Enables user(s) to hear in any channel.        | !undeafen @Tom @John |


## How to use
```
Please don't. No, seriously! 

This is a Minimal Viable Product (MVP)

There are a lot of things I still need to implement, refactor and catch.. 
looking at you, exceptions.

I have made this bot specifically for my server and some things may be hard coded!

However.. If you see something you like that I wrote,
then please grab it and use it for an example in your bot! 

Cheers,
Brando!
```

## License
[MIT](https://choosealicense.com/licenses/mit/)
