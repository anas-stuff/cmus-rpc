#!/bin/env sh

if (( "$EUID" != 0 )); then
  echo "Please this script with sudo!"
  exit 1
fi

echo "Remove jar file.."
sudo rm -r /usr/share/java/cmus-rpc

echo "Remove execrable file..."
sudo rm /bin/cmus-rpc

echo "Done :/"

