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
                sh './mvnw clean package'
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
