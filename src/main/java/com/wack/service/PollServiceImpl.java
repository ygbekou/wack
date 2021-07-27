package com.wack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wack.dao.GenericDao;
import com.wack.dao.PollDao;
import com.wack.model.BaseEntity;
import com.wack.poll.PollQuestion;

@Service(value="pollService")
public class PollServiceImpl implements PollService  {

	@Autowired
	PollDao pollDao;

	@Autowired
	GenericService genericService;
	
	@Override
	public List<PollQuestion> getPendingPollQuestions(Long pollId, Long userId) {
		// TODO Auto-generated method stub
		return pollDao.getPendingPollQuestions( pollId,  userId);
	}
}
