package ru.sbrf.delivery.dto;

public class BaseResponse {
    private boolean result;
    private int code;

    public BaseResponse(boolean result, int code) {
        this.result = result;
        this.code = code;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
