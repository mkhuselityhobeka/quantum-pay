pipeline {
     agent any
     tools {
        maven 'Maven 3.8.6'
        jdk 'jdk11'
     }
     triggers {
        pollSCM '* * * * *'
     }
     stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
     }
}