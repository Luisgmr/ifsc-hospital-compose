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

configurations.all {
    resolutionStrategy {
        eachDependency {
            if (requested.group == "org.jetbrains.skiko") {
                useVersion("0.8.18")
            }
        }
        // Força explicitamente o runtime para Windows
        force("org.jetbrains.skiko:skiko-awt-runtime-windows-x64:0.8.18")
    }
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation("mysql:mysql-connector-java:8.0.27")
    implementation("br.com.devsrsouza.compose.icons:font-awesome:1.1.1")
    implementation("com.seanproctor:data-table-material3:0.10.1")
    implementation("com.seanproctor:data-table:0.10.1")

    // Hibernate/JPA
    implementation("org.hibernate:hibernate-core:5.6.10.Final")
    implementation("javax.persistence:javax.persistence-api:2.2")
    implementation("org.hibernate:hibernate-entitymanager:5.6.10.Final") // Se necessário

    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.24")  // Usado para evitar a geração de código boilerplate
    annotationProcessor("org.projectlombok:lombok:1.18.24")  // Para usar o Lombok com a anotação de processamento
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
