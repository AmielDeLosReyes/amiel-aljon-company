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
                    def services = ['app-registry', 'config-server', 'app-registry']
                    for (service in services) {
                        dir(service) {
                            sh './mvnw clean package'
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
