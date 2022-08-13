pipeline {
     agent any
     tools {
        maven 'MAVEN_HOME'
        jdk 'JAVA_HOME'
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