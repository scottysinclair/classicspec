package scott.classicspec.query;

import scott.barleydb.api.query.JoinType;
import scott.barleydb.api.query.QProperty;
import scott.barleydb.api.query.QueryObject;
import scott.classicspec.model.Client;
import scott.classicspec.query.QBillingAddress;

/**
 * Generated from Entity Specification
 *
 * @author scott
 */
public class QClient extends QueryObject<Client> {
  private static final long serialVersionUID = 1L;
  public QClient() {
    super(Client.class);
  }

  public QClient(QueryObject<?> parent) {
    super(Client.class, parent);
  }


  public QProperty<Long> id() {
    return new QProperty<Long>(this, "id");
  }

  public QProperty<Long> modifiedAt() {
    return new QProperty<Long>(this, "modifiedAt");
  }

  public QProperty<String> name() {
    return new QProperty<String>(this, "name");
  }

  public QProperty<scott.classicspec.model.ClientTyp> type() {
    return new QProperty<scott.classicspec.model.ClientTyp>(this, "type");
  }

  public QProperty<Long> billingAddressId() {
    return new QProperty<Long>(this, "billingAddress");
  }

  public QBillingAddress joinToBillingAddress() {
    QBillingAddress billingAddress = new QBillingAddress();
    addLeftOuterJoin(billingAddress, "billingAddress");
    return billingAddress;
  }

  public QBillingAddress joinToBillingAddress(JoinType joinType) {
    QBillingAddress billingAddress = new QBillingAddress();
    addJoin(billingAddress, "billingAddress", joinType);
    return billingAddress;
  }

  public QBillingAddress existsBillingAddress() {
    QBillingAddress billingAddress = new QBillingAddress(this);
    addExists(billingAddress, "billingAddress");
    return billingAddress;
  }
}