package com.example.restapi2.servlet;

import com.example.restapi2.DB.*;
import com.example.restapi2.model.ContributionOfUserForEvent;
import com.example.restapi2.model.DebtsOfUserForEvent;
import com.example.restapi2.model.Event;
import com.example.restapi2.model.EventUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "ContributionOfUserForEventServlet")
public class ContributionOfUserForEventServlet extends HttpServlet {
    private ContributionOfUserForEventDB contributionForHolidayDB;
    private EventDB eventDB;
    private EventUserDB eventUserDB;
    private final List<ContributionOfUserForEvent> debtors = new ArrayList<>();
    private final List<ContributionOfUserForEvent> lenders = new ArrayList<>();
    private static double totalScore = 0;

    public void init() {
        contributionForHolidayDB = new ContributionOfUserForEventDB();
        eventDB = new EventDB();
        eventUserDB = new EventUserDB();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameOfEvent = request.getParameter("nameOfEvent");
        Double inputMoneyOfUser = Double.valueOf(request.getParameter("inputMoneyOfUser"));
        String forWhatInputMoneyUser = request.getParameter("forWhatInputMoneyUser");
        Date dateOfInputMoneyOfUser = new Date();
        dateOfInputMoneyOfUser.getTime();
        PrintWriter printWriter = response.getWriter();

        if (inputMoneyOfUser > 0 && forWhatInputMoneyUser.length() < 50) {
            Event event = new Event();
            event.setNameOfEvent(nameOfEvent);
            eventDB.addEvent(event);

            EventUser eventUser = new EventUser();
            eventUser.setUserId(event.getId());
            eventUser.setEventId(event.getId());
            eventUserDB.addUserAndEvent(eventUser);

            ContributionOfUserForEvent contributionForHoliday = new ContributionOfUserForEvent();
            contributionForHoliday.setEventUserId(eventUser.getUserId());
            contributionForHoliday.setInputMoneyOfUser(inputMoneyOfUser);
            contributionForHoliday.setDateOfInputMoneyOfUser(dateOfInputMoneyOfUser);
            contributionForHoliday.setForWhatInputMoneyUser(forWhatInputMoneyUser);
            contributionForHolidayDB.saveContributionOfUser(contributionForHoliday);

            printWriter.println("You successfully contributed " + contributionForHoliday.getInputMoneyOfUser()
                    + " money to the total score ");
            totalScore = totalScore + inputMoneyOfUser;
        } else {
            printWriter.println("Something get wrong");
            printWriter.println("You can't put to the score lower than 0 money");
            printWriter.println("Try again");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ContributionOfUserForEvent> contributionForHolidays = contributionForHolidayDB.getInformationAboutUsersContribution();
        List<ContributionOfUserForEvent> userInputMoneys = contributionForHolidayDB.getTotalScoreOfContribution();
        PrintWriter printWriter = resp.getWriter();
        if (totalScore != 0) {
            printWriter.println("The total score is " + userInputMoneys);
            for (ContributionOfUserForEvent contributionForHoliday : contributionForHolidays) {
                printWriter.println(contributionForHoliday.toString());
            }
        } else {
            printWriter.println("The total score is 0");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        calculateTotalDebtsOfUser();
        double delta = 0.0;
        for (ContributionOfUserForEvent debtor : debtors ) {
            for (ContributionOfUserForEvent lender : lenders) {
                delta = debtor.getDebts() + lender.getDebts();
                var lend = lender.getDebts();
                var debt = debtor.getDebts();
                    if (delta < 0) {
                        printWriter.println("User with id = " + debtor.getEventUserId() + " have to give "
                            + lend + " money to person with id = " + lender.getEventUserId());
                    debt = debtor.getDebts() + lender.getDebts();
                    if (debt < 0) {
                        debtor.setDebts(debt);
                        lender.setDebts(0.0);
                    }
                } else {
                    printWriter.println("User with id = " + debtor.getEventUserId() + " have to give "
                            + Math.abs(debt) + " money to person with id = " + lender.getEventUserId());
                    lend = debtor.getDebts() + lender.getDebts();
                    if (lend > 0) {
                        lender.setDebts(lend);
                        debtor.setDebts(0.0);
                    }
                }
            }
        }
    }

    private void calculateTotalDebtsOfUser() {
        List<ContributionOfUserForEvent> contributionForEvents = contributionForHolidayDB.getContributionsOfUsersForEvent();
        for (ContributionOfUserForEvent debtsOfUserForEvent : contributionForEvents) {
            double debtOfUser = debtsOfUserForEvent.getInputMoneyOfUser() - (totalScore / contributionForHolidayDB.getSizeUsersOfContribution());
            if (debtOfUser < 0) {
                debtsOfUserForEvent.setDebts(debtOfUser);
                debtors.add(debtsOfUserForEvent);
            } else {
                debtsOfUserForEvent.setDebts(debtOfUser);
                lenders.add(debtsOfUserForEvent);
            }
        }

    }
}
