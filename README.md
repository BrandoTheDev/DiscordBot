# JARVIS (Discord Bot)
``
A utility bot used to manage channels, users, games and more!
`` 
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

## Tools & Frameworks

```
Java    - Our programming language of choice.
Maven   - Build automation tool.
JDA*    - Java Discord API (Discord API Wrapper)
Jsoup   - HTML Parser, used for webscraping sites.
```

## The why & who it's for (TLDR Below):
```
I've always been fascinated with chat bots. I got my first experience on Internet Relay Chat
and built my first "user client bot" within the mIRC client using mIRC Scripting Language (MSL).
Watching how fast the bot responded and that no one was behind the screen manually pushing buttons 
expanded my mind and got me hooked on scripts and automation. I have created a vast amounts of bots 
in python but nothing with OOP and very messy code. JARVIS is my first attempt at a clean chat bot.

This bot is for someone who is also fascinated by chat bots and would like to see one at a simple level,
yet uses programming concepts to the best of their ability. Im going to try to keep it simple and not overwhelming
as that is a big factor that kept me away from programming. If you want a professional bot with lots of features,
this is your turn away warning. Once I polish it and ask for tips I am going to archive it as a simple clean bot.

TLDR: I love bots. I'm going to keep this one simple and archive it in the future. -Thanks for looking.
```

## License
[MIT](https://choosealicense.com/licenses/mit/)


## DISCLAIMER
This software is provided "as is" and without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, fitness for a particular purpose, and non-infringement. In no event shall the author or contributors be liable for any direct, indirect, incidental, special, exemplary, or consequential damages (including, but not limited to, procurement of substitute goods or services; loss of use, data, or profits; or business interruption) however caused and on any theory of liability, whether in contract, strict liability, or tort (including negligence or otherwise) arising in any way out of the use of this software, even if advised of the possibility of such damage.
