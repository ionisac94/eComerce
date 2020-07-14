pipeline {

    agent any

    tools {
        maven 'Maven_3.6.2'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile stage') {
            steps {
                bat 'mvn compile'
                step([$class: 'JacocoPublisher'])
            }
        }

        stage('Build stage') {
            steps {
                bat 'mvn -DskipTest clean package'
            }
        }

        stage('Unit Tests') {
            steps {
                bat "mvn test"
            }
        }

        stage('Result') {
            steps {
                publishHTML([allowMissing         : false,
                             alwaysLinkToLastBuild: true,
                             keepAll              : true,
                             reportDir            : 'target/site/jacoco/',
                             reportFiles          : 'index.html',
                             reportName           : 'Jacoco Coverage Report'
                ])

            }

        }
    }
}