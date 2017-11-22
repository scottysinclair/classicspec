package scott.classicspec.query;

import scott.barleydb.api.query.JoinType;
import scott.barleydb.api.query.QProperty;
import scott.barleydb.api.query.QueryObject;
import scott.classicspec.model.Service;
import scott.classicspec.query.QApplication;
import java.math.BigDecimal;

/**
 * Generated from Entity Specification
 *
 * @author scott
 */
public class QService extends QueryObject<Service> {
  private static final long serialVersionUID = 1L;
  public QService() {
    super(Service.class);
  }

  public QService(QueryObject<?> parent) {
    super(Service.class, parent);
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

  public QProperty<Long> applicationId() {
    return new QProperty<Long>(this, "application");
  }

  public QApplication joinToApplication() {
    QApplication application = new QApplication();
    addLeftOuterJoin(application, "application");
    return application;
  }

  public QApplication joinToApplication(JoinType joinType) {
    QApplication application = new QApplication();
    addJoin(application, "application", joinType);
    return application;
  }

  public QApplication existsApplication() {
    QApplication application = new QApplication(this);
    addExists(application, "application");
    return application;
  }

  public QProperty<BigDecimal> costPerCall() {
    return new QProperty<BigDecimal>(this, "costPerCall");
  }
}