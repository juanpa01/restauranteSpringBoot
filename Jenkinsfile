
pipeline{
 agent any

    stages{
        stage('checkout'){
            steps{
                git branch: 'main', credentialsId: 'githubId', url: 'https://github.com/juanpa01/comun'
            }
        }

        /*
        stage('checkout2'){
            steps{
                git branch: '${BRANCH_NAME}', credentialsId: 'githubId', url: 'https://github.com/juanpa01/comun'
            }
        }*/

        
                /*
                stage('Test- Frontend'){
                    steps {
                        echo '------------>Test Frontend<------------'
                        dir("${PROJECT_PATH_FRONT}"){
                            // comando ejecucion test
                        }
                    }
                }
                */
            }
        }
        /*
        stage('Static Code Analysis') {
            environment {
                SONARSCANNER = "${tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner"
            }
            steps{
                echo '------------>Análisis de código estático<------------'
                withSonarQubeEnv('SonarQubePruebas') {
                    sh "./gradlew sonarqube"
                }
                echo '------------>Revision de Quality Gates<------------'
                sleep 5
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
			}
        }

        stage('Build'){
            parallel {
                stage('construcción Backend'){
                    steps{
                        echo "------------>Compilación backend<------------"
                        dir("${PROJECT_PATH_BACK}"){
                            sh './gradlew build -x test'
                        }
                    }
                }
            }
         }*/
    }
	/*
    post {
        failure {
            mail(
                to: '',
                body:"Build failed in Jenkins: Project: ${env.JOB_NAME} Build /n Number: ${env.BUILD_NUMBER} URL de build: ${env.BUILD_NUMBER}/n/nPlease go to ${env.BUILD_URL} and verify the build",
                subject: "ERROR CI: ${env.JOB_NAME}"
            )
            updateGitlabCommitStatus name: 'IC Jenkins', state: 'failed'
        }
        success {
            updateGitlabCommitStatus name: 'IC Jenkins', state: 'success'
        }
    }	*/
    
}