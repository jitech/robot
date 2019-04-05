package br.com.robot.robot;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.robot.entity.User;
import br.com.robot.repository.UserRepository;

@Controller
public class MainController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NotificationService notificationService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/someAction", method = RequestMethod.POST)
	public Object someAction(@RequestParam(value="text") String text) {

		try {
				if(text != null && !text.equals("")) {	  
					Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	  
			
					List<User> users = userRepository.findAll(authentication.getName());
			
					for(User user : users) {
						notificationService.notify(new Notification("<br/>"+authentication.getName() + ": " +text), user.getName());
					}
				}
   
				return HttpStatus.OK;
				
		}catch(Exception ex) {
			ex.printStackTrace();
			return HttpStatus.BAD_REQUEST;
		}
	}
}