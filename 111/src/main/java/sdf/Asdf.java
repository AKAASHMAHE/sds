package sdf;

public class Asdf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Akaash");
	}

}


























SonarCube Installation in Ubuntu 
Step 1: 
First install PostgreSQL if it’s not already installed on your Ubuntu system: 
sudo apt install -y postgresql-common postgresql 
Step 2: 
Enable PostgreSQL so it starts automatically whenever your system boots: 
sudo systemctl enable postgresql 
Step 3: 
Start the PostgreSQL service now: 
sudo systemctl start postgresql 
Step 4: 
Log in to PostgreSQL as the default postgres admin user: 
sudo -u postgres psql 
Step 5: 
Create a new user called sonaruser with a password (replace your_password with your own 
password): 
CREATE ROLE sonaruser WITH LOGIN ENCRYPTED PASSWORD 'your_password'; 
Step 6: 
Create a database for SonarQube: 
CREATE DATABASE sonarqube; 
Step 7: 
Give full access of that database to the sonaruser: 
GRANT ALL PRIVILEGES ON DATABASE sonarqube TO sonaruser; 
Step 8: 
Switch into the sonarqube database: 
\c sonarqube
Then give schema permissions: 
GRANT ALL PRIVILEGES ON SCHEMA public TO sonaruser; 
Step 9: 
Exit PostgreSQL 
\q 
Install SonarQube  
Step 1: 
First update your system package list: 
sudo apt update 
Step 2: 
Install Java (OpenJDK 17) because SonarQube needs Java to run: 
sudo apt install openjdk-17-jdk -y 
Step 3: 
Install unzip so you can extract the downloaded SonarQube file: 
sudo apt install unzip 
Step 4: 
Check if Java installed correctly: 
java -version 
Step 5: 
Extract the downloaded zip file: 
unzip sonarqube-25.2.0.102705.zip 
Step 6: 
Move the extracted folder to /opt directory: 
sudo mv sonarqube-25.2.0.102705/ /opt/sonarqube 
Step 7: 
Create a separate system user for SonarQube: 
sudo adduser --system --no-create-home --group --disabled-login sonarqube 
Step 8: 
Give that user full permission to access the SonarQube folder: 
sudo chown -R sonarqube:sonarqube /opt/sonarqube 


Configure SonarQube 
SonarQube needs some system and database settings before running properly. Follow these 
steps: 
 
Step 1: 
Open the main SonarQube config file: 
sudo nano /opt/sonarqube/conf/sonar.properties 
At the bottom of this file, add your database details: 
sonar.jdbc.username=sonaruser 
sonar.jdbc.password=your_password 
sonar.jdbc.url=jdbc:postgresql://localhost:5432/sonarqube 
Save and exit. 
 
Step 2: 
Open system configuration file: 
sudo nano /etc/sysctl.conf 
 
Step 3: 
Add these lines at the bottom: 
vm.max_map_count=524288 
fs.file-max=131072 
Save and exit. 
 
Step 4: 
Set user limits for SonarQube: 
sudo nano /etc/security/limits.d/99-sonarqube.conf 
Add: 
sonarqube - nofile 131072 
sonarqube - nproc 8192 
Save and exit.

	Step 5: 
Allow SonarQube port (9000) through firewall: 
sudo ufw allow 9000/tcp 
If UFW firewall is not installed, install and allow SSH: 
sudo apt install ufw -y && sudo ufw allow 22/tcp 
 
Step 6: 
Reload firewall rules: 
sudo ufw reload 
 
Step 7: 
Check firewall status to confirm rules: 
sudo ufw status 
You should see only allowed ports like 22 (SSH) and 9000 (SonarQube). 
 
Setup SonarQube as a System Service 
This lets SonarQube run like a background service and start automatically when your server 
boots. 
 
Step 1: 
Create a new service file for SonarQube: 
sudo nano /etc/systemd/system/sonarqube.service 
 
Step 2 : 
 
Paste this configuration inside the file: 
[Unit] 
Description=SonarQube service 
After=syslog.target network.target 
 
[Service] 
Type=forking
ExecStart=/opt/sonarqube/bin/linux-x86-64/sonar.sh start 
ExecStop=/opt/sonarqube/bin/linux-x86-64/sonar.sh stop 
User=sonarqube 
Group=sonarqube 
PermissionsStartOnly=true 
Restart=always 
StandardOutput=syslog 
LimitNOFILE=131072 
LimitNPROC=8192 
TimeoutStartSec=5 
SuccessExitStatus=143 
 
[Install] 
WantedBy=multi-user.target 
Save and exit. 
Then reload systemd so it detects the new service: 
sudo systemctl daemon-reload 
 
Step 3: 
Enable SonarQube service so it starts automatically on boot: 
sudo systemctl enable sonarqube 
 
Step 4: 
Start SonarQube service now: 
sudo systemctl start sonarqube 
 
Step 5: 
Check if SonarQube is running: 
sudo systemctl status sonarqube 
sudo reboot now



















---
	STEPS INVOLVED:
Step 1 : Open docker.com
Step 2 : Download the latest version.
Step 3 : To run docker commands in windows, open powershell and run the docker commands.
(1)To see the list of docker images in our machine
docker images
(2)To download an image from docker hub 
docker pull image-name
(3)To upload an image into dockerhub
docker push image-name 
(4)To search for an images on docker hub
docker search image-name
(5)To delete an image on docker host 
docker mi image-name
(6)To create an image from a container
docker commit container-name/container-id image-name
7. To create an image from a dockerfile
docker build -t imagename
8.To see the list of running containers 
docker container ls
9.To see the list of stopped and running containers
docker ps -a
10. To start a container
docker start container-name/container-id
11. To stop a container
docker stop container-name/container-id
12.To restart a container 
docker restart container-name/container-id
13.To remove a stopped container
docker rm container-name/container-id 
14. To remove a running container
docker rm -f container-name/container-id 
15. To stop all the running containers
docker stop $(docker ps -aq)
16. To see the logs generated by the container
docker log container-name/container-id
17. To see the ports of a container
docker port container-name/container-id
18. To find detailed info about a container
docker inspect container-name/container-id
19.creating a docker container 
20.To inspect a network
docker run image-name
docker network inspect network-id/network-name

	---
cd C:
Changes the current directory to the C drive.
mkdir directoryname
Creates a new folder/directory with the given name.
cat > filename
Creates a file and allows you to enter data into it (Ctrl + Z to save in Windows).
touch filename
Creates an empty file with the given name.
ls
Displays the list of files and folders in the current directory.
ls -lrt
Lists files in long format, sorted by time (oldest first).
git add filename
Moves the file from the working directory to the staging area.
git add filename1 filename2 filename3
Adds multiple files to the staging area.
git status
Shows the status of files (tracked, untracked, staged, modified).
git reset filename
Removes the file from the staging area but keeps it in the working directory.
git commit -m "comments"
Saves staged changes into the local repository with a message.
git log
Displays the full commit history.
git log --oneline
Shows commit history in a single-line short format.
git branch
Lists all local branches.
git branch -a
Lists all branches (local and remote).
git branch branch-name
Creates a new branch.
git checkout branch-name
Switches to the specified branch.
git checkout master
Switches to the master/main branch.
git merge branch-name
Merges the given branch into the current branch.
git checkout -b branch-name
Creates a new branch and switches to it immediately.
git branch -d branch-name
Deletes a branch safely (only if merged).
git branch -D branch-name
Force deletes a branch even if not merged.
