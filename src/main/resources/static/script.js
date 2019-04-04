function sendAction(event) {
    	  
    var text = document.getElementById('text').value; 
    	
    document.getElementById('text').value = "";
    	
    $("#notifications-area").append("<br/>Me: "+ text);
    	    	
    event.preventDefault();
    
    $.ajax({
          url: "/someAction",
          type: "POST",
          data: jQuery.param({text: text})
    });
    
    var objDiv = document.getElementById("notifications-area");
    document.getElementById("notifications-area").scrollTop = objDiv.scrollHeight
    
    return;
}      
      
   
$(document).ready(function() {
	$("#do-some-action").on("click", sendAction);        
});
      
      
function connect() {

	var socket = new SockJS('/ws');
    var stompClient = Stomp.over(socket);

    stompClient.connect({}, function(frame) {
        stompClient.subscribe('/user/queue/notify', function(notification) {
             notify(JSON.parse(notification.body).content);
        });
    });
          
    return;
}
        
function notify(message) {
	
	$("#notifications-area").append(message + "\n");
	
	var objDiv = document.getElementById("notifications-area");
    document.getElementById("notifications-area").scrollTop = objDiv.scrollHeight
	
	return;
}
        
$(document).ready(function() {        
	connect();        
});