package scott.classicspec.query;

import scott.barleydb.api.query.QProperty;
import scott.barleydb.api.query.QueryObject;
import scott.classicspec.model.BillingAddress;

/**
 * Generated from Entity Specification
 *
 * @author scott
 */
public class QBillingAddress extends QueryObject<BillingAddress> {
  private static final long serialVersionUID = 1L;
  public QBillingAddress() {
    super(BillingAddress.class);
  }

  public QBillingAddress(QueryObject<?> parent) {
    super(BillingAddress.class, parent);
  }


  public QProperty<Long> id() {
    return new QProperty<Long>(this, "id");
  }

  public QProperty<Long> modifiedAt() {
    return new QProperty<Long>(this, "modifiedAt");
  }

  public QProperty<String> iban() {
    return new QProperty<String>(this, "iban");
  }
}