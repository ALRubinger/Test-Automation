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
package test.automation.execution;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.hibernate.Query;
import org.hibernate.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.automation.model.http.User;
import test.automation.model.test.model.configuration.Client;
import test.automation.model.test.model.configuration.TestConfiguration;
import test.automation.model.test.model.test.TestDefinition;

/**
 * @author Walter White
 */
@ApplicationScoped
public class ClientService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
	@Inject
	private Session session;

	/**
	 * Produces the application clients
	 * @param testConfiguration
	 * @return
	 * @throws Exception
	 */
	@ApplicationScoped
	@Produces
	public Set<Client> buildClients(TestConfiguration testConfiguration) throws Exception {
		LOGGER.debug("load testing application");

		final Set<Client> clients = new HashSet<Client>();

		final Query query = session.getNamedQuery("User.findActive");
		final List<User> activeUsers = query.list();

		for (User user : activeUsers) {
			buildClient(testConfiguration, clients, user);
		}

		return(clients);
	}

	private void buildClient(TestConfiguration testConfiguration, final Set<Client> clients, User user) {
		final int numberOfClientsForUser = getNumberOfClientsForUser(user);
		final Set<TestDefinition> testDefinitionsForUser = getMatchingTestDefinitions(user, testConfiguration.getTestDefinitions());
		final int clientDelay = getClientDelay(user);

		createClientsForUser(clients, numberOfClientsForUser, testDefinitionsForUser, clientDelay);
	}

	private void createClientsForUser(final Set<Client> clients, final int numberOfClientsForUser, final Set<TestDefinition> testDefinitionsForUser, final int clientDelay) {
		for (int i = 0; i < numberOfClientsForUser; i++) {
			clients.add(new Client(testDefinitionsForUser, clientDelay));
		}
	}

	private Set<TestDefinition> getMatchingTestDefinitions(final User user, final Set<TestDefinition> testDefinitions) {
		final Set<TestDefinition> matchingTestDefinitions = new HashSet<TestDefinition>();
		for (final TestDefinition testDefinition : testDefinitions) {

			if (true) //if (user.equals(AuthenticationUtil.getValidUser(testDefinition))) {
			{
				matchingTestDefinitions.add(testDefinition);
			}
			//}
		}

		return (matchingTestDefinitions);
	}

	private int getNumberOfClientsForUser(User user) {
		return (Integer.valueOf(System.getProperty(user.getName() + ".instances")));
	}

	private int getClientDelay(User user) {
		return (Integer.valueOf(System.getProperty(user.getName() + ".delay")));
	}
}
