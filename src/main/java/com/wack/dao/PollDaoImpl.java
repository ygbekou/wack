package com.wack.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wack.domain.PollSearchCriteria;
import com.wack.model.Employee;
import com.wack.model.Position;
import com.wack.poll.Poll;
import com.wack.poll.PollDesc;
import com.wack.poll.PollQuestion;
import com.wack.poll.PollQuestionDesc;
import com.wack.poll.PollTypeDesc;
import com.wack.util.Utils;

@SuppressWarnings("unchecked")
@Repository
public class PollDaoImpl implements PollDao {

	@Autowired
	private EntityManager entityManager;

	public List<PollQuestion> getPendingPollQuestions(Long pollId, Long userId) {
		List<Object[]> objects = null;
		if (userId > 0) {
			String sqlQuery = "SELECT PQ.POLL_QUESTION_ID, PQ.DESCRIPTION,  PQ.RANK, P.POSITION_ID, P.NAME  "
					+ "FROM  POLL_QUESTION PQ LEFT OUTER JOIN POSITION P ON (P.POSITION_ID = PQ.POSITION_ID ) WHERE PQ.POLL_ID= :pollId  "
					+ "AND NOT EXISTS (SELECT 1 FROM VOTE V, POLL_CHOICE PC WHERE PC.POLL_CHOICE_ID = V.POLL_CHOICE_ID "
					+ "AND PC.POLL_QUESTION_ID=PQ.POLL_QUESTION_ID AND V.USER_ID = :userId)";

			objects = entityManager.createNativeQuery(sqlQuery).setParameter("userId", userId)
					.setParameter("pollId", pollId).getResultList();
		}else {
			String sqlQuery = "SELECT PQ.POLL_QUESTION_ID, PQ.DESCRIPTION,  PQ.RANK, P.POSITION_ID, P.NAME  "
					+ "FROM  POLL_QUESTION PQ LEFT OUTER JOIN POSITION P ON (P.POSITION_ID = PQ.POSITION_ID ) WHERE PQ.POLL_ID= :pollId  ";

			objects = entityManager.createNativeQuery(sqlQuery)
					.setParameter("pollId", pollId).getResultList();
		}

		List<PollQuestion> yss = new ArrayList<PollQuestion>();
		PollQuestion ys;
		for (Object[] obj : objects) {
			ys = new PollQuestion();
			ys.setId(new Long(obj[0].toString()));
			//ys.setDescription((obj[1] == null ? "" : obj[1].toString()));
			ys.setRank(new Integer(obj[2].toString()));
			if (obj[3] != null) {
				Position p = new Position();
				p.setId(new Long(obj[3].toString()));
				//p.setName((obj[4] == null ? "" : obj[4].toString()));
				ys.setPosition(p);
			}
			yss.add(ys);
		}
		return yss;
	}
	
	
	
	public List<PollDesc> getPolls(PollSearchCriteria searchCriteria) {
	    StringBuilder queryBuilder = new StringBuilder("SELECT p, pt, pd, ptd"
				+ "  FROM Poll as p, PollDesc as pd, PollType as pt, PollTypeDesc as ptd "
				+ "  WHERE p.pollType = pt AND ptd.pollType = pt AND pd.poll = p ");
	    
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();

		if (searchCriteria.getId() != null) {
			queryBuilder.append("AND p.id = :pId ");
		}

		if (searchCriteria.getLanguage() != null) {
			queryBuilder.append("AND ptd.language = :pLang ");
			queryBuilder.append("AND pd.language = :pLang ");
		}

		if (searchCriteria.getStatus() != null) {
			queryBuilder.append("AND p.status = :pStatus ");
		}

        Query query = entityManager.createQuery(queryBuilder.toString());
        
        
        if (searchCriteria.getId() != null) {
        	query.setParameter("pId", searchCriteria.getId());
		}

		if (searchCriteria.getLanguage() != null) {
			query.setParameter("pLang", searchCriteria.getLanguage());
		}

		if (searchCriteria.getStatus() != null) {
			query.setParameter("pStatus", searchCriteria.getStatus());
		}
		
        
        List<Object[]> list = query.getResultList();
		List<PollDesc> pollDescs = new ArrayList<>();

		for (Object[] objects : list) {
			PollDesc pd = (PollDesc)objects[2];
			PollTypeDesc ptd = (PollTypeDesc)objects[3];
			
			pd.setPollTypeDesc(ptd);
			pollDescs.add((PollDesc)objects[2]);
		}

		return pollDescs;

	}
	 
	
	public List<PollQuestionDesc> getPollQuestions(PollSearchCriteria searchCriteria) {
	    StringBuilder queryBuilder = new StringBuilder("SELECT pq, pqd, p, pd"
				+ "  FROM PollQuestion as pq, PollQuestionDesc as pqd, Poll as p, PollDesc as pd "
				+ "  WHERE pq.poll = p AND pqd.pollQuestion = pq AND pd.poll = p ");
	    
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();

		if (searchCriteria.getId() != null) {
			queryBuilder.append("AND pq.id = :pqId ");
		}
		if (searchCriteria.getPollId() != null) {
			queryBuilder.append("AND p.id = :pollId ");
		}

		if (searchCriteria.getLanguage() != null) {
			queryBuilder.append("AND pqd.language = :pLang ");
			queryBuilder.append("AND pd.language = :pLang ");
		}

		if (searchCriteria.getStatus() != null) {
			queryBuilder.append("AND p.status = :pStatus ");
		}

        Query query = entityManager.createQuery(queryBuilder.toString());
        
        
        if (searchCriteria.getId() != null) {
        	query.setParameter("pqId", searchCriteria.getId());
		}
        if (searchCriteria.getPollId() != null) {
        	query.setParameter("pollId", searchCriteria.getPollId());
        }
		if (searchCriteria.getLanguage() != null) {
			query.setParameter("pLang", searchCriteria.getLanguage());
		}

		if (searchCriteria.getStatus() != null) {
			query.setParameter("pStatus", searchCriteria.getStatus());
		}
		
        
        List<Object[]> list = query.getResultList();
		List<PollQuestionDesc> pollQuestionDescs = new ArrayList<>();

		for (Object[] objects : list) {
			PollQuestionDesc pqd = (PollQuestionDesc)objects[1];
			PollDesc pd = (PollDesc)objects[3];
			
			pqd.setPollDesc(pd);
			pollQuestionDescs.add(pqd);
		}

		return pollQuestionDescs;

	}
	
}
