pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'JDK-21'
    }

    environment {
        // Enables headless execution for Jenkins servers
        CHROME_HEADLESS = 'true'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile & Static Check') {
            steps {
                bat 'mvn clean test-compile'
            }
        }

        stage('Execute Functional & A11y Tests') {
            steps {
                bat 'mvn test -Dcucumber.filter.tags="@smoke or @dashboard or @accessibility"'
            }
        }
    }

    post {
        always {
            // Archive TestNG and Cucumber results in Jenkins
            junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
            archiveArtifacts artifacts: '**/target/surefire-reports/**/*', allowEmptyArchive: true
        }
        success {
            echo 'All functional and accessibility tests passed successfully!'
        }
        failure {
            echo 'Build failed: UI regression or accessibility violation detected.'
        }
    }
}