# -*- mode: ruby -*-
# Don't change it unless you know what you're doing.
Vagrant.configure("2") do |config|


  # Determines what box
  config.vm.box = "bento/ubuntu-18.04"
  config.vm.network "forwarded_port", guest: 25565, host: 25565

  # Share an additional folder to the guest VM. The first argument is
  # the path on the host to the actual folder. The second argument is
  # the path on the guest to mount the folder.
  config.vm.synced_folder ".", "/host_spigot_data"

  # Enable provisioning with a shell script.
  # maybe try privileged = false
  config.vm.provision "shell", inline: <<-SHELL

    sudo apt-get update
    sudo apt-get -y install default-jre

    cd /host_spigot_data
    java -Xmx1024M -Xms1024M -jar spigot-1.16.5.jar nogui

  SHELL
end
