package entities;

import java.util.Date;

public class Result {
    public Integer AnswerId;
    public Integer SessionId;

    public Date dateOfResult;
    public Result(Integer AnswerId, Integer SessionId){
        this.AnswerId = AnswerId;
        this.SessionId = SessionId;
    }

    public void setDateOfResult(Date dateOfResult) {
        this.dateOfResult = dateOfResult;
    }

    public Integer getAnswerId() {
        return AnswerId;
    }

    public Integer getSessionId() {
        return SessionId;
    }

    public Date getDateOfResult() {
        return dateOfResult;
    }
}
