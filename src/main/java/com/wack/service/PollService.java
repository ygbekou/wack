package com.wack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wack.domain.PollSearchCriteria;
import com.wack.model.BaseEntity;
import com.wack.poll.PollDesc;
import com.wack.poll.PollQuestion;
import com.wack.poll.PollQuestionDesc;
import com.wack.poll.Vote;

@Service(value="pollService")
public interface PollService {
	
	List<PollQuestion> getPendingPollQuestions(Long pollId, Long userId);

	List<PollDesc> getPollDescs(PollSearchCriteria searchCriteria);
	
	List<PollQuestionDesc> getPollQuestionDescs(PollSearchCriteria searchCriteria);
	
	BaseEntity processVote(Vote vote) throws Exception;

}
