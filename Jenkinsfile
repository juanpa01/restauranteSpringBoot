pipeline{
    agent any
    triggers {
        pollSCM('* * * * *')
    }

    stages{
        stage('Checkout') {
            steps {
                echo '------------>Checkout desde Git Microservicio<------------'
                //Esta opción se usa para el checkout sencillo de un microservicio
                // gitCheckout(
                //     urlProject:'https://github.com/juanpa01/restauranteSpringBoot.git',
                //     branchProject: '${BRANCH_NAME}',
                // )

                //Esta opción se usa cuando el comun está centralizado para varios microservicios
                gitCheckoutWithComun(
                    urlProject:'https://github.com/juanpa01/restauranteSpringBoot.git',
                    branchProject: '${BRANCH_NAME}',
                    urlComun: 'https://github.com/juanpa01/comun.git'
                )

                dir("${PROJECT_PATH_BACK}"){
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean'
                }
            }
        }

    stages {
        stage("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
        }
        stage("Unit test") {
            steps {
                sh "./gradlew test"
            }
        }
        stage("Code coverage") {
            steps {
        	    sh "./gradlew jacocoTestReport"
        	 	publishHTML (target: [
         	        reportDir: 'build/reports/jacoco/test/html',
         			reportFiles: 'index.html',
         			reportName: 'JacocoReport'
         	    ])
         		sh "./gradlew jacocoTestCoverageVerification"
         	}
        }
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('SonarQubePruebas') {
                    sh './gradlew sonarqube'
                }
            }
        }
    }
}