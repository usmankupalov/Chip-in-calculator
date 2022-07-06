package com.example.restapi2.Service;

import com.example.restapi2.model.ContributionOfUserForEvent;

import java.util.List;

public interface ContributionForHolidayService {
    void saveContributionOfUser(ContributionOfUserForEvent contributionForHoliday);
    List<ContributionOfUserForEvent> getContributionsOfUsersForEvent();
    List<ContributionOfUserForEvent> getTotalScoreOfContribution();
    Integer getSizeUsersOfContribution();

}
