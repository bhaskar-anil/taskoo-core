package in.taskoo.core.error.message;

import in.taskoo.core.exception.ValidationMessage;

public enum ApplicationErrorMessages implements ValidationMessage {
    CONSTRAINT_FAILED("AEM-000", "{error}"),
    NO_DATA_FOUND("AEM-000" , "No data found");

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