package jsonapi.model;


public class StatusResponse {

    private Integer id;
    private Boolean beforeIsOnline;
    private Boolean afterIsOnline;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getBeforeIsOnline() {
        return beforeIsOnline;
    }

    public void setBeforeIsOnline(Boolean beforeIsOnline) {
        this.beforeIsOnline = beforeIsOnline;
    }

    public Boolean getAfterIsOnline() {
        return afterIsOnline;
    }

    public void setAfterIsOnline(Boolean afterIsOnline) {
        this.afterIsOnline = afterIsOnline;
    }
}
