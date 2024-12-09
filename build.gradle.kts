import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

group = "com.luisgmr.ifsc.hospital"
version = "1.0-SNAPSHOT"

repositories {
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation("mysql:mysql-connector-java:8.0.27")
    implementation("br.com.devsrsouza.compose.icons:font-awesome:1.1.1")
    implementation("com.seanproctor:data-table-material3:latest.release")
    implementation("com.seanproctor:data-table:latest.release")
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.luisgmr.ifsc.hospital.resources"
    generateResClass = auto
}

compose.desktop {
    application {
        mainClass = "AppKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ifsc-hospital-compose"
            packageVersion = "1.0.0"
        }
    }
}
