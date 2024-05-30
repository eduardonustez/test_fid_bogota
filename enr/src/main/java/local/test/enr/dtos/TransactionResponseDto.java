package local.test.enr.dtos;

public class TransactionResponseDto {
    private boolean success;
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    private String error;
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
}
