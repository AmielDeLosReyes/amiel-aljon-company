pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/AmielDeLosReyes/amiel-aljon-company.git'
            }
        }
        stage('Build JARs') {
            steps {
                script {
                    def services = ['app-registry', 'config-server', 'api-gateway']
                    for (service in services) {
                        dir(service) {
                            sh './mvnw clean package -DskipTests'
                        }
                    }
                }
            }
        }
        stage('Build Docker Images') {
            steps {
                sh 'docker-compose build'
            }
        }
        stage('Start Services') {
            steps {
                sh 'docker-compose up -d'
            }
        }
    }
}
