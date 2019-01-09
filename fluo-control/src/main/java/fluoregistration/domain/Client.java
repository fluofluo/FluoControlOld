package fluoregistration.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "client")

public class Client {

	@Id
    String id;
	String clientName;
    String clientAddress;
    String clientCode;
    
	    public Client() {
	    }
    
    	public Client(String clientName, String clientAddress, String clientCode) {
		        this.clientName = clientName;
		        this.clientAddress = clientAddress;
		        this.clientCode = clientCode;
		}
    	
    
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	

	
}
