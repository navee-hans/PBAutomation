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

        stage('Cucumber Reports'){
         steps{
            cucumber buildStatus: "UNSTABLE",
            fileIncludePattern: "**/cucumber-json-report.json",
            jsonReportDirectory: 'target'
         }

        }
    }

    post {
        always {
                            emailext subject: "Test Execution Report",
                             body: "Attached is the test execution report.",
                             to: "9ankeshsharma@gmail.com", "naveehans@gmail.com",
                             attachLog: true

        }

        }
}