#!/usr/bin/env groovy

//@Library(['piper-lib', 'piper-lib-os']) _

pipeline {
    stages {
        stage('Cleanup') {
            steps {
                cleanWs()
                deleteDir()
                sh '''
                    #!/bin/bash
                    echo "Starting Test execution..."
                '''
            }
        }
        stage('Test1') {
            parallel {
                stage('SET1') {
                    steps {
                        dir('SET1') {
                            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                                echo "Downloading source code from PR7_MIT_Test branch "
                                git branch: 'main', poll: false, url: 'https://github.com/QAJ123/JenkinsIntegration.git'
                                setupPipelineEnvironment script: this, storeGithubStatistics: true
                                mavenExecute script: this,
                                        goals: ['clean', 'test']
                            }
                        }
                    }

                }
            }

        }

    }
}