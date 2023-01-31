package co.zip.candidate.userapi.account;

public class AccountDTO {
    private long id;
    private long userId;

    public AccountDTO(long id, long userId) {
        this.id = id;
        this.userId = userId;
    }

    public AccountDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
