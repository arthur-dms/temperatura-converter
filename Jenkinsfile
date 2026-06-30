pipeline {
    agent any

    triggers { cron('H/15 * * * *') }   // Cenário 4 (nightly) — só registra após a 1ª execução manual

    stages {
        stage('Checkout') {
            steps { checkout scm }
        }

        stage('Build') {                 // CONTAINER 1
            steps {
                sh '''
                    docker run --rm --volumes-from jenkins \
                      -w "$WORKSPACE" maven:3.9-eclipse-temurin-17 \
                      mvn -B clean compile
                '''
            }
        }

        stage('Test') {                  // CONTAINER 2 (isolado)
            steps {
                sh '''
                    docker run --rm --volumes-from jenkins \
                      -w "$WORKSPACE" maven:3.9-eclipse-temurin-17 \
                      mvn -B test -Dmaven.test.failure.ignore=true
                '''
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    jacoco execPattern: '**/target/jacoco.exec',
                           classPattern: '**/target/classes',
                           sourcePattern: '**/src/main/java'
                }
            }
        }
    }

    post {
        success  { echo 'Build OK e testes verdes (SUCCESS).' }
        unstable { echo 'Build OK, mas testes falharam (UNSTABLE).' }
        failure  { echo 'Falha de compilação (FAILURE).' }
    }
}
