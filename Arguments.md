## Syntax:
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
| `-p1f` or `--partTowFormat` | Set the format for the second part                           | Format for second part    |


### Examples:
```bash
java -jar JCmusIntegrationWithDiscord.jar -h
```
```bash
java -jar JCmusIntegrationWithDiscord.jar --help
```

```bash
java -jar JCmusIntegrationWithDiscord.jar -p1f %title%
```

```bash
java -jar JCmusIntegrationWithDiscord.jar -p1f "%artist% - %title%" -p2f "%album% - %date%"
```

```bash
java -jar JCmusIntegrationWithDiscord.jar -p1f "Anas listening to %title%" -p2f "From %artist%"
```
