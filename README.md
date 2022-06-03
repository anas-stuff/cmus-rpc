# JCMUSIntegrationWithDiscord - A Discord integration for CMUS
I know the name is terrible but that's where I'm at right now :P

[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_JCmusIntegrationWithDiscord&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_JCmusIntegrationWithDiscord)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_JCmusIntegrationWithDiscord&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_JCmusIntegrationWithDiscord)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_JCmusIntegrationWithDiscord&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_JCmusIntegrationWithDiscord)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_JCmusIntegrationWithDiscord&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_JCmusIntegrationWithDiscord)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_JCmusIntegrationWithDiscord&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_JCmusIntegrationWithDiscord)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_JCmusIntegrationWithDiscord&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_JCmusIntegrationWithDiscord)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_JCmusIntegrationWithDiscord&metric=bugs)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_JCmusIntegrationWithDiscord)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_JCmusIntegrationWithDiscord&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_JCmusIntegrationWithDiscord)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_JCmusIntegrationWithDiscord&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_JCmusIntegrationWithDiscord)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_JCmusIntegrationWithDiscord&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_JCmusIntegrationWithDiscord)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3e0d24aa2c1441e484622b8540193cdf)](https://app.codacy.com/gh/Anas-Elgarhy/JCmusIntegrationWithDiscord?utm_source=github.com&utm_medium=referral&utm_content=Anas-Elgarhy/JCmusIntegrationWithDiscord&utm_campaign=Badge_Grade_Settings)
[![CodeFactor](https://www.codefactor.io/repository/github/anas-elgarhy/jcmusintegrationwithdiscord/badge)](https://www.codefactor.io/repository/github/anas-elgarhy/jcmusintegrationwithdiscord)
[![wakatime](https://wakatime.com/badge/user/0671d7a1-0f1f-4dae-9501-2d7aa4f6fc20/project/5bd30ca7-e14d-4d4b-8fb6-7e1c0ab9250c.svg)](https://wakatime.com/badge/user/0671d7a1-0f1f-4dae-9501-2d7aa4f6fc20/project/5bd30ca7-e14d-4d4b-8fb6-7e1c0ab9250c)

<img alt="image 1" src="./Screenshots/1.png">
<img alt="image 2" src="./Screenshots/2.gif">
<img alt="image 3" src="./Screenshots/3.png">
<img alt="image 4" src="./Screenshots/4.png">
<img alt="image 5" src="./Screenshots/5.png">

### Syntax:
`java -jar JCmusIntegrationWithDiscord.jar [Option] [value] [Option] [value] ...`

### Options:
| Option                      | Description                                                  | Values                    |
|-----------------------------|--------------------------------------------------------------|---------------------------|
| `-h` or `--help`            | Show the help                                                | -                         |
| `-v` or `--version`         | Show the version                                             | -                         |
| `-d` or `--debug`           | Debug mode                                                   | -                         |
| `-l` or `--link`            | Linking with cmus (close the program if cmus is not running) | -                         |
| `-c` or `--config`          | Set custom path to config file                               | Path to config file .json |
| `-i` or `--interval`        | Set interval between checks                                  | interval time (seconds)   |
| `-s` or `--sleep`           | Set sleep when there is no activity                          | sleep time (seconds)      |
| `-p1f` or `--partOneFormat` | Set the format for the first part                            | Format for first part     |
| `-p2f` or `--partTowFormat` | Set the format for the second part                           | Format for second part    |


### Examples:
```bash
java -jar JCmusIntegrationWithDiscord.jar -p1f %title%
```

```bash
java -jar JCmusIntegrationWithDiscord.jar -p1f "%artist% - %title%" -p2f "%album% - %date%"
```

```bash
java -jar JCmusIntegrationWithDiscord.jar -p1f "Anas listening to %title%" -p2f "From %artist%"
```

### How to do auto run when you start the cmus
- Put the following in your shellrc file e.g. `.bashrc` or `.zshrc`
```bash
    alias JCmusIntegrationWithDiscord = '/usr/lib/jvm/jdk-18/bin/java -jar path/to/jar/JCmusIntegrationWithDiscord.jar'
    alias cmus = 'JCmusIntegrationWithDiscord --link &>/dev/null & cmus'
```

### Available in
[![GitHub](https://img.shields.io/badge/GitHub-Main%20repo-brightgreen?style=for-the-badge&logo=GitHub)](https://github.com/Anas-Elgarhy/JCmusIntegrationWithDiscord)
[![GitLab](https://img.shields.io/badge/GitLab-Mirror%20repo-brightgreen?style=for-the-badge&logo=GitLab)](https://gitlab.com/Anas-Elgarhy/JCmusIntegrationWithDiscord)
[![BitBucket](https://img.shields.io/badge/BitBucket-Mirror%20repo-brightgreen?style=for-the-badge&logo=BitBucket)](https://bitbucket.org/anas_elgarhy/JCmusIntegrationWithDiscord)
[![Codeberg](https://img.shields.io/badge/Codeberg-Mirror%20repo-brightgreen?style=for-the-badge&logo=Codeberg)](https://codeberg.org/anas-elgarhy/JCmusIntegrationWithDiscord)


### I use
- [`cmus-remote` tool](https://github.com/cmus/cmus) to the make a query to the cmus
- [Discord-RPC library](https://github.com/Vatuu/discord-rpc) to send the information to Discord
- [The Discord API](https://discordapp.com/developers/docs/intro) to learn more about RPC
- [commons-clib2 library](https://github.com/apache/commons-cli) to parse the command line arguments
- [jackson library](https:github.com/FasterXML/jackson-databind) to save the config.json and load it
- [cmus-rich-presence](https://github.com/pascalpuffke/cmus-rich-presence) for inspiration

[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=Anas-Elgarhy_JCmusIntegrationWithDiscord)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_JCmusIntegrationWithDiscord)

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_JCmusIntegrationWithDiscord)

[![License MIT](https://img.shields.io/badge/license-MIT-green.svg)](https://spdx.org/licenses/MIT.html)