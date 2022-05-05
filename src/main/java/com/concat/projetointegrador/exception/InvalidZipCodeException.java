package com.concat.projetointegrador.exception;

import lombok.Data;

@Data
public class InvalidZipCodeException extends Exception {

		private static final long serialVersionUID = 1L;

		public InvalidZipCodeException() {
		}

		public InvalidZipCodeException(String message) {
				super(message);
		}

		public InvalidZipCodeException(String message, Throwable cause) {
				super(message, cause);
		}

		public InvalidZipCodeException(Throwable cause) {
				super(cause);
		}
}