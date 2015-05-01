if (System.getenv('TRAVIS_BRANCH')) {
    grails.project.repos.grailsCentral.username = System.getenv("GRAILS_CENTRAL_USERNAME")
    grails.project.repos.grailsCentral.password = System.getenv("GRAILS_CENTRAL_PASSWORD")
}

grails.project.work.dir = 'target'

grails.project.target.level = 1.6
grails.project.source.level = 1.6

grails.project.dependency.resolver = 'maven'
grails.project.dependency.resolution = {

    inherits 'global'
    log 'warn'

    repositories {
        mavenLocal()
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()
        mavenRepo 'http://repo.spring.io/milestone'
        mavenRepo 'https://raw.github.com/fernandezpablo85/scribe-java/mvn-repo'
    }

    plugins {
        compile ':spring-security-core:2.0-RC4'
        compile ':oauth:2.6.1'
        compile ':codenarc:0.22'
        // we need this to compile in Grails 2.4 RC...
        compile ':hibernate:3.6.10.18', {
            export = false
        }

        build ':release:3.1.1', ':rest-client-builder:2.1.1', ':testapps:0.5.0', {
            export = false
        }
    }
}

codenarc {
    reports = {
        CodenarcXmlReport('xml') {
            outputFile = 'target/CodeNarc-Report.xml'
            title = 'Spring Security OAuth plugin CodeNarc Report'
        }
        CodenarcHtmlReport('html') {
            outputFile = 'target/CodeNarc-Report.html'
            title = 'Spring Security OAuth plugin CodeNarc Report'
        }
    }
    ruleSetFiles = 'file:grails-app/conf/CodeNarcRuleSet.groovy'
    maxPriority1Violations = 0
    maxPriority2Violations = 0
    maxPriority3Violations = 0
}
