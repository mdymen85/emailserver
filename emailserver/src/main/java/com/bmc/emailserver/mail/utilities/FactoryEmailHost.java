package com.bmc.emailserver.mail.utilities;

import com.bmc.emailserver.mail.AbstractdMail;
import com.bmc.emailserver.mail.Gmail;
import com.bmc.emailserver.mail.Walla;
import com.bmc.emailserver.mail.Yahoo;

public enum FactoryEmailHost {

	YAHOO("yahoo.com", new Yahoo()),GOOGLE("gmail.com", new Gmail()),WALLA("walla.com.il", new Walla());
	
	private String host;
	private AbstractdMail sendMail;
	
	private FactoryEmailHost(String host, AbstractdMail sendMail) {
		this.host = host;
		this.sendMail = sendMail;
	}
	
	public String getHost() {
		return this.host;
	}
	
	public AbstractdMail getSendMail() {
		return this.sendMail;
	}
	
}
