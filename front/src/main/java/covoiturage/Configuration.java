package covoiturage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Configuration {

	private final Logger log = Logger.getLogger(Configuration.class);
	private final Properties props = new Properties();

	public Configuration() {
		InputStream resources = this.getClass().getClassLoader().getResourceAsStream("covoiturage.properties");
		try {
			props.load(resources);
		} catch (IOException e) {
			log.error("Impossible de charger le fichier de propriété", e);
		}
	}

	public String getString(String key) {
		return props.getProperty(key);
	}

	public int getInt(String key) {
		return Integer.parseInt(props.getProperty(key));
	}
}
