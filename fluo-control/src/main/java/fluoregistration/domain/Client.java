/*package fluoregistration.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="client")
	public class Client {

		@Id
		private String id;
		@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
		private String clientname;
		private String clientaddress;
		private String clientcode;
		private String clientnumber;
		private boolean enabled; 
		
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getClientname() {
			return clientname;
		}

		public void setClientname(String clientname) {
			this.clientname = clientname;
		}

		public String getClientaddress() {
			return clientaddress;
		}

		public void setClientaddress(String clientaddress) {
			this.clientaddress = clientaddress;
		}

		public String getClientcode() {
			return clientcode;
		}

		public void setClientcode(String clientcode) {
			this.clientcode = clientcode;
		}

		public String getClientnumber() {
			return clientnumber;
		}

		public void setClientnumber(String clientnumber) {
			this.clientnumber = clientnumber;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
}
		*/