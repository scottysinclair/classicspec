package scott.classicspec.model;

import scott.barleydb.api.core.entity.Entity;
import scott.barleydb.api.core.proxy.ProxyFactory;
import scott.barleydb.api.exception.model.ProxyCreationException;


public class ClassicspecProxyFactory implements ProxyFactory {

  private static final long serialVersionUID = 1L;

  @SuppressWarnings("unchecked")
  public <T> T newProxy(Entity entity) throws ProxyCreationException {
    if (entity.getEntityType().getInterfaceName().equals(Application.class.getName())) {
      return (T) new Application(entity);
    }
    if (entity.getEntityType().getInterfaceName().equals(BillingAddress.class.getName())) {
      return (T) new BillingAddress(entity);
    }
    if (entity.getEntityType().getInterfaceName().equals(Client.class.getName())) {
      return (T) new Client(entity);
    }
    if (entity.getEntityType().getInterfaceName().equals(ClientServiceCalls.class.getName())) {
      return (T) new ClientServiceCalls(entity);
    }
    if (entity.getEntityType().getInterfaceName().equals(Service.class.getName())) {
      return (T) new Service(entity);
    }
    return null;
  }
}
