pipeline {
    agent any

    tools {
         maven 'Maven_3.9.9'
         jdk 'JDK_17'
     }

    environment {
        IMAGE_NAME = 'wajihdocker/wajihrepo'
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

       stage('Build Docker Image') {
            steps {
                script {
                    bat "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                }
            }
        }

          stage('Optional: Push Docker Image') {
                  when {
                      expression { return env.DOCKER_REGISTRY?.trim() }
                  }
                  steps {
                     script {
                         docker.withRegistry("https://${DOCKER_REGISTRY}", 'docker_credantial') {
                             def fullImage = "${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}"

                             // Affichage utile pour debug
                             echo "Tagging and pushing image: ${fullImage}"

                             // Tag l'image localement vers le nom complet du registre
                             bat "docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${fullImage}"

                             // Push l'image taguée
                             bat "docker push ${fullImage}"
                         }
                     }

                  }
//             steps {
//                            // bat "docker login -u ${DOCKER_HUB_CREDENTALS_USR} -p ${DOCKER_HUB_CREDENTALS_PSW}"
//                            script {
//                                             docker.withRegistry("https://${DOCKER_REGISTRY}", 'docker_credantial') {
//                                                      def image = docker.image("${IMAGE_NAME}:${IMAGE_TAG}")
//                                                                     image.push()
//                                                    }
//                                          }
//                         }
//               }
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
