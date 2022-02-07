package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object Test : BuildType({
    name = "test"

    vcs {
        root(HttpsGithubComAgCloudtechnerJavaTomcatMavenExampleRefsHeadsMain)
    }
steps {
    maven {
        name = "Install"
        goals = "clean install"
    }
    step {
        name = "Sonar"
        type = "sonar-plugin"
        param("sonarServer", "1b5902dd-29e3-43a7-a9b3-6a3c3f8a0384")
    }
    maven {
        name = "Package"
        goals = "package"
    }
}
    triggers {
        vcs {
        }
    }
})
