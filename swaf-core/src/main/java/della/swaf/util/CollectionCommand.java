package della.swaf.util;

import java.util.Collection;

public interface CollectionCommand {

	Collection getCollection();

	Object runOn(Object singleElement);

}
