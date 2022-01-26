package fr.diginamic.SpringGestionBanque.exceptions;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssuranceVieNotFoundException extends CompteNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AssuranceVieNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public AssuranceVieNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AssuranceVieNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public AssuranceVieNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AssuranceVieNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
