#!/bin/env sh

# check sudo permissions
if (( "$EUID" != 0 )); then
  echo "Please run this script with sudo"
  exit 1
fi


INSTALL_PATH=/usr/share/java/cmus-rpc
VERSION=2.0.1

mkdir -p "$INSTALL_PATH"

echo "Install version: $VERSION"
echo "Downloading jar file..."
sudo wget "https://github.com/anas-elagarhy/cmus-rpc/releases/download/$VERSION/cmus-rpc.jar"
sudo mv ./cmus-rpc.jar "$INSTALL_PATH"/cmus-rpc.jar

printf "\nDownloaded in %s/cmus-rpc.jar\n" $INSTALL_PATH

echo "Create the executable file in /bin"
# shellcheck disable=SC2024
sudo echo "exec /usr/bin/java -jar $INSTALL_PATH/cmus-rpc.jar \$@" >>  /bin/cmus-rpc

sudo chmod +x /bin/cmus-rpc

printf "\nDone, enjoy :D\n"

echo "Please type cmus-rpc --help to show the available options"
