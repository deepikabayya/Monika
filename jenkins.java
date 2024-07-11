node {
	stage('Checkout code') {
         git 'https://github.com/jilu407/test-maven.git'

    }
	stage('Build'){
		echo 'build pass'
		sh 'mvn -version'
                 sh 'mvn clean'
	}
	stage('test 007'){
		echo 'test pass'
		sh "mvn test"
	}
	stage('Compile'){
		echo 'Compile pass'
		sh "mvn compile"
	}
	stage('package'){
		echo 'Compile pass'
		sh "mvn package"
	}
	stage('Ansible Deploy'){
		echo "Deploying the Project.........."
		ansiblePlaybook credentialsId: 'deploy', disableHostKeyChecking: true, installation: 'ansible', inventory: 'dev.inv', playbook: 'play.yml', sudoUser: null

	}
}

pipeline{
agent any
stages 
{
stage('Build') 
{
steps{
echo "Building the Code.........."
 sh "mvn -version"
 sh "mvn clean"
}
}
stage('Test') 
{
steps{
echo "Testing the Code.........."
sh "mvn test"
}
}
stage('Compile') 
{
steps{
echo "Compiling the Project.........."
sh "mvn compile"
}
}
stage('package') 
{
steps{
echo "Compiling the Project.........."
sh "mvn package"
}
}
stage('Deploy') 
{
steps{
echo "Deploying the Project.........."
/* sh "cp /var/lib/jenkins/workspace/maven-pipeline-1/target/*.war /opt/tomcat/webapps/" */
ansiblePlaybook credentialsId: 'edf29c56-6626-4ce1-a40e-4d6a98e6afea', disableHostKeyChecking: true, installation: 'ansible', inventory: 'dev.inv', playbook: 'play.yml', sudoUser: null
}
}
}
}
