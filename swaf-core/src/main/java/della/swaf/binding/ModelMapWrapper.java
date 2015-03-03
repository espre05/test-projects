package della.swaf.binding;

import java.util.HashMap;
import java.util.Map;

import com.jgoodies.binding.beans.Model;

/**
 * 
 * This class minimize the effort in writing Model for JGoodies Binding.
 * Extending Model in this way enables the bean-to-gui communication gui-to-bean
 * communication is enabled for free in creating components via JGoodies Binding
 * BasicComponentFactory (that is, using JGoodies Adapters).
 * 
 * A implementation of this class must provide method for properties following
 * the java bean conventions. It is mandatory that all the properties were
 * initialized before creating the gui component (via BasicComponentFactory)
 * 
 * Properties are backed up directly by a java.util.Map implementation. Take a
 * look ad ModelItemWrapper for a more indipendent Wrapper that belongs just to
 * Item class so it is indipendent by implementation mappings. 
 * 
 * TODO: it would be nice have a way to auto-generate beans that extends this
 * class
 * 
 * @author Daniele
 * 
 */
public abstract class ModelMapWrapper extends Model {

	private final Map props;

	public ModelMapWrapper() {
		props = new HashMap();
	}

	public Object getProperty(String propertyName) {
		return props.get(propertyName);
	}

	public void putProperty(String propertyName, Object oldValue, Object newValue) {
		props.put(propertyName, newValue);
		firePropertyChange(propertyName, oldValue, newValue);
	}

	protected String getStringProperty(String propertyName) {
		return (String) getProperty(propertyName);
	}

	protected boolean getBooleanProperty(String propertyname) {
		return (Boolean) getProperty(propertyname);
	}

	protected double getDoubleProperty(String propertyname) {
		return (Double) getProperty(propertyname);
	}

}
