package emailverify;

import user.User;

public interface EmailVerify{
	//send email
	boolean sendEmail(User user);
	//generate verification code
	String getRandom();
}