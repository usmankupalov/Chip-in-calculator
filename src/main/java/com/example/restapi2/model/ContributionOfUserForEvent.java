package com.example.restapi2.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contribution_of_user_for_event")
public class ContributionOfUserForEvent extends DebtsOfUserForEvent{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "event_user_id")
    private Long eventUserId;

    @Column(name = "input_money")
    private double inputMoneyOfUser;

    @Column(name = "date_of_input_money")
    private Date dateOfInputMoneyOfUser;

    @Column(name = "for_what_input_money")
    private String forWhatInputMoneyUser;

    public ContributionOfUserForEvent() {}

    public ContributionOfUserForEvent(Long id, Long eventUserId, double inputMoneyOfUser, Date dateOfInputMoneyOfUser, String forWhatInputMoneyUser) {
        this.id = id;
        this.eventUserId = eventUserId;
        this.inputMoneyOfUser = inputMoneyOfUser;
        this.dateOfInputMoneyOfUser = dateOfInputMoneyOfUser;
        this.forWhatInputMoneyUser = forWhatInputMoneyUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventUserId() {
        return eventUserId;
    }

    public void setEventUserId(Long eventUserId) {
        this.eventUserId = eventUserId;
    }

    public double getInputMoneyOfUser() {
        return inputMoneyOfUser;
    }

    public void setInputMoneyOfUser(double inputMoneyOfUser) {
        this.inputMoneyOfUser = inputMoneyOfUser;
    }

    public Date getDateOfInputMoneyOfUser() {
        return dateOfInputMoneyOfUser;
    }

    public void setDateOfInputMoneyOfUser(Date dateOfInputMoneyOfUser) {
        this.dateOfInputMoneyOfUser = dateOfInputMoneyOfUser;
    }

    public String getForWhatInputMoneyUser() {
        return forWhatInputMoneyUser;
    }

    public void setForWhatInputMoneyUser(String forWhatInputMoneyUser) {
        this.forWhatInputMoneyUser = forWhatInputMoneyUser;
    }

    @Override
    public String toString() {
        return "ContributionOfUserForEvent{" +
                "user id=" + eventUserId +
                ", input Money Of Users=" + inputMoneyOfUser +
                ", date Of Input Money Of Users=" + dateOfInputMoneyOfUser +
                ", for What Input Money Users='" + forWhatInputMoneyUser + '\'' +
                '}';
    }
}
