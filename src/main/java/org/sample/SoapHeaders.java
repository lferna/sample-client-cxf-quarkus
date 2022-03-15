package org.sample;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

import org.apache.cxf.headers.Header;

import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class SoapHeaders {
	
	public List<Header> getHeaders() throws SOAPException {
		try {
			List<Header> soapHeaders = new ArrayList<Header>();
			SOAPFactory sf = SOAPFactory.newInstance();
			SOAPElement seqElement = sf.createElement(new QName("uri:org.apache.cxf","dummy"));
			SOAPElement textElement = sf.createElement(new QName("uri:org.apache.cxf","value")); 
			seqElement.addChildElement(textElement);
			Header dummyHeader = new Header(new QName("uri:org.apache.cxf","dummy"), seqElement);

			soapHeaders.add(dummyHeader);	
		return soapHeaders;
		}catch (SOAPException ex) {
			log.error("Error. "+ex.getMessage()+" - "+ex.getCause());
			throw ex;
		}
	}
}
