// @Library('ceiba-jenkins-library@master') _
pipeline{
	// any -> tomaria slave 5 u 8
	// Para mobile se debe especificar el slave -> {label 'Slave_Mac'}
	// Para proyectos de arus se debe tomar el slave 6 o 7 -> {label 'Slave6'} o {label 'Slave7'}
    agent any
	
    environment {
        PROJECT_PATH_BACK = 'restaurante'
    }
	
    triggers {
        // @yearly, @annually, @monthly, @weekly, @daily, @midnight, and @hourly o definir un intervalo ejemplo H */4 * * 1-5
        pollSCM('@daily') //define un intervalo regular en el que Jenkins debería verificar los cambios de fuente nuevos
    }
	
    // tools {
    //     jdk 'JDK13_Centos'
    // }
	
    // Parametros disponibles en jenkins
     /*parameters{
            string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
            text(name: 'BIOGRAPHY', defaultValue: '', description: 'Enter some information about the person')
            booleanParam(name: 'TOGGLE', defaultValue: true, description: 'Toggle this value')
            choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')
            password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'Enter a passwor')
     }*/
	
    stages{

        // stage('CheckoutrestauranteSpringBoot') {
        //     steps {
        //         // sh 'mkdir -p restauranteSpringBoot'
        //         dir("restauranteSpringBoot")
        //         {
        //             git branch: '${BRANCH_NAME}',
        //             credentialsId:  '4b7363c0-04e8-478f-bc50-9e7750f44f61',
        //             url: 'https://github.com/juanpa01/restauranteSpringBoot.git'
        //         }
        //     }
        // }

        // stage('Checkoutcomun') {
        //     steps {
        //         sh 'mkdir -p comun'
        //         dir("comun")
        //         {
        //             git branch: '${BRANCH_NAME}',
        //             credentialsId:  '4b7363c0-04e8-478f-bc50-9e7750f44f61',
        //             url: 'https://github.com/juanpa01/comun.git'
        //         }
        //     }
        // }

        

        // stage('Checkout') {
        //     steps {
        //         echo '------------>Checkout desde Git Microservicio<------------'
        //         //Esta opción se usa para el checkout sencillo de un microservicio
        //         gitCheckout(
        //              urlProject:'https://github.com/juanpa01/restauranteSpringBoot.git',
        //              branchProject: '${BRANCH_NAME}',
        //          )

        //         //Esta opción se usa cuando el comun está centralizado para varios microservicios
        //         /*gitCheckoutWithComun(
        //             urlProject:'https://github.com/juanpa01/restauranteSpringBoot.git',
        //             branchProject: '${BRANCH_NAME}',
        //             urlComun: 'https://github.com/juanpa01/comun.git'
        //         )*/
        //         dir("${PROJECT_PATH_BACK}"){
        //             sh 'chmod +x ./gradlew'
        //             sh './gradlew clean'
        //         }
        //     }
        // }

        stage('Compilacion y Test Unitarios'){
            // El "parallel" es si vamos a correr los test del frontend en paralelo con los test de backend, se configura en otro stage dentro de parallel
            parallel {
                stage('Test- Backend'){
                    steps {
                        echo '------------>Test Backend<------------'
                        dir("${PROJECT_PATH_BACK}"){
                            sh './gradlew --stacktrace test'
                        }
                    }
                    post{
                        always {
                            junit '**/build/test-results/test/*.xml' //Configuración de los reportes de JUnit
                        }
                    }
                }
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
         }
    }
	
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
    }	
}