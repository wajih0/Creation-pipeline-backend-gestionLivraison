pipeline {
    agent any

    tools {
        maven 'Maven_3.9.9'
        jdk 'JDK_17'
    }

    environment {
        IMAGE_NAME = 'wajihdocker/demoproduit'
        IMAGE_TAG = 'latest'
        DOCKER_REGISTRY = 'docker.io' // exemple: 'dockerhub' ou vide si pas de push
        DOCKER_CREDENTIALS_ID = 'docker_credantial'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Test Unitaire') {
            steps {
                echo "Lancement des tests unitaires..."
                bat './mvnw test' // ou 'mvn test' si vous n'utilisez pas le wrapper
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube_server') {
                    bat 'mvn sonar:sonar -Dsonar.projectKey=pip_backend'
                }
            }
        }

        stage('Package') {
            steps {
                bat './mvnw clean package -DskipTests'
                bat 'dir target\\*.jar'
            }
        }

        stage('Nexus') {
            steps {
                nexusArtifactUploader(
                    artifacts: [[
                        artifactId: 'demoproduit',
                        file: 'target/demoproduit-0.0.1-SNAPSHOT.jar',
                        type: 'jar'
                    ]],
                    credentialsId: 'nexus_token',
                    groupId: 'com.example',
                    nexusUrl: 'localhost:8082',
                    nexusVersion: 'nexus3',
                    protocol: 'http',
                    repository: 'maven-snapshots',
                    version: '0.0.1-SNAPSHOT'
                )
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    bat "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                }
            }
        }

//         stage('Optional: Push Docker Image') {
//             when {
//                 expression { return env.DOCKER_REGISTRY?.trim() }
//             }
//             steps {
//                 script {
//                     docker.withRegistry("https://${DOCKER_REGISTRY}", 'docker_credantial') {
//                         def fullImage = "${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}"
//
//                         echo "Tagging and pushing image: ${fullImage}"
//
//                         bat "docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${fullImage}"
//                         bat "docker push ${fullImage}"
//                     }
//                 }
//             }
//         }
    }

    post {
        success {
            mail to: 'wajihbenhmida5@gmail.com',
                subject: "✅ SUCCESS: Build #${env.BUILD_NUMBER} Passed",
                body: "The build was successful. Job: ${env.JOB_NAME} - Build number: ${env.BUILD_NUMBER}"
        }
        failure {
            mail to: 'wajihbenhmida5@gmail.com',
                subject: "❌ FAILURE: Build #${env.BUILD_NUMBER} Failed",
                body: "The build failed. Check Jenkins for details: ${env.BUILD_URL}"
        }
    }
}
