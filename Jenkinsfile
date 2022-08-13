pipeline {
     agent any
     triggers {
        pollSCM '* * * * *'
     }
     stages {
        stage('Build') {
            steps {
                sh './mvnw assemble'
            }
        }
     }
}