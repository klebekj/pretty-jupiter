/*
 * This Groovy source file was generated by the Gradle 'init' task.
 */
package com.github.joselion.prettyjupiter

import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project
import spock.lang.Specification

/**
 * A simple unit test for the 'com.github.joselion.prettyjupiter.greeting' plugin.
 */
public class PrettyJupiterPluginTest extends Specification {
    def "plugin registers task"() {
        given:
        def project = ProjectBuilder.builder().build()

        when:
        project.plugins.apply("com.github.joselion.pretty-jupiter")

        then:
        project.tasks.findByName("greeting") != null
    }
}