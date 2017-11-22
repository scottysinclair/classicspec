package scott.classicspec.query;

import scott.barleydb.api.query.JoinType;
import scott.barleydb.api.query.QProperty;
import scott.barleydb.api.query.QueryObject;
import scott.classicspec.model.ClientServiceCalls;
import scott.classicspec.query.QClient;
import scott.classicspec.query.QService;
import java.math.BigDecimal;

/**
 * Generated from Entity Specification
 *
 * @author scott
 */
public class QClientServiceCalls extends QueryObject<ClientServiceCalls> {
  private static final long serialVersionUID = 1L;
  public QClientServiceCalls() {
    super(ClientServiceCalls.class);
  }

  public QClientServiceCalls(QueryObject<?> parent) {
    super(ClientServiceCalls.class, parent);
  }


  public QProperty<Long> id() {
    return new QProperty<Long>(this, "id");
  }

  public QProperty<Long> modifiedAt() {
    return new QProperty<Long>(this, "modifiedAt");
  }

  public QProperty<Long> clientId() {
    return new QProperty<Long>(this, "client");
  }

  public QClient joinToClient() {
    QClient client = new QClient();
    addLeftOuterJoin(client, "client");
    return client;
  }

  public QClient joinToClient(JoinType joinType) {
    QClient client = new QClient();
    addJoin(client, "client", joinType);
    return client;
  }

  public QClient existsClient() {
    QClient client = new QClient(this);
    addExists(client, "client");
    return client;
  }

  public QProperty<Long> serviceId() {
    return new QProperty<Long>(this, "service");
  }

  public QService joinToService() {
    QService service = new QService();
    addLeftOuterJoin(service, "service");
    return service;
  }

  public QService joinToService(JoinType joinType) {
    QService service = new QService();
    addJoin(service, "service", joinType);
    return service;
  }

  public QService existsService() {
    QService service = new QService(this);
    addExists(service, "service");
    return service;
  }

  public QProperty<BigDecimal> cost() {
    return new QProperty<BigDecimal>(this, "cost");
  }
}