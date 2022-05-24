package com.myapp;

import java.util.Collections;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

@ApplicationScoped
public class IDStoreImpl implements IdentityStore {

	@Override
	public CredentialValidationResult validate(Credential credential) {

		if (!(credential instanceof UsernamePasswordCredential)) {
			return CredentialValidationResult.NOT_VALIDATED_RESULT;
		}

		UsernamePasswordCredential unCred = (UsernamePasswordCredential) credential;

		if ("user1".equals(unCred.getCaller()) && "password1".equals(unCred.getPasswordAsString())) {

			return new CredentialValidationResult("user1", Collections.emptySet());

		} else {
			return CredentialValidationResult.INVALID_RESULT;
		}
	}

	@Override
	public int priority() {
		return 0;
	}
}
