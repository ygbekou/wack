package com.wack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wack.dao.PollDao;
import com.wack.domain.PollSearchCriteria;
import com.wack.poll.PollDesc;
import com.wack.poll.PollQuestion;
import com.wack.poll.PollQuestionDesc;

@Service(value="pollService")
public class PollServiceImpl implements PollService  {

	@Autowired
	PollDao pollDao;
	
	@Autowired
	GenericService genericService;
	
	

	@Override
	public List<PollQuestion> getPendingPollQuestions(Long pollId, Long userId) {
		return pollDao.getPendingPollQuestions( pollId,  userId);
	}
	
	
	@Override
	public List<PollDesc> getPollDescs(PollSearchCriteria searchCriteria) {
		return pollDao.getPolls(searchCriteria);
	}
	
	@Override
	public List<PollQuestionDesc> getPollQuestionDescs(PollSearchCriteria searchCriteria) {
		return pollDao.getPollQuestions(searchCriteria);
	}
	
}
