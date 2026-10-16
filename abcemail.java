interface NotificationService {
    void sendNotification(String message);
}
class EmailNotification implements NotificationService {
    public void sendNotification(String message){
    System.out.println("Email: " + message);
    }
}

public class abcemail {
public static void main(String[] args) {

SMSNotification sms = new SMSNotification();
 sms.sendNotification("You got 1cr");
   
EmailNotification email = new EmailNotification();
 email.sendNotification("You got bankrupted");
  }
}