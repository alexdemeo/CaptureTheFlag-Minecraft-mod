#!/bin/bash

rm /Users/alexdemeo/Library/Application\ Support/minecraft/mods/ctf*.jar
rm /Users/alexdemeo/Documents/Other/minecraft-other/test/mods/ctf*.jar
rm ~/Desktop/ctf*.jar

cd /Users/alexdemeo/Documents/Other/minecraft-other/CTF
./gradlew build
cp /Users/alexdemeo/Documents/Other/minecraft-other/CTF/build/libs/* /Users/alexdemeo/Library/Application\ Support/minecraft/mods/
cp /Users/alexdemeo/Documents/Other/minecraft-other/CTF/build/libs/* /Users/alexdemeo/Documents/Other/minecraft-other/test/mods/

mv /Users/alexdemeo/Documents/Other/minecraft-other/CTF/build/libs/* ~/Desktop/
