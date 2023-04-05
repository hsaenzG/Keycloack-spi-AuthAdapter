package com.identicum.keycloak;

import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.authenticators.browser.UsernamePasswordForm;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.jboss.logging.Logger;

public class CustomAuthenticator extends UsernamePasswordForm {
//public class CustomAuthenticator implements Authenticator {

	private static final Logger log = Logger.getLogger(CustomAuthenticator.class);

	@Override
	public void authenticate(AuthenticationFlowContext context) {
		SoapWebServiceClient.postSOAPXML();
		log.info("inside authenticate - hsaenz");
		//todo make your auth validation check logic
		String username = "carlitos";

		UserModel user = context.getSession().users().getUserByUsername(context.getRealm(), username);
		SoapWebServiceClient.postSOAPXML();
		context.success(); // With context.success(), or failing with an error, you can manage your custom validation
	}

	@Override
	public void action(AuthenticationFlowContext authenticationFlowContext) {

	}

	@Override
	public boolean requiresUser() {
		return false;
	}

	@Override
	public boolean configuredFor(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {
		return false;
	}

	@Override
	public void setRequiredActions(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {

	}

	@Override
	public void close() {

	}
}