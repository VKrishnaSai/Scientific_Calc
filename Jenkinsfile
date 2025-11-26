pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Assuming 'main' is the directory where pom.xml and source code reside
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
                    // Running tests without skipping
                    bat 'mvn test'
                }
            }
        }

        stage('Package') {
            steps {
                dir('main') {
                    // Packaging the application (JAR)
                    bat 'mvn package'
                }
            }
        }

        // --- NEW/UPDATED DOCKER STAGES ---

        // CRITICAL FIX: Login before building to authenticate base image pull
        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    // Log in to Docker Hub using stored credentials ('dockerhub' ID required)
                    bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASS%'
                }
            }
        }
        
        stage('Build Docker Image') {
            steps {
                // Build the image using Dockerfile in root and context in main/
                bat 'docker build -f Dockerfile -t calculator main'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                // Re-using credentials for tagging and pushing
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    // Tag with user's Docker Hub username
                    bat 'docker tag calculator %DOCKER_USER%/calculator:latest'
                    // Push the final image
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
            echo 'Pipeline failed! Check the logs for the specific stage failure.'
        }
    }
}