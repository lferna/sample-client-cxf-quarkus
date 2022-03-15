package org.sample;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.soap.SOAPException;

import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.headers.Header;
import org.tempuri.NumberConversionSoapType;

import io.quarkiverse.cxf.annotation.CXFClient;


@Path("/sample")
public class SampleResource {

	@Inject 
	@CXFClient("sample-client")
	NumberConversionSoapType sampleClient;
	
	@Inject
	SoapHeaders soapHeaders;
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sample() throws SOAPException {
		ClientProxy.getClient(sampleClient).getRequestContext().put(Header.HEADER_LIST,
				soapHeaders.getHeaders());		
		return sampleClient.numberToDollars(new BigDecimal(100));
        
    }
	
}