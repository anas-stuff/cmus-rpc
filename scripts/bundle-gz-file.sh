#!/bin/env sh
if [ -f ".temp" ]; then
  rm -r ./.temp/
fi

mkdir ./.temp/
cp ../out/artifacts/cmus_rpc_jar/cmus-rpc.jar ./.temp/
cp ./cmus-rpc.sh ./.temp/


cd ./.temp

ls

VER="$1"
echo "Create tar file to cmus-rpc $VER ..."
TARNAME=cmus-rpc-"$VER".tar.gz
tar -cvf "./$TARNAME" ./cmus-rpc.sh ./cmus-rpc.jar

cd ../..
if ! [ -f "./release" ]; then
  echo "Create release file"
  mkdir release 
fi

echo "Move the $TARNAME to release directory."

mv "./scripts/.temp/$TARNAME" ./release

echo "Remove temp folder"
rm -r ./scripts/.temp
