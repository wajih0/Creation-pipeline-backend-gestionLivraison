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



//         stage('Test') {
//             steps {
//                 bat 'mvn test'
//             }
//         }
//
//            stage('Test Unitaire') {
//                     steps {
//                         echo "Lancement des tests unitaires..."
//                         bat './mvnw test' // ou 'mvn test' si vous n'utilisez pas le wrapper
//                     }
//                 }

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
                                                      // Vérification que le WAR est généré
                                                      bat 'ls -l target/*.war'
                                                  }
                                    }
                           stage('Nexus') {
                                        steps {
                                              nexusArtifactUploader(
                                                          artifacts: [[
                                                              artifactId: 'demoproduit',
                                                              file: 'target/demoproduit-0.0.1-SNAPSHOT.war', // Nom exact
                                                              type: 'war' // Spring Boot génère un .war par défaut
                                                          ]],
                                                          credentialsId: 'nexus',
                                                          groupId: 'com.example', // Doit matcher <groupId>
                                                          nexusUrl: 'localhost:8082',
                                                          nexusVersion: 'nexus3',
                                                          protocol: 'http',
                                                          repository: 'maven-snapshots',
                                                          version: '0.0.1-SNAPSHOT' // Doit matcher <version>
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
