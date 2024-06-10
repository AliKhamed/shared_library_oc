#!/usr/bin/env groovy

def call(String sonarQubeCredentialsID, String SONAR_PROJECT_KEY, String sonarqubeUrl) {

    withCredentials([string(credentialsId: 'sonarQubeCredentialsID', variable: 'SONAR_TOKEN')]) {
                        sh  """
                            ./gradlew sonar \
                            -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                            -Dsonar.host.url=${sonarqubeUrl} \
                            -Dsonar.token=${SONAR_TOKEN} \
                            -Dsonar.scm.provider=git \
                            -Dsonar.java.binaries=build/classes
                            """
          }
}
