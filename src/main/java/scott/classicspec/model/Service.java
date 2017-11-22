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
public class Service extends AbstractCustomEntityProxy {
  private static final long serialVersionUID = 1L;

  private final ValueNode id;
  private final ValueNode modifiedAt;
  private final ValueNode name;
  private final RefNodeProxyHelper application;
  private final ValueNode costPerCall;

  public Service(Entity entity) {
    super(entity);
    id = entity.getChild("id", ValueNode.class, true);
    modifiedAt = entity.getChild("modifiedAt", ValueNode.class, true);
    name = entity.getChild("name", ValueNode.class, true);
    application = new RefNodeProxyHelper(entity.getChild("application", RefNode.class, true));
    costPerCall = entity.getChild("costPerCall", ValueNode.class, true);
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

  public Application getApplication() {
    return super.getFromRefNode(application.refNode);
  }

  public void setApplication(Application application) {
    setToRefNode(this.application.refNode, application);
  }

  public BigDecimal getCostPerCall() {
    return costPerCall.getValue();
  }

  public void setCostPerCall(BigDecimal costPerCall) {
    this.costPerCall.setValue(costPerCall);
  }
}
