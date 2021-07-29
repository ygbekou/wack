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
	PollService pollService;
	
	@Autowired
	ServletContext context;
/*
 	@RequestMapping(value = "/vote", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Vote vote(@RequestBody Vote vote) {
		return baseService.vote(vote);
	} */
 	
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
	}}