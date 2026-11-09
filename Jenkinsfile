pipeline {
    agent any

    environment {
        HEADLESS = 'true'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Execute UI & Accessibility Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            // Publishes TestNG & Cucumber test results to the Jenkins UI
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
        }
    }
}