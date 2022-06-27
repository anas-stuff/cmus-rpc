# A Discord Rich Presence for cmus player

[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_cmus-rpc&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_cmus-rpc)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_cmus-rpc&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_cmus-rpc)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_cmus-rpc&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_cmus-rpc)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_cmus-rpc&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_cmus-rpc)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_cmus-rpc&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_cmus-rpc)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_cmus-rpc&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_cmus-rpc)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_cmus-rpc&metric=bugs)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_cmus-rpc)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_cmus-rpc&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_cmus-rpc)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_cmus-rpc&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_cmus-rpc)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=Anas-Elgarhy_cmus-rpc&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_cmus-rpc)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3e0d24aa2c1441e484622b8540193cdf)](https://app.codacy.com/gh/Anas-Elgarhy/cmus-rpc?utm_source=github.com&utm_medium=referral&utm_content=Anas-Elgarhy/cmus-rpc&utm_campaign=Badge_Grade_Settings)
[![CodeFactor](https://www.codefactor.io/repository/github/anas-elgarhy/cmus-rpc/badge)](https://www.codefactor.io/repository/github/anas-elgarhy/cmus-rpc)

<img alt="image 1" src="./Screenshots/1.png">
<img alt="image 2" src="./Screenshots/2.gif">
<img alt="image 3" src="./Screenshots/3.png">
<img alt="image 4" src="./Screenshots/4.png">

- Require jdk-17 or higher
- Require cmus
## Install

### Linux
- From aur: `yay -S cmus-rpc`
- Manual:
- Make sure you installed `wget`
- Run this command
   ```bash
    curl -s https://raw.githubusercontent.com/Anas-Elgarhy/cmus-rpc/master/scripts/install.sh | sudo bash
   ```

## Uninstall

### Linux
- Manual:
- Run this command
  ```bash
    curl -s https://raw.githubusercontent.com/Anas-Elgarhy/cmus-rpc/master/scripts/uninstall.sh | sudo bash
  ```


- Requirements for development:
    - jdk-17 or higher
    - Maven
    - IntelliJ IDEA (not required but recommended)

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
cmus-rpc -p1f %title%
```

```bash
cmus-rpc -p1f "%artist% - %title%" -p2f "%album% - %date%"
```

```bash
cmus-rpc -p1f "Anas listening to %title%" -p2f "From %artist%"
```

### How to do auto run when you start the cmus
- Put the following line in your shellrc file e.g. `.bashrc` or `.zshrc`
```bash
    alias cmus = 'cmus-rpc --link &>/dev/null & cmus'
```

### Available in
[![GitHub](https://img.shields.io/badge/GitHub-Main%20repo-brightgreen?style=for-the-badge&logo=GitHub)](https://github.com/Anas-Elgarhy/cmus-rpc)
[![GitLab](https://img.shields.io/badge/GitLab-Mirror%20repo-brightgreen?style=for-the-badge&logo=GitLab)](https://gitlab.com/Anas-Elgarhy/cmus-rpc)
[![BitBucket](https://img.shields.io/badge/BitBucket-Mirror%20repo-brightgreen?style=for-the-badge&logo=BitBucket)](https://bitbucket.org/anas_elgarhy/cmus-rpc)
[![Codeberg](https://img.shields.io/badge/Codeberg-Mirror%20repo-brightgreen?style=for-the-badge&logo=Codeberg)](https://codeberg.org/anas-elgarhy/cmus-rpc)


### Recourses
- [`cmus-remote` tool](https://github.com/cmus/cmus) to the make a query to the cmus
- [Discord-RPC library](https://github.com/Vatuu/discord-rpc) to send the information to Discord
- [The Discord API](https://discordapp.com/developers/docs/intro) to learn more about RPC
- [commons-clib2 library](https://github.com/apache/commons-cli) to parse the command line arguments
- [jackson library](https:github.com/FasterXML/jackson-databind) to save the config.json and load it
- [cmus-rich-presence](https://github.com/pascalpuffke/cmus-rich-presence) for inspiration

[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=Anas-Elgarhy_cmus-rpc)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_cmus-rpc)

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/summary/new_code?id=Anas-Elgarhy_cmus-rpc)

[![License MIT](https://img.shields.io/badge/license-MIT-green.svg)](https://spdx.org/licenses/MIT.html)