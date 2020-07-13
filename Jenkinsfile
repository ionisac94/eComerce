pipeline {

    agent any

    tools {
        maven 'Maven_3.6.2'
    }

    stages {
        stage('Checkout'){
            steps{
                checkout scm
            }
        }

        stage('Compile stage') {
            steps {
                sh 'mvn clean compile'
                step([$class: 'JacocoPublisher' ] )
            }
        }

        stage('Testing stage') {
            steps {
                sh "mvn test"
            }
        }

        stage('Result'){
            steps{
                publishHTML([allowMissing: false,
                             alwaysLinkToLastBuild: true,
                             keepAll: true,
                             reportDir: 'target/site/jacoco/',
                             reportFiles: 'index.html',
                             reportName: 'Jacoco Coverage Report'
                ])

            }

        }
    }
    post { always { publishTestResults() } }
}


def publishTestResults() {
    junit 'target/surefire-reports/*.xml'
}