# Tiny Storage
Minecraft Mod:- Adds tiny storage options to the game!

## Last Build Status:
Master: 
[![Build Status](https://travis-ci.org/SmithsModding/Tiny-Storage.svg?branch=master)](https://travis-ci.org/Tim020/Tiny-Storage)

Dev: [![Build Status](https://travis-ci.org/SmithsModding/Tiny-Storage.svg?branch=Dev)](https://travis-ci.org/Tim020/Tiny-Storage)

Dev-1.8: [![Build Status](https://travis-ci.org/SmithsModding/Tiny-Storage.svg?branch=Dev-1.8)](https://travis-ci.org/Tim020/Tiny-Storage)

CurseForge link: 
 http://minecraft.curseforge.com/mc-mods/227712-tiny-storage

Mods.IO link: 
 https://mods.io/mods/1138-tiny-storage

Nightly builds (may not be stable!): 
 http://mavenrepo.orionminecraft.com/com/timthebrick/tinystorage/
 
 Installation
============
## Users:
  1. Download all the Dependencies and install those.
  2. Drop Tiny Storage in the mods folder.
  
## Modders (JetBrains IDEA):
  1. Fork this Repository
  2. Download the fork
  3. Run on a commandline: 
      1. gradlew setupDecompWorkspace
      2. gradlew copyChicken
      3. gradlew idea
  4. Inside IDEA:
      1. set ~/resources as 'Resources Root'
      2. set ~/src/api as 'Sources Root'
      3. Open the module settings and go to Module Dependencies
      4. Remove the extract options from all of ChickenBones mod: (CCC, CCL, NEI)
      5. Set the Scope off al of ChickenBones mods to provided.

