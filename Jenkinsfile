pipeline {
agent {
        node {
            label 'localmachine'
        }
       }

    stages {
        stage('Checkout') {
            steps {
                // Checkout source code from Git repository
                bat 'git clone https://github.com/navee-hans/M2IAutomation.git'
            }
        }

        stage('Build and test') {
            steps {
                // Build the Maven project
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
             junit '**/target/cucumber-reports/*.xml'
        }

        }
}