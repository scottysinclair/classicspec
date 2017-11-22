package scott.classicspec.model;

import scott.barleydb.api.core.entity.Entity;
import scott.barleydb.api.core.entity.ValueNode;
import scott.barleydb.api.core.proxy.AbstractCustomEntityProxy;
import scott.barleydb.api.core.entity.RefNode;
import scott.barleydb.api.core.proxy.RefNodeProxyHelper;

/**
 * Generated from Entity Specification
 *
 * @author scott
 */
public class Client extends AbstractCustomEntityProxy {
  private static final long serialVersionUID = 1L;

  private final ValueNode id;
  private final ValueNode modifiedAt;
  private final ValueNode name;
  private final ValueNode type;
  private final RefNodeProxyHelper billingAddress;

  public Client(Entity entity) {
    super(entity);
    id = entity.getChild("id", ValueNode.class, true);
    modifiedAt = entity.getChild("modifiedAt", ValueNode.class, true);
    name = entity.getChild("name", ValueNode.class, true);
    type = entity.getChild("type", ValueNode.class, true);
    billingAddress = new RefNodeProxyHelper(entity.getChild("billingAddress", RefNode.class, true));
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

  public String getName() {
    return name.getValue();
  }

  public void setName(String name) {
    this.name.setValue(name);
  }

  public scott.classicspec.model.ClientTyp getType() {
    return type.getValue();
  }

  public void setType(scott.classicspec.model.ClientTyp type) {
    this.type.setValue(type);
  }

  public BillingAddress getBillingAddress() {
    return super.getFromRefNode(billingAddress.refNode);
  }

  public void setBillingAddress(BillingAddress billingAddress) {
    setToRefNode(this.billingAddress.refNode, billingAddress);
  }
}
