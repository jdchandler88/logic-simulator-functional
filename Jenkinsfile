pipeline {
    agent any
	environment {
    	MAVEN_CREDS = credentials('mavenCreds')
    }
	tools {
	    gradle 'gradle-5.4.1'
	}
    stages {
        stage('build') {
            steps {
				script {
				    executeGradleTask("clean build scaladoc")
				}
            }
        }
    }
    post {
        always {
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'build/docs/javadoc/', reportFiles: 'index-all.html', reportName: 'Scaladoc'])
            junit 'build/test-results/**/*.xml'
            jacoco exclusionPattern: '**/*Test*.class'
        }
    }
}

def executeGradleTask(String task) {
        sh "gradle ${task} -PmavenUser=$MAVEN_CREDS_USR -PmavenPassword=$MAVEN_CREDS_PSW"
}