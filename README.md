# JCMUSIntegrationWithDiscord - A Discord integration for CMUS
I know the name is terrible but that's where I'm at right now :P

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/dd42f7e96aa24494a734e2b7ae06a955)](https://www.codacy.com?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Anas-Elgarhy/JCmusIntegrationWithDiscord&amp;utm_campaign=Badge_Grade)
[![wakatime](https://wakatime.com/badge/user/0671d7a1-0f1f-4dae-9501-2d7aa4f6fc20/project/5bd30ca7-e14d-4d4b-8fb6-7e1c0ab9250c.svg)](https://wakatime.com/badge/user/0671d7a1-0f1f-4dae-9501-2d7aa4f6fc20/project/5bd30ca7-e14d-4d4b-8fb6-7e1c0ab9250c)

<img alt="image 1" src="./Screenshots/1.png">
<img alt="image 2" src="./Screenshots/2.gif">
<img alt="image 3" src="./Screenshots/3.png">
<img alt="image 4" src="./Screenshots/4.png">
<img alt="image 5" src="./Screenshots/5.png">

### How to customize 
- You can edit the config.json file to change the settings [click here to more details](./Config.md)
- You can use the arguments to change the settings [click here to more details](./Arguments.md)

### How to do auto run when you start the cmus
- Put the following in your shellrc file e.g. `.bashrc` or `.zshrc`
```bash
    alias JCmusIntegrationWithDiscord = '/usr/lib/jvm/jdk-18/bin/java -jar path/to/jar/JCmusIntegrationWithDiscord.jar'
    alias cmus = 'JCmusIntegrationWithDiscord &>/dev/null & cmus'
```

### I use
- [`cmus-remote` tool](https://github.com/cmus/cmus) to the make a query to the cmus
- [Discord-RPC library](https://github.com/Vatuu/discord-rpc) to send the information to Discord
- [The Discord API](https://discordapp.com/developers/docs/intro) to learn more about RPC
- [commons-clib2 library](https://github.com/apache/commons-cli) to parse the command line arguments
- [jackson library](https:github.com/FasterXML/jackson-databind) to save the config.json and load it
- [cmus-rich-presence](https://github.com/pascalpuffke/cmus-rich-presence) for inspiration

#### License: MIT