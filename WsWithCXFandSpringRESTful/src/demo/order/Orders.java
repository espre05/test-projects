package demo.order;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

// START SNIPPET: bean
@XmlRootElement
public class Orders {
    private Collection<Order> orders;

    public Collection<Order> getOrder() {
        return orders;
    }

    public void setOrder(Collection<Order> o) {
        this.orders = o;
    }
}
