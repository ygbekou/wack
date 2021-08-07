package com.wack.dao;

import java.util.List;

import com.wack.domain.PollSearchCriteria;
import com.wack.poll.PollDesc;
import com.wack.poll.PollQuestion;
import com.wack.poll.PollQuestionDesc;

public interface PollDao {
	public List<PollQuestion> getPendingPollQuestions(Long pollId, Long userId);
	
	public List<PollDesc> getPolls(PollSearchCriteria searchCriteria);
	
	public List<PollQuestionDesc> getPollQuestions(PollSearchCriteria searchCriteria);

}
