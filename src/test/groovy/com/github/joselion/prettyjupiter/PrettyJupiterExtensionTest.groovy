package com.github.joselion.prettyjupiter

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

import spock.lang.Specification

class PrettyJupiterExtensionTest extends Specification {

  def 'creates the extension with default values'() {
    given:
      final Project project = ProjectBuilder.builder().build()
      final PrettyJupiterExtension extension = project.extensions.create('prettyJupiter', PrettyJupiterExtension)

    expect:
      extension.duration.enabled.get() == true
      extension.duration.threshold.get() == 75
      extension.failure.maxMessageLines.get() == 15
      extension.failure.maxTraceLines.get() == 10
  }

  def 'can modify extension using assignment'() {
    given:
      final Project project = ProjectBuilder.builder().build()
      final PrettyJupiterExtension extension = project.extensions.create('prettyJupiter', PrettyJupiterExtension)

    when:
      extension.duration.enabled = false
      extension.duration.threshold = 150
      extension.failure.maxMessageLines = 30
      extension.failure.maxTraceLines = 20

    then:
      extension.duration.enabled.get() == false
      extension.duration.threshold.get() == 150
      extension.failure.maxMessageLines.get() == 30
      extension.failure.maxTraceLines.get() == 20
  }

  def 'can modify extension using closures'() {
    given:
      final Project project = ProjectBuilder.builder().build()
      final PrettyJupiterExtension extension = project.extensions.create('prettyJupiter', PrettyJupiterExtension)

    when:
      extension.duration {
        enabled = false
        threshold = 150
      }

      extension.failure {
        maxMessageLines = 30
        maxTraceLines = 20
      }

    then:
      extension.duration.enabled.get() == false
      extension.duration.threshold.get() == 150
      extension.failure.maxMessageLines.get() == 30
      extension.failure.maxTraceLines.get() == 20
  }
}
