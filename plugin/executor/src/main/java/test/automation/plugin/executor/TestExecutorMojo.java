/*
 * WalterJWhite.com
 * Copyright 2010, Walter White, and individual contributors
 * by the @authors tag.
 *
 * Licensed under the GNU Lesser General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.gnu.org/licenses/lgpl.html
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test.automation.plugin.executor;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
import org.apache.maven.embedder.MavenEmbedder;
import org.apache.maven.model.Model;
import org.apache.maven.model.Profile;
import org.apache.maven.monitor.event.EventMonitor;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.wagon.events.TransferListener;

/**
 * Responsible for running the tests with all of the profiles configured in profiles.xml.
 * @author Walter White
 */
public class TestExecutorMojo extends AbstractMojo {

	/**
	 * Test Profile Names: this is actually the prefix of the test profile name allowed to run.
	 * @parameter
	 */
	private String[] testProfileNamePatterns;
	private Pattern[] testProfileNamingPatterns;
	/**
	 * pom.xml file, specifying this makes the rest of the code much easier.
	 * And, it is trivial to get from within maven.
	 * @parameter
	 */
	private File pomFile;
	private final MavenEmbedder mavenEmbedder;
	private Model model;

	public TestExecutorMojo() {
		mavenEmbedder = new MavenEmbedder();
	}

	@Override
	public void execute() throws MojoExecutionException {
		try {
			model = mavenEmbedder.readModel(pomFile);
			test(model);
		} catch (Exception e) {
			throw (new MojoExecutionException("Unable to run tests", e));
		}
	}

	/**
	 * Finds all of the matching test profiles.
	 * @param model the maven model (pom.xml) read into a Java object.
	 * @return list of matching test profiles.
	 */
	private List<Profile> getTestProfiles(final Model model) {
		constructTestProfileNamingPatterns();

		final List<Profile> testProfiles = new ArrayList<Profile>();
		for (final Profile profile : (List<Profile>) model.getProfiles()) {
			if (isTestProfile(profile)) {
				testProfiles.add(profile);
			}
		}

		return (testProfiles);
	}

	private void constructTestProfileNamingPatterns() {
		testProfileNamingPatterns = new Pattern[testProfileNamePatterns.length];
		for (int index = 0; index < testProfileNamePatterns.length; index++) {
			testProfileNamingPatterns[index] = Pattern.compile(testProfileNamePatterns[index]);
		}
	}

	/**
	 * Checks if the profile begins with one of the names in our list of test profile names.
	 * @param profile the profile to check
	 * @return true if it matches, false otherwise.
	 */
	private boolean isTestProfile(final Profile profile) {
		for (final Pattern testProfileNamingPattern : testProfileNamingPatterns) {
			if (testProfileNamingPattern.matcher(profile.getId()).matches()) {
				return (true);
			}
		}

		return (false);
	}

	/**
	 * Tests all of the profiles.
	 * @param model the maven model (pom.xml).
	 */
	private void test(final Model model) throws Exception {
		for (final Profile profile : getTestProfiles(model)) {
			test(model, profile);
		}
	}

	/**
	 * Tests the given model with the specified profile.
	 * @param model the maven model (pom.xml).
	 * @param profile the profile to test.
	 */
	private void test(final Model model, final Profile profile) throws Exception {
		runTests(pomFile, model, profile);
	}

	private void runTests(final File pomFile, final Model model, final Profile profile) throws Exception {
		final MavenProject mavenProject = mavenEmbedder.readProject(pomFile);

		activateProfile(model, profile, mavenProject);
		final List<String> goals = Arrays.asList(new String[]{"clean", "integration-test"});

		final EventMonitor eventMonitor = null;
		final TransferListener transferListener = null;
		final Properties properties = null;
		final File executionRootDirectory = new File(pomFile.getParentFile().getAbsolutePath() + File.separator + "target");

		mavenEmbedder.execute(mavenProject, goals, eventMonitor, transferListener, properties, executionRootDirectory);
	}

	private void activateProfile(final Model model, final Profile profile, final MavenProject mavenProject) {
		final List<Profile> activeProfiles = mavenProject.getActiveProfiles();
		activeProfiles.add(profile);

		mavenProject.setActiveProfiles(activeProfiles);
	}
}
