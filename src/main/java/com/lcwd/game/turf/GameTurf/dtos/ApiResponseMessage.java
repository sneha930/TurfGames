package com.lcwd.game.turf.GameTurf.dtos;

import org.springframework.http.HttpStatus;

public class ApiResponseMessage {
    private String message;
    private Boolean success;
    private HttpStatus status;

    public ApiResponseMessage(String message, Boolean success, HttpStatus status) {
        this.message = message;
        this.success = success;
        this.status = status;
    }

    public ApiResponseMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

//    static Builder class
    public static class Builder {
        private String message;
        private Boolean success;
        private HttpStatus status;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder success(Boolean success) {
            this.success = success;
            return this;
        }

        public Builder status(HttpStatus status) {
            this.status = status;
            return  this;
        }

        public ApiResponseMessage build() {
            return new ApiResponseMessage(message, success, status);
        }
}

    @Override
    public String toString() {
        return "ApiResponseMessage{" +
                "message='" + message + '\'' +
                ", success=" + success +
                ", status=" + status +
                '}';
    }
}
