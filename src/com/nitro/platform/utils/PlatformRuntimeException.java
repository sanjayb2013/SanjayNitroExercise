package com.nitro.platform.utils;

import java.text.MessageFormat;

public class PlatformRuntimeException extends RuntimeException {
	
	private static final long serialVersionUID = 3723616097327257900L;
	private String errCd = null; // Mandatory
	private Object[] params = null;

	public enum RUNEC {
		INVALID_API_USAGE("A critical system error has occurred. Incorrect System API Usage. {0}"), 
		INVALID_PROCESSING_DATA("A system error has occurred. Invalid processing data. {0}"),
		;

		private String internalErrMsg; // Note: This is the actual message used for display to the users.
		private MessageFormat mf;

		private RUNEC(String l_errMsg) {
			internalErrMsg = l_errMsg;
			mf = new MessageFormat(l_errMsg);
		}

		public String getInternalErrMsg(Object[] params) {
			if (params != null && params.length > 0)
				return mf.format(params, new StringBuffer(), null).toString();
			else
				return internalErrMsg;
		}

	}

	/** Creates a RuntimeException with the specified error code.
	 * 
	 * @param errCd mandatory, the error code must be provided and should not be null
	 * @param params optional, the parameters that provide additional context/information for the error
	 * @param message optional, defaults to the following: "ERROR_CODE[errCd]"
	 * @param cause optional, the cause of this error */
	protected PlatformRuntimeException(String errCd, Object[] params, String message, Throwable cause) {
		super((message == null ? "ECS_ERROR_CODE[" + errCd + "]" : message), cause);
		this.errCd = errCd;
		this.params = params == null ? new Object[] {} : params;
	}

	/** Creates an PlatformRuntimeException with the specified error code.
	 * 
	 * @param errCd mandatory, the error code must be provided and should not be null
	 * @param params optional, the parameters that provide additional context/information for the error
	 * @param cause optional, the cause of this error */
	public PlatformRuntimeException(RUNEC errCd, Object[] params, Throwable cause) {
		super(errCd.getInternalErrMsg(params), cause);
		this.errCd = errCd.name();
		this.params = params == null ? new Object[] {} : params;
	}

	public String getErrCd() {
		return errCd;
	}

	public Object[] getParams() {
		return params;
	}

	@Override
	public String getLocalizedMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("ECS_ERROR_CODE: [");
		sb.append(errCd);
		sb.append(']');
		if (getMessage() != null) {
			sb.append(" ERROR_MSG: [");
			sb.append(getMessage());
			sb.append(']');
		}
		for (int i = 0; i < params.length; i++) {
			if (i == 0)
				sb.append(" PARAMS: [");
			if (params[i] != null) {
				sb.append("{");
				sb.append(params[i]);
				sb.append('}');
			}
			if (i < params.length - 1)
				sb.append(',');
			else
				sb.append(']');
		}
		fillCauseMessage(sb, getCause(), 1);
		return sb.toString();
	}

	private void fillCauseMessage(StringBuilder sb, Throwable cause, int i) {
		if (cause != null && cause.getMessage() != null) {
			sb.append(System.getProperty("line.separator").charAt(0));
			sb.append("CAUSE#");
			sb.append(i++);
			sb.append(": [");
			sb.append(cause.toString());
			sb.append(']');
			fillCauseMessage(sb, cause.getCause(), i);
		}
	}

}
