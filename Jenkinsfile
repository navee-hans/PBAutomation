pipeline {
agent {
        node {
            label 'localmachine'
        }
       }

    stages {
//         stage('Checkout') {
//             steps {
//                 // Checkout source code from Git repository
//                 sh 'git clone https://github.com/navee-hans/M2IAutomation.git'
//             }
//         }

        stage('Build') {
            steps {
                // Build the Maven project
                sh 'mvn clean install -DskipTests=true'
            }
        }

        stage('Test') {
            steps {
                // Run Cucumber tests
                sh 'mvn test'
            }
        }

        stage('Publish Test Results') {
            steps {
                // Publish Cucumber test reports
                cucumber buildStatus: 'UNSTABLE', failedFeaturesNumber: 1, failedScenariosNumber: 1, skippedScenariosNumber: 1, unstableFeaturesNumber: 1, unstableScenariosNumber: 1
            }
        }
    }

    post {
        always {
            // Archive artifacts
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
}