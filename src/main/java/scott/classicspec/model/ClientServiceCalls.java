package scott.classicspec.model;

import scott.barleydb.api.core.entity.Entity;
import scott.barleydb.api.core.entity.ValueNode;
import scott.barleydb.api.core.proxy.AbstractCustomEntityProxy;
import scott.barleydb.api.core.entity.RefNode;
import scott.barleydb.api.core.proxy.RefNodeProxyHelper;
import java.math.BigDecimal;

/**
 * Generated from Entity Specification
 *
 * @author scott
 */
public class ClientServiceCalls extends AbstractCustomEntityProxy {
  private static final long serialVersionUID = 1L;

  private final ValueNode id;
  private final ValueNode modifiedAt;
  private final RefNodeProxyHelper client;
  private final RefNodeProxyHelper service;
  private final ValueNode cost;

  public ClientServiceCalls(Entity entity) {
    super(entity);
    id = entity.getChild("id", ValueNode.class, true);
    modifiedAt = entity.getChild("modifiedAt", ValueNode.class, true);
    client = new RefNodeProxyHelper(entity.getChild("client", RefNode.class, true));
    service = new RefNodeProxyHelper(entity.getChild("service", RefNode.class, true));
    cost = entity.getChild("cost", ValueNode.class, true);
  }

  public Long getId() {
    return id.getValue();
  }

  public Long getModifiedAt() {
    return modifiedAt.getValue();
  }

  public void setModifiedAt(Long modifiedAt) {
    this.modifiedAt.setValue(modifiedAt);
  }

  public Client getClient() {
    return super.getFromRefNode(client.refNode);
  }

  public void setClient(Client client) {
    setToRefNode(this.client.refNode, client);
  }

  public Service getService() {
    return super.getFromRefNode(service.refNode);
  }

  public void setService(Service service) {
    setToRefNode(this.service.refNode, service);
  }

  public BigDecimal getCost() {
    return cost.getValue();
  }

  public void setCost(BigDecimal cost) {
    this.cost.setValue(cost);
  }
}
