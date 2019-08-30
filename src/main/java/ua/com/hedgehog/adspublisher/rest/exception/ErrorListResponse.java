package ua.com.hedgehog.adspublisher.rest.exception;

import java.util.List;

public class ErrorListResponse {
    private int status;
    private List<String> messages;

    public ErrorListResponse(int status, List<String> messages) {
        this.status = status;
        this.messages = messages;
    }

    public int getStatus() {
        return status;
    }

    public List<String> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return "ErrorListResponse{" +
                "status=" + status +
                ", messages=" + messages +
                '}';
    }
}
