package fluoregistration.domain;







import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "client")

public class Client {

	@Id
	String id;
	@NotNull
	String clientName;
	String clientAddressStreet;
	String clientAddressCity;
	String clientAddressPostalCode;
	String clientCode;
	String contactPersonName;
	String contactPersonNumber;
	String contactPersonEmail;

	public Client() {
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

	public String getClientAddressStreet() {
		return clientAddressStreet;
	}

	public void setClientAddressStreet(String clientAddressStreet) {
		this.clientAddressStreet = clientAddressStreet;
	}

	public String getClientAddressCity() {
		return clientAddressCity;
	}

	public void setClientAddressCity(String clientAddressCity) {
		this.clientAddressCity = clientAddressCity;
	}

	public String getClientAddressPostalCode() {
		return clientAddressPostalCode;
	}

	public void setClientAddressPostalCode(String clientAddressPostalCode) {
		this.clientAddressPostalCode = clientAddressPostalCode;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getContactPersonNumber() {
		return contactPersonNumber;
	}

	public void setContactPersonNumber(String contactPersonNumber) {
		this.contactPersonNumber = contactPersonNumber;
	}

	public String getContactPersonEmail() {
		return contactPersonEmail;
	}

	public void setContactPersonEmail(String contactPersonEmail) {
		this.contactPersonEmail = contactPersonEmail;
	}  	


}
