#!/usr/bin/env groovy

def call(String githubToken, String imageName, String gitUserEmail, String gitUserName, String gitRepoName) {

    withCredentials([string(credentialsId: "${githubToken}", variable: 'GITHUB_TOKEN')]) {
                sh '''
                        git config user.email "${gitUserEmail}"
                        git config user.name "${gitUserName}"
                        sed -i "s|image:.*|image: ${imageName}:${BUILD_NUMBER}|g" deployment.yml
                        git add deployment.yml
                        git commit -m "Update deployment image to version ${BUILD_NUMBER}"
                        git pull https://${GITHUB_TOKEN}@github.com/${gitUserName}/${gitRepoName} main
                        git push https://${GITHUB_TOKEN}@github.com/${gitUserName}/${gitRepoName} HEAD:main
                    '''
    

  }
}
