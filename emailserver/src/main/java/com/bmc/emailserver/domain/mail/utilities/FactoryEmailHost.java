package com.bmc.emailserver.domain.mail.utilities;

import com.bmc.emailserver.domain.mail.AbstractdMail;
import com.bmc.emailserver.domain.mail.Gmail;
import com.bmc.emailserver.domain.mail.Walla;
import com.bmc.emailserver.domain.mail.Yahoo;

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
