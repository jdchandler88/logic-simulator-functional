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
				    executeGradleTask("clean build scaladoc reportScoverage")
				}
            }
        }
    }
    post {
        always {
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'build/docs/scaladoc/', reportFiles: 'index-all.html', reportName: 'Scaladoc'])
            junit 'build/test-results/**/*.xml'
            step([$class: 'ScoveragePublisher', reportDir: 'build/reports/scoverage', reportFile: 'scoverage.xml'])
            cobertura autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: 'build/reports/scoverage/cobertura.xml', failUnhealthy: false, failUnstable: false, maxNumberOfBuilds: 0, onlyStable: false, sourceEncoding: 'ASCII', zoomCoverageChart: false
        }
    }
}

def executeGradleTask(String task) {
        sh "gradle ${task} -PmavenUser=$MAVEN_CREDS_USR -PmavenPassword=$MAVEN_CREDS_PSW"
}
