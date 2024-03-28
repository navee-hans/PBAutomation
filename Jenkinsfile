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

        stage('Build') {
            steps {
                // Build the Maven project
                bat 'mvn clean install -DskipTests=true'
            }
        }

        stage('Test') {
            steps {
                // Run Cucumber tests
                bat 'mvn test'
            }
        }

//         stage('Publish Test Results') {
//             steps {
//              // Generate HTML report from Cucumber JSON report using Cucumber Reports plugin
//             cucumberReports(
//                 fileIncludePattern: '**/target/cucumber-report.json',
//                 trendsLimit: 10)
//             }
//         }
    }

    post {
        always {
            // Archive artifacts
            cucumber(addTestResults: true, testResults: '**/target/cucumber-report.json'),
            //archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
}