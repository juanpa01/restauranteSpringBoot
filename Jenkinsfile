
pipeline{
 agent any

    triggers {
        pollSCM('@daily') 
    }
	
    stages{
        stage('checkout'){
            strps{
                git credentialsId: 'githubId', url: 'https://github.com/juanpa01/restauranteSpringBoot.git'
            }
        }
    }
	
}