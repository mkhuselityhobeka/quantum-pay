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
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
     }
}