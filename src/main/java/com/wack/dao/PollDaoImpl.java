package com.wack.dao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.wack.model.Position;
import com.wack.poll.PollQuestion;

@SuppressWarnings("unchecked")
@Repository
public class PollDaoImpl implements PollDao {

	@Autowired
	private EntityManager entityManager;

	public List<PollQuestion> getPendingPollQuestions(Long pollId, Long userId) {
		String sqlQuery = "SELECT PQ.POLL_QUESTION_ID, PQ.DESCRIPTION,  PQ.RANK, P.POSITION_ID, P.NAME  "
				+ "FROM  POLL_QUESTION PQ LEFT OUTER JOIN POSITION P ON (P.POSITION_ID = PQ.POSITION_ID ) WHERE PQ.POLL_ID= :pollId  "
				+ "AND NOT EXISTS (SELECT 1 FROM VOTE V, POLL_CHOICE PC WHERE PC.POLL_CHOICE_ID = V.POLL_CHOICE_ID "
				+ "AND PC.POLL_QUESTION_ID=PQ.POLL_QUESTION_ID AND V.USER_ID = :userId)";

		List<Object[]> objects = entityManager.createNativeQuery(sqlQuery).setParameter("userId", userId)
				.setParameter("pollId", pollId).getResultList();

		List<PollQuestion> yss = new ArrayList<PollQuestion>();
		PollQuestion ys;
		for (Object[] obj : objects) {
			ys = new PollQuestion();
			ys.setId(new Long(obj[0].toString()));
			ys.setDescription((obj[1] == null ? "" : obj[1].toString()));
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
}
