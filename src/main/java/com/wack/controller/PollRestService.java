package com.wack.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.wack.poll.Poll;
import com.wack.poll.PollChoice;
import com.wack.poll.PollQuestion;
import com.wack.service.GenericService;
import com.wack.service.PollService;


@RestController
@RequestMapping("/service/poll")
public class PollRestService {

	@Autowired
	GenericService baseService;
	@Autowired
	PollService pollService;
	
	@Autowired
	ServletContext context;

	@RequestMapping(value = "/save", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Poll createPoll(@RequestBody Poll poll) {
		baseService.save(poll);
		return poll;
	}

	@RequestMapping(value = "/savePollQuestion", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody PollQuestion createPollQuestion(@RequestBody PollQuestion poll) {
		baseService.save(poll);
		return poll;
	}

/*	@RequestMapping(value = "/getPollQuestions", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody List<PollQuestion> getPollQuestions(@RequestBody Poll poll) {

		List<PollQuestion> retVal = new ArrayList<PollQuestion>();
		try {
			retVal = (List) baseService.loadByParentsIds(PollQuestion.class, "poll", poll.getId(), null, null);
			if (retVal != null) {
				Collections.sort(retVal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal == null ? new ArrayList<PollQuestion>() : retVal;
	}*/

 	@RequestMapping(value = "/getPendingPollQuestions/{pollId}/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<PollQuestion> getPendingPollQuestions(@PathVariable("pollId") Long pollId,
			@PathVariable("userId") Long userId ) {
		List<PollQuestion> retVal = new ArrayList<PollQuestion>();
		try {
			retVal = pollService.getPendingPollQuestions(pollId, userId);
			if (retVal != null) {
				Collections.sort(retVal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal == null ? new ArrayList<PollQuestion>() : retVal;
	}

	@RequestMapping(value = "/deletePollQuestion", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody String deletePollQuestion(@RequestBody PollQuestion poll) {
		try {
			System.out.println("Delete Poll:" + poll);
			baseService.delete(poll);
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erreur: " + e.getMessage();
		}
	}

	@RequestMapping(value = "/savePollChoice", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody PollChoice createPollChoice(@RequestBody PollChoice poll) {
		baseService.save(poll);
		return poll;
	}

/*	@RequestMapping(value = "/vote", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Vote vote(@RequestBody Vote vote) {
		return baseService.vote(vote);
	}*/

/*	@RequestMapping(value = "/getPollChoices", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody List<PollChoice> getPollChoices(@RequestBody PollQuestion poll) {

		List<PollChoice> retVal = new ArrayList<PollChoice>();
		try {
			retVal = (List) baseService.loadByParentsIds(PollChoice.class, "pollQuestion", poll.getId(), null, null);
			if (retVal != null) {
				Collections.sort(retVal);
				PollChoice winner = null;
				for (PollChoice pc : retVal) {
					if (winner == null || pc.getVoteCount() > winner.getVoteCount()) {
						winner = pc;
					}
				}
				if (winner != null)
					winner.setWinner(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal == null ? new ArrayList<PollChoice>() : retVal;
	}
*/
	@RequestMapping(value = "/deletePollChoice", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody String deletePollChoice(@RequestBody PollChoice poll) {
		System.out.println("Delete Poll:" + poll);
		try {
			baseService.delete(poll);
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erreur: " + e.getMessage();
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody String deletePoll(@RequestBody Poll poll) {
		System.out.println("Delete Poll:" + poll);
		try {
			baseService.delete(poll);
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erreur: " + e.getMessage();
		}
	}

/*	@RequestMapping(value = "/getAllActivePolls", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Poll> getAllActivePolls() {
		System.out.println("Poll list Requested - getPolls");
		List<Poll> polls = (List) baseService.findByColumn(Poll.class, "status", (short) 1);
		Collections.sort(polls);
		return polls;
	}

	@RequestMapping(value = "/getAllPolls", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Poll> getAllPolls() {
		System.out.println("Poll list Requested - getPolls");
		ArrayList<Poll> polls = (ArrayList) baseService.loadAll(Poll.class);
		Collections.sort(polls);
		return polls;
	}*/
}