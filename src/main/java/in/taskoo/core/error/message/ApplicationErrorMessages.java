package in.taskoo.core.error.message;

import in.taskoo.core.exception.ValidationMessage;

public enum ApplicationErrorMessages implements ValidationMessage {
    CONSTRAINT_FAILED("AEM-000", "{error}"),
    NO_DATA_FOUND("AEM-001" , "No data found"),
	TASK_UPDATE_NOT_ALLOWED("AEM-002","Update is not allowed as task is {status}"),
	TASK_CANCEL_NOT_ALLOWED("AEM-003","Task can't be cancelled");

    private String code;
    private String placeholderMessage;

    private ApplicationErrorMessages(final String code,final String placeholderMessage) {
        this.code = code;
        this.placeholderMessage = placeholderMessage;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getPlaceholderMessage() {
        return this.placeholderMessage;
    }
}