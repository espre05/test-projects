package della.swaf.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ImageCache {
	
	private Map map;
	private String defaultImageKey;

	public ImageCache() {
		this.map = new HashMap();
	}

	public boolean containsKey(String key) {
		return map.containsKey(key);
	}

	public Image get(String key) {
		return (Image) map.get(key);
	}

	public void put(String key, Image image) {
		map.put(key, image);
	}

	public int size() {
		return map.size();
	}

	public Set keySet() {
		return map.keySet();
	}

	public Image getDefault() {
		return get(defaultImageKey);
	}
	
	public void setDefaultImageKey(String defaultImageKey, BufferedImage image) {
		this.defaultImageKey = defaultImageKey;
		put(defaultImageKey, image);
	}

}
