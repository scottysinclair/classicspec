package scott.classicspec;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import scott.barleydb.api.core.Environment;
import scott.barleydb.api.core.entity.EntityContext;
import scott.barleydb.api.dto.DtoConverter;
import scott.barleydb.api.exception.execution.SortServiceProviderException;
import scott.barleydb.api.exception.execution.persist.SortPersistException;
import scott.barleydb.api.exception.execution.query.BarleyDBQueryException;
import scott.barleydb.api.persist.PersistRequest;
import scott.barleydb.api.stream.EntityStreamException;
import scott.barleydb.api.stream.ObjectInputStream;
import scott.barleydb.bootstrap.EnvironmentDef;
import scott.classicspec.dto.ApplicationDto;
import scott.classicspec.dto.BillingAddressDto;
import scott.classicspec.dto.ClientDto;
import scott.classicspec.dto.ServiceDto;
import scott.classicspec.model.Application;
import scott.classicspec.model.BillingAddress;
import scott.classicspec.model.Client;
import scott.classicspec.model.ClientTyp;
import scott.classicspec.model.Service;
import scott.classicspec.query.QApplication;

public class ClassicSpecTest {

  private Environment env;

  @Before
  public void beforeClass() throws Exception {
    env = EnvironmentDef.build()
    .withDataSource()
        .withDriver("org.postgresql.xa.PGXADataSource")
        .withUser("test_user")
        .withPassword("password")
        .withUrl("jdbc:postgresql://172.18.0.3:5432/test_db")
        .end()
     .withSpecs(ClassicSpec.class)
     .withDroppingSchema(true)
     .withSchemaCreation(true)
     .create();

  }


  @Test
  public void testSaveDataUsingEntitiesDirectly() throws SortServiceProviderException, SortPersistException {

    EntityContext ctx = new ClassicspecEntityContext(env);

    ctx.setAutocommit(false);

    /*
     * create client and billing address application models.
     */
    Client client = ctx.newModel(Client.class);
    client.setName("John");
    client.setType(ClientTyp.INTERNAL);

    BillingAddress billingAddress = ctx.newModel(BillingAddress.class);
    billingAddress.setIban("AT1000100002123");

    client.setBillingAddress(billingAddress);

    /*
     * save the client and it's billing address
     */
    ctx.persist(new PersistRequest().save(client));

    /*
     * create application and service application models.
     */
    Application app = ctx.newModel(Application.class);
    app.setName("Retail Structured Products");

    Service s1 = ctx.newModel(Service.class);
    s1.setApplication(app);
    s1.setName("Service 1");
    s1.setCostPerCall(new BigDecimal("13.01"));
    app.getServices().add(s1);

    Service s2 = ctx.newModel(Service.class);
    s2.setApplication(app);
    s2.setName("Service 2");
    s2.setCostPerCall(new BigDecimal("13.02"));
    app.getServices().add(s2);

    //save the application and it's services
    ctx.persist(new PersistRequest().save(app));

    ctx.commit();
  }

  @Test
  public void testSaveDataUsingDtosAndDaoLayer() throws SortServiceProviderException, SortPersistException, BarleyDBQueryException {

    TestDao dao = new TestDao(env);

    /*
     * create client and billing address application models.
     */
    ClientDto client = new ClientDto();
    client.setName("John");
    client.setType(ClientTyp.INTERNAL);

    BillingAddressDto billingAddress = new BillingAddressDto();
    billingAddress.setIban("AT1000100002123");

    client.setBillingAddress(billingAddress);

    dao.save(client);

    /*
     * create application and service application models.
     */
    ApplicationDto app = new ApplicationDto();
    app.setName("Retail Structured Products");
    app.getServices().setFetched(true);

    ServiceDto s1 = new ServiceDto();
    s1.setApplication(app);
    s1.setName("Service 1");
    s1.setCostPerCall(new BigDecimal("13.01"));
    app.getServices().add(s1);

    ServiceDto s2 = new ServiceDto();
    s2.setApplication(app);
    s2.setName("Service 2");
    s2.setCostPerCall(new BigDecimal("13.02"));
    app.getServices().add(s2);

    dao.save(app);
  }

  @Test
  public void testLoadingUsingDtosAndServices() throws SortServiceProviderException, SortPersistException, BarleyDBQueryException {
     testSaveDataUsingDtosAndDaoLayer();
     System.out.println("----------------------------------------------------------------------------------");

     /*
      * DTOS and daos can be used to save and load data without having to deal with entities
      */
     TestDao dao = new TestDao(env);

     for (ApplicationDto app: dao.loadApplicationsAndServices()) {
       for (ServiceDto srv: app.getServices()) {
         System.out.println(app.getName() + " --> " + srv.getName() + " $" + srv.getCostPerCall());
       }
     }

  }

  @Test
  public void testStreamingObjectGraph() throws SortServiceProviderException, BarleyDBQueryException, EntityStreamException, SortPersistException {
    testSaveDataUsingDtosAndDaoLayer();
    System.out.println("----------------------------------------------------------------------------------");

    /*
     * Directly using the EntityContext allows for advanced behaviours aka - large data streaming.
     */
    EntityContext ctx = new ClassicspecEntityContext(env);

    /*
     * query for all applications joining to the services table so that each application has it's services relation loaded.
     */
    QApplication query = new QApplication();
    query.joinToServices();

    try ( ObjectInputStream<Application> inA = ctx.streamObjectQuery( query ); ) {
        Application app = null;
        /*
         * calling read will load the next object graph from the result-set of the query.
         */
        while((app = inA.read()) != null) {
          /*
           * access the services which were fetched as part of the above query.
           * if the above query did not perform a join from app  -> services then the app.getServices() call would perform a lazy load.
           */
           for (Service srv: app.getServices()) {
              System.out.println(app.getName() + " --> " + srv.getName() + " $" + srv.getCostPerCall());
          }
        }
    }
  }



  @Test
  public void testNestedStreaming() throws SortServiceProviderException, BarleyDBQueryException, EntityStreamException, SortPersistException {
    testSaveDataUsingDtosAndDaoLayer();
    System.out.println("----------------------------------------------------------------------------------");
    EntityContext ctx = new ClassicspecEntityContext(env);

    /*
     * query for all applications and stream through the results.
     */
    try ( ObjectInputStream<Application> inA = ctx.streamObjectQuery(new QApplication()); ) {
        Application app = null;
        /*
         * calling read will load the next object graph from the result-set of the query.
         */
        while((app = inA.read()) != null) {
          /*
           * query through the application's services and stream through the results (the same connection will execute a new query).
           */
          try ( ObjectInputStream<Service> inS = app.streamServices(); ) {
            Service srv = null;
            /*
             * calling read will load the next object graph from the result-set of the query.
             */
            while((srv = inS.read()) != null) {
              System.out.println(app.getName() + " --> " + srv.getName() + " $" + srv.getCostPerCall());
            }
          }
        }
    }
  }

}


/**
 * A simple example dao class for loading and saving DTO data.
 * @author scott
 *
 */
class TestDao {

  private final Environment env;

  public TestDao(Environment env) {
    this.env = env;
  }

  public List<ApplicationDto> loadApplicationsAndServices() throws SortServiceProviderException, BarleyDBQueryException {
    /*
     * create a ctx
     */
    EntityContext ctx = new ClassicspecEntityContext(env);
    ctx.setAllowGarbageCollection(false);

    /*
     * define the query which loads applications and their services.
     */
    QApplication qApp = new QApplication();
    qApp.joinToServices();

    /*
     * execute it
     */
    List<Application> applications = ctx.performQuery(qApp).getList();

    /*
     * convert all entities in the CTX to DTOs
     */
    DtoConverter conv = new DtoConverter(ctx);
    conv.convertToDtos();
    return conv.getDtos(applications, ApplicationDto.class);
  }

  public void save(ClientDto client) throws SortServiceProviderException, BarleyDBQueryException, SortPersistException {
    /*
     * create a ctx
     */
    EntityContext ctx = new ClassicspecEntityContext(env);
    ctx.setAutocommit(false);
    ctx.setAllowGarbageCollection(false);

    /*
     * convert the DTOS to entities which exist in the ctx.
     */
    DtoConverter conv = new DtoConverter(ctx);
    conv.importDtos(client);

    /*
     * save and commit the client (owned billing address is automatically included (OWNING RELATION).
     */
    Client clientEntity = conv.getModel(client);
    ctx.persist(new PersistRequest().save(clientEntity));
    ctx.commit();

    /*
     * apply the changes back to the same DTOs
     */
    conv.convertToDtos();
  }


  public void save(ApplicationDto app) throws SortServiceProviderException, SortPersistException, BarleyDBQueryException {
    EntityContext ctx = new ClassicspecEntityContext(env);
    ctx.setAutocommit(false);
    ctx.setAllowGarbageCollection(false);

    DtoConverter conv = new DtoConverter(ctx);
    conv.importDtos(app);

    //save the app and it's services
    Application appEntity = conv.getModel(app);
    ctx.persist(new PersistRequest().save(appEntity));
    ctx.commit();

    //convert the result of the persist back to the original DTOS
    conv.convertToDtos();
  }

}
