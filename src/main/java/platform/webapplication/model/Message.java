package platform.webapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer from_user;
    private Integer to_user;
    private Date date;
    private String  text;

    public Message() {
    }

    public Message(Integer id, Integer fromUser, Integer toUser, Date date, String text) {
        this.id = id;
        this.from_user = fromUser;
        this.to_user = toUser;
        this.date = date;
        this.text = text;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getFrom_user() {
        return this.from_user;
    }

    public Integer getTo_user() {
        return this.to_user;
    }

    public Date getDate() {
        return this.date;
    }

    public String getText() {
        return this.text;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFrom_user(Integer fromUser) {
        this.from_user = fromUser;
    }

    public void setTo_user(Integer toUser) {
        this.to_user = toUser;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }
}
