package com.qkcare.controller;


import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/service/fileUploader")
public class FileUploaderRestService {

	@Autowired
	ServletContext context;

	@CrossOrigin
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String uploadFile(@RequestPart("file") MultipartFile file, @RequestPart("id") String id) {
	
		return "Success";
	}

	@RequestMapping(value = "/load", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String uploadFil2e(@RequestParam("file") MultipartFile file,
			@PathVariable("entity") String entity) {

		return "";
	}
}