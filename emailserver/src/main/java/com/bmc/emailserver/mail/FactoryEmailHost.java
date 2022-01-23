package com.bmc.emailserver.mail;

public enum FactoryEmailHost {

	YAHOO("yahoo.com", new SendYahoo()),GOOGLE("google.com", new SendGmail()),WALLA("walla.com.il", new SendWalla());
	
	private String host;
	private AbstractSendMail sendMail;
	
	private FactoryEmailHost(String host, AbstractSendMail sendMail) {
		this.host = host;
		this.sendMail = sendMail;
	}
	
	public String getHost() {
		return this.host;
	}
	
	public AbstractSendMail getSendMail() {
		return this.sendMail;
	}
	
}
