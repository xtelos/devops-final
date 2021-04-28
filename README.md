# devops-final

# Launch Command
java -Xmx1024M -Xms1024M -jar spigot-1.16.5.jar nogui


# Docker Commannds 
 
docker build Dockerfiles/.

docker container create --publish 25565:25565/tcp --name "test" --env RAM=1G sirplexus/minecraft-server-standalone   

Start the containter

