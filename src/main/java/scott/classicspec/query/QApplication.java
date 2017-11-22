package scott.classicspec.query;

import scott.barleydb.api.query.JoinType;
import scott.barleydb.api.query.QProperty;
import scott.barleydb.api.query.QueryObject;
import scott.classicspec.model.Application;
import scott.classicspec.query.QService;

/**
 * Generated from Entity Specification
 *
 * @author scott
 */
public class QApplication extends QueryObject<Application> {
  private static final long serialVersionUID = 1L;
  public QApplication() {
    super(Application.class);
  }

  public QApplication(QueryObject<?> parent) {
    super(Application.class, parent);
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

  public QService joinToServices() {
    QService services = new QService();
    addLeftOuterJoin(services, "services");
    return services;
  }

  public QService joinToServices(JoinType joinType) {
    QService services = new QService();
    addJoin(services, "services", joinType);
    return services;
  }

  public QService existsServices() {
    QService services = new QService(this);
    addExists(services, "services");
    return services;
  }
}