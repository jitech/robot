package br.com.robot.robot;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.robot.entity.User;
import br.com.robot.repository.UserRepository;

@RestController
public class MainController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NotificationService notificationService;

	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index");
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