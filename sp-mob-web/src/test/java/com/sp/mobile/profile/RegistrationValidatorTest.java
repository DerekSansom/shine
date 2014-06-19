package com.sp.mobile.profile;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.validation.BindingResult;

public class RegistrationValidatorTest {

	RegistrationValidator registrationValidator = new RegistrationValidator();


	@Before
	public void setUp() throws Exception {
	}


	@Test
	public void testEmailPattern() {

		BindingResult errors = Mockito.mock(BindingResult.class);
		registrationValidator.validateEmail("derek.de_r-wer@del_sa-n.me.uk.me.com", errors);

		Mockito.verifyNoMoreInteractions(errors);
	}

}
