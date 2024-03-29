package br.com.robot.robot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
  
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	public void notify(Notification notification, String username) throws Exception{
		this.messagingTemplate.convertAndSendToUser(username, "/queue/notify", notification);
	}
}