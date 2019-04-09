package br.com.robot.robot;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.robot.entity.Publication;
import br.com.robot.entity.Talk;
import br.com.robot.entity.User;
import br.com.robot.repository.PublicationRepository;
import br.com.robot.repository.TalkRepository;
import br.com.robot.repository.UserRepository;

@RestController
public class MainController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TalkRepository talkRepository;
	
	@Autowired
	private PublicationRepository publicationRepository;
	
	@Autowired
    public Environment environment;
	
	@Autowired
	private NotificationService notificationService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/execute", method = RequestMethod.GET)
	public Object execute(@RequestParam(value="url") String url) {
		return new RestTemplate().getForObject(url, Object.class);
	}
	
	@RequestMapping(value = "/talk", method = RequestMethod.POST)
	public Object talk(@RequestParam(value="text") String text) {

		try {
				if(text != null && !text.equals("")) {	  
					Authentication authentication = SecurityContextHolder.getContext().getAuthentication();					
					
					List<User> users = userRepository.findAll(authentication.getName());
						
					notificationService.notify(new Notification(environment.getProperty("message.me").replace("USER", "Me").replace("MESSAGE", text)), authentication.getName());
					
					for(User user : users) {													
						notificationService.notify(new Notification(environment.getProperty("message.other").replace("USER", authentication.getName()).replace("MESSAGE", text)), user.getName());
					
						talkRepository.save(new Talk(text, userRepository.find(authentication.getName()), user));
					}
				}
   
				return HttpStatus.OK;
				
		}catch(Exception ex) {
			ex.printStackTrace();
			return HttpStatus.BAD_REQUEST;
		}
	}
	
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public Object publish(@RequestParam(value="title") String title, @RequestParam(value="text") String text) {

		try {
				if(title != null && !title.equals("") && text != null && !text.equals("")) {	  
					Authentication authentication = SecurityContextHolder.getContext().getAuthentication();					
					
					List<User> users = userRepository.findAll(authentication.getName());
						
					notificationService.notify(new Notification(environment.getProperty("message.me").replace("USER", "Me").replace("MESSAGE", text)), authentication.getName());
					
					for(User user : users) {													
						notificationService.notify(new Notification(environment.getProperty("message.other").replace("USER", authentication.getName()).replace("MESSAGE", text)), user.getName());			
						publicationRepository.save(new Publication(title, text, user, new Date()));
					}
				}
   
				return HttpStatus.OK;
				
		}catch(Exception ex) {
			ex.printStackTrace();
			return HttpStatus.BAD_REQUEST;
		}
	}
}