
pipeline{
 agent any
 	
    triggers {
        pollSCM('@daily') 
    }
	
    stages{
        stage('checkout'){
            git credentialsId: 'githubId', url: 'https://github.com/juanpa01/restauranteSpringBoot.git'
        }
    }
	
}