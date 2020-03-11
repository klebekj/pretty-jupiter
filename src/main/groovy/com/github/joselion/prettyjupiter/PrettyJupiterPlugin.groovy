package com.github.joselion.prettyjupiter

import static org.gradle.api.tasks.testing.logging.TestExceptionFormat.SHORT

import org.gradle.api.Project
import org.gradle.api.Plugin

import com.github.joselion.prettyjupiter.helpers.PrettyJupiterPluginException

/**
 * A Gradle plugin to better log JUnit Jupiter tests when run from the Gradle
 * test task. Nested tests are grouped and the output styles is very similar to
 * {@code mocha.js}
 */
public class PrettyJupiterPlugin implements Plugin<Project> {
  public void apply(Project project) {
    try {
      final PrettyJupiterPluginExtension extension = project.extensions.create('prettyJupiter', PrettyJupiterPluginExtension)
      final PrettyLogger prettyLogger = new PrettyLogger(project, extension)

      project.test.testLogging {
        exceptionFormat(SHORT)
        showStandardStreams(true)
      }

      project.test.beforeSuite(prettyLogger.&logDescriptors)

      project.test.afterTest(prettyLogger.&logResults)
    } catch(MissingPropertyException e) {
      throw new PrettyJupiterPluginException('This plugin canm only be applied if a test task exist!', e)
    }
  }
}
