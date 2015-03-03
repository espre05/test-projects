package demo.order;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "GetOrder")
public class GetOrder {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
