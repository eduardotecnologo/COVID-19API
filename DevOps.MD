

#!/bin/bash
sudo yum update -y
sudo amazon-linux-extras install epel -y
sudo yum install daemonize -y
sudo wget -O /etc/yum.repos.d/jenkins.repo 
    https://pkg.jenkins.io/redhat-stable/jenkins.repo
sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key
sudo yum upgrade
sudo amazon-linux-extras install java-openjdk11 -y
sudo yum install jenkins -y
sudo systemctl daemon-reload
sudo systemctl start jenkins
sudo cat /var/lib/jenkins/secrets/initialAdminPassword

#!/bin/bash
sudo apt-get update
sudo apt-get upgrade -y
sudo apt-get install docker.io git -y
sudo usermod -aG docker ubuntu
sudo reboot

---

docker --version

/* Comandos Docker */

docker images          # Visualizar as imagens docker disponiveis.
docker ps                 # Visualizar os containers rodando atualmente.
docker run                # Rodar um container, necessário passar os parametros de uso.
docker build             # Faz o build de uma imagem docker baseado no Dockerfile que deve ser informado via parametros.
docker rm                 # Apaga um container que não está mais sendo utilizado, pode-se utiliza-lo junto ao -f para forçar a parada de um container em execução.
docker rmi                # Apaga uma imagem armazenada localmente.

/* Comando utilizados durante a live */

docker build -t covid-19api .     # Irá criar sua propria imagem baseado em instruções no dockerfile.
docker run -itd -p 80:3000 covid-19api   # Irá rodar o container com a imagem previamente criada e expondo a aplicação na porta 80
docker ps                                             # Verificar se o container está rodando.



# Rodar na instância Jenkins

sudo yum update -y   # Atualiza os softwares disponiveis para o sistema
sudo yum install git -y  # Instala o git
sudo amazon-linux-extras install docker -y   # Instala o docker
sudo systemctl start docker   # Inicia o docker
sudo usermod -aG docker jenkins   # Da permissões ao Jenkins para rodar comandos Docker
sudo systemctl restart jenkins   # Reinicia o Jenkins para aplicar as permissões anteriormente adicionadas

# Rodar na instância app-server

docker rm -f $(docker ps -qa)  # Finaliza todos os containers em execução
docker run -itd -p 80:3000 REPOSITORY_NAME/dcw-app:TAG  # Inicializa o novo container com a versão especificada

# Instala pré-requisitos para serem usados posteriormente

sudo apt install ruby-full -y   # Instala o Ruby
wget https://aws-codedeploy-us-east-1.s3.us-east-1.amazonaws.com/latest/install   # Faz o download do agente do codedeploy
chmod +x ./install                    # Da permissão de execução para o arquivo
sudo ./install auto > /tmp/logfile    # executa a instalação do agente
sudo service codedeploy-agent status  # Verifica se o agente foi iniciado corretamente
sudo reboot                           # Reinicia a maquina para garantir que o agente entre em execução
