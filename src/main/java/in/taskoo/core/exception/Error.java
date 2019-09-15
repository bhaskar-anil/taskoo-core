package in.taskoo.core.exception;

import java.util.Map;

import org.apache.commons.collections.MapUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error {

    private String code;

    private String message;

    private String placeholderMessage;

    private Map<String, String> placeholders;

    public Error(final String code, final String placeholderMessage,
            final Map<String, String> placeholders) {
        this.code = code;
        this.message = getMessage(placeholderMessage, placeholders);
        this.placeholderMessage = placeholderMessage;
        this.placeholders = placeholders;
    }

    public String getMessage(String placeholderMessage, final Map<String, String> placeholders) {
        if (MapUtils.isEmpty(placeholders)) {
            return placeholderMessage;
        }
        for (final Map.Entry<String, String> entry : placeholders.entrySet()) {
            final String placeholderString = "{" + entry.getKey() + "}";
            placeholderMessage = placeholderMessage.replace(placeholderString,
                    entry.getValue() == null ? "null" : entry.getValue());
        }
        return placeholderMessage;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Error other = (Error) obj;
        if (code == null) {
            if (other.code != null) {
                return false;
            }
        } else if (!code.equals(other.code)) {
            return false;
        }
        return true;
    }
}
