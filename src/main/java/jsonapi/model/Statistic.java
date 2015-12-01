package jsonapi.model;

import jsonapi.entity.User;

import java.util.List;

public class Statistic {

    private Integer requestId;

    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Integer getRequestId() {

        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }
}
