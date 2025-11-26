pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/VKrishnaSai/Scientific_Calc.git'
            }
        }

        stage('Build') {
            steps {
                dir('main') {
                    bat 'mvn clean compile'
                }
            }
        }

        stage('Test') {
            steps {
                dir('main') {
                    bat 'mvn test'
                }
            }
        }

        stage('Package') {
            steps {
                dir('main') {
                    bat 'mvn package -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('main') {
                    bat 'docker build -t calculator .'
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASS%'
                    bat 'docker tag calculator %DOCKER_USER%/calculator:latest'
                    bat 'docker push %DOCKER_USER%/calculator:latest'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}