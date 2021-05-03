# devops-final

# Server IP for Direct Connection
54.198.138.26:25565

# General Launch Command for Spigot Based Server with Plugins
java -Xmx1024M -Xms1024M -jar spigot-1.16.5.jar nogui

NOTE:
The amount of RAM used by the server can be changed via the -Xmx and -Xms switches. The current command is set to launch a server with 1gb of RAM allocated, but more can be allocated simply by changing the number in the switch (which is in megabytes).

Another jar file for the server can also be used and then specified for launch via the -jar switch. The command is current set to launch the server via the Spigot jar file, so that the plugins will be loaded properly. 

# Vagrant Command to Launch Server Locally
vagrant up
NOTE: Once you are ready to shutdown the server locally, simply type CONTROL + C to stop the server.
vagrant down

# Project Overview
This project involved the creation of a minecraft server through various methods. The server launched is spigot based, so the user is able to download plugins off of internet databases to run at their own will. The project is able to be run directly (assuming an adequate version of java is installed) through the general launch command shown above, through vagrant by typing vagrant up, or simply joining our server hosted through AWS at 54.198.138.26:25565. 

# Technologies Used
Minecraft: https://www.minecraft.net/en-us/
Spigot Build Tools: https://hub.spigotmc.org/jenkins/job/BuildTools/
Vagrant: https://www.vagrantup.com/
Docker: https://www.docker.com/
Amazon Web Services: https://aws.amazon.com/
Github: https://github.com/


# Setup Process
If you wish to simply run a completely vanilla Minecraft server with no plugins, simply install docker via the link above, then via command line used the commands below to start the server:

docker container create --publish 25565:25565/tcp --name "test" --env RAM=1G sirplexus/minecraft-server-standalone   

docker container start test

This is where we initally started to get our server running locally. We then created a directory in which we ran the Spigot Build Tools to get a base spigot server setup. Following this we virtualized the server via vagrant so to launch the server, you only need to use the vagrant up command to get it running locally. After that, we created an Amazon Lightsail instance running a base ubuntu 18.04 image, installed java on that, and ran the general launch command above (which allowed for direct connection outside of the local realm). All file coordination was done using Github to keep a file repo consistent between all instances of the server. 

There were two main tutorials we followed:
Minecraft Wiki: https://minecraft.fandom.com/wiki/Tutorials/Setting_up_a_server#Docker
Amazon Web Services: https://aws.amazon.com/getting-started/hands-on/run-your-own-minecraft-server/
