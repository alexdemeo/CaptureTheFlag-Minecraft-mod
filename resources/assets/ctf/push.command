#!/bin/bash

cd /Users/alexdemeo/Documents/Other/minecraft-other/CTF/src/main/
read message
git add *
git commit -m "$message"
git push -f origin master