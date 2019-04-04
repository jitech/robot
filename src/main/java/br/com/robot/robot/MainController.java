package br.com.robot.robot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

  @Autowired
  private NotificationService notificationService;

  @RequestMapping("/")
  public String index() {
    return "index";
  }

  @RequestMapping(value = "/someAction", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> someAction(@RequestParam(value="from") String from, @RequestParam(value="text") String text) {

	  if(text != null && !text.equals("")) {	  
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	  
		  notificationService.notify(new Notification(authentication.getName() + ": " +text), from);
	  }
   
	  return new ResponseEntity<>(HttpStatus.OK);
  }

}
