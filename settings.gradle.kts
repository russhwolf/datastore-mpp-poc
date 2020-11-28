pluginManagement {
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.android.library" -> useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }

    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }
}

include(":internal-testutils-truth")
include(":datastore-core")
