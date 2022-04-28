
pipeline {
    agent any

    environment {
		registry = "eduardodeveloper/covid-19api"
        registryCredential = "dockerhub_id" 
        dockerImage = ''
    }

    stages {
    	stage('Clone Repository') {
    		steps {  
                git branch: "master", url: 'https://github.com/eduardotecnologo/COVID-19API.git'   
            }
    	}
    	stage('Build Docker Image') {
            steps{
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }
    	stage('Send image to Docker Hub') {
            steps{
                script {
                    docker.withRegistry( '', registryCredential) {
                        dockerImage.push()
                    }
                }
            }
        }
    	stage('Cleaning up') {
        	steps {
            	sh "docker rmi $registry:$BUILD_NUMBER"
        	}
		}
    }
}
