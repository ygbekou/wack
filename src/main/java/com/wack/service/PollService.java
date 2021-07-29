package com.wack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wack.poll.PollQuestion;

@Service(value="pollService")
public interface PollService {
	public List<PollQuestion> getPendingPollQuestions(Long pollId, Long userId);

}
