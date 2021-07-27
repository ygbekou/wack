package com.wack.dao;

import java.util.List;

import com.wack.poll.PollQuestion;

public interface PollDao {
	public List<PollQuestion> getPendingPollQuestions(Long pollId, Long userId);

}
