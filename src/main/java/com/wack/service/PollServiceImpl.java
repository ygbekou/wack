package com.wack.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wack.dao.PollDao;
import com.wack.domain.PollSearchCriteria;
import com.wack.model.BaseEntity;
import com.wack.model.authorization.Permission;
import com.wack.model.authorization.Role;
import com.wack.model.stock.Material;
import com.wack.model.stock.Quote;
import com.wack.poll.PollDesc;
import com.wack.poll.PollQuestion;
import com.wack.poll.PollQuestionDesc;
import com.wack.poll.Vote;

@Service(value="pollService")
public class PollServiceImpl implements PollService  {

	private static final Logger LOGGER = Logger.getLogger(PollServiceImpl.class);
	
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
	
	
	@Transactional
	public BaseEntity processVote(Vote vote) throws Exception {		
		Vote vt = null;
		try {
			int deleted = deleteExistingVotes(vote.getPollChoice().getId(), vote.getUser().getId());
			
			vt = (Vote) genericService.save(vote);
			
		} catch (Exception ex) {
			vt = new Vote();
			vt.setErrors(Arrays.asList(ex.getMessage()));
		}
		
		return vt;
		
	}
	
	
	private int deleteExistingVotes(Long pollChoiceId, Long userId) {
		
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		paramTupleList.add(Quartet.with("V.USER_ID = ", "userId", userId + "", "Long"));
		paramTupleList.add(Quartet.with("V.POLL_CHOICE_ID = ", "pollChoiceId", pollChoiceId + "", "Long"));
		
		String queryStr =  "DELETE FROM VOTE WHERE 1 = 1 AND USER_ID = :userId AND POLL_CHOICE_ID IN "
				+ "(SELECT POLL_CHOICE_ID FROM POLL_CHOICE WHERE POLL_QUESTION_ID "
				+ " = (SELECT POLL_QUESTION_ID FROM POLL_CHOICE WHERE POLL_CHOICE_ID = :pollChoiceId ))";
		
		Integer totalDeleted = genericService.deleteNativeByCriteria(queryStr, paramTupleList);
		
		LOGGER.info(String.format("Number of votes deleted %d ", totalDeleted));
		
		return totalDeleted;
	}
	
}
