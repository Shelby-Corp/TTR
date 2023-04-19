package entities;

import java.util.Date;

public class Session extends Entity{

    public Integer studentId;
    public Integer typeTestingId;
    public Date dateOfSession;
    public Session(Integer id, Integer studentId, Integer typeTestingId){
        this.id = id;
        this.studentId = studentId;
        this.typeTestingId = typeTestingId;
    }

    public void setDateOfSession(Date dateOfSession) {
        this.dateOfSession = dateOfSession;
    }

    public Date getDateOfSession() {
        return dateOfSession;
    }

    public Integer getStudentId(){
        return studentId;
    }

    public Integer getTypeTestingId(){
        return typeTestingId;
    }
}
