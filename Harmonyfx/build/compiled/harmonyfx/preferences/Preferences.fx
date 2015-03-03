/*
 * Preferences.fx
 *
 * Created on 21 Jun 09, 9:50:52
 */

package harmonyfx.preferences;
import javafx.util.Properties;
import javafx.io.Storage;
/**
 * @author Muhammad Hakim A <hakimrie[at]gmail.com>
 */

public class Preferences extends Properties{
    public-init var source: String;


    public function put(key: String, value: Integer) {
        put(key, value.toString());
    }

    public function get(key: String, default: Integer) {
        var value = get(key);
        if (value != null) try {
          return Integer.valueOf(value)
        }
        catch (exception) {
        }
        default
    }


    public function put(key: String, value: Number) {
        put(key, value.toString())
    }

    public function get(key: String, default: Number) {
        var value = get(key);
        if (value != null) try {
          return Number.valueOf(value)
        }
        catch (exception) {
        }
        default
    }


    var storage: Storage;

    postinit {
    storage = Storage {
      source: source
    }
    if (storage.resource.readable) {
      load(storage.resource.openInputStream())
    }
    FX.addShutdownAction(store)
    }

    function store() {
    if (storage.resource.writable) {
      store(storage.resource.openOutputStream(true))
    }
    }
}
