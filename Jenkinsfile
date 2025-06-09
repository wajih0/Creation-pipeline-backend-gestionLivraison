pipeline {
    agent any

    tools {
         maven 'Maven_3.9.9'
         jdk 'JDK 17'
     }

    environment {
        IMAGE_NAME = 'demoproduit'
        IMAGE_TAG = 'latest'
        DOCKER_REGISTRY = '' // exemple: 'dockerhub' ou vide si pas de push
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                }
            }
        }

        stage('Optional: Push Docker Image') {
            when {
                expression { return env.DOCKER_REGISTRY != '' }
            }
            steps {
                script {
                    docker.withRegistry('', 'docker-hub-credentials-id') {
                        sh "docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}"
                        sh "docker push ${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}"
                    }
                }
            }
        }
    }

    post {
        success {
            echo '✅ Build terminé avec succès'
        }
        failure {
            echo '❌ Le build a échoué'
        }
    }
}
