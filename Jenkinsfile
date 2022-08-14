pipeline {
     agent any
     tools {
        maven 'Maven 3.8.1'
        jdk 'jdk-11.0.1'
     }
     node {
        jdk = tool name: 'jdk-11.0.1'
        env.JAVA_HOME = "${jdk}"
        sh "${jdk}/bin/java -version"
        sh "${JAVA_HOME}/bin/java -version"
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

        stage ('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
     }
}