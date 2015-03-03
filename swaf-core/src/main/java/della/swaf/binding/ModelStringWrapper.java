package della.swaf.binding;

import com.jgoodies.binding.beans.Model;

public class ModelStringWrapper extends Model {

	private String propertyValue;
	private final String bindProperty;

	public ModelStringWrapper(String bindProperty) {
		this.bindProperty = bindProperty;
	}

	protected void setModelProperty(String value) {
		Object oldValue = propertyValue;
		this.propertyValue = value;		
		firePropertyChange(bindProperty, oldValue, value);
	}


	protected String getModelProperty() {
		return propertyValue;
	}

}
