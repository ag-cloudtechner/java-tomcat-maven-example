pipeline {
    agent any
    tools {
	maven 'java_maven'
    }  
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',		    
		credentialsId: '94ac03cc-9a87-4df7-ad17-380b4eebc193',
			url: "https://github.com/ag-cloudtechner/java-tomcat-maven-example.git"    
	    }
	}	
        stage('Building') {
            steps {
                sh 'mvn clean install'
            }
        }		    
	    
        stage('Sonar Testing') {
            steps {

                withSonarQubeEnv('sonar1') {
                    sh "mvn sonar:sonar"
                }
            }
	}   
         stage('Quality Gate') {
           steps {
              timeout(time: 1, unit: 'MINUTES') {
                waitForQualityGate abortPipeline:true
              }
            }
          }
	    stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
	stage('Deploy') {
            steps {
		    sh 'cp /root/.jenkins/workspace/pipeline/target/*.war /opt/apache-tomcat-9.0.58/webapps/'
            }
        }
  }
}
