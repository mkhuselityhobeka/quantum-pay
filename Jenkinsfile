pipeline {
     agent any

     tools {
        maven 'Maven 3.8.1'
        jdk 'jdk-11.0.1'
     }

     triggers {
        pollSCM '* * * * *'
     }
     stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ("Build and Push docker image") {
            steps {
                sh 'mvn clean install'
            }
        }
     }
}