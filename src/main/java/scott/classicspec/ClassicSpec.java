package scott.classicspec;

import static scott.barleydb.api.specification.CoreSpec.*;

import scott.barleydb.api.core.types.JdbcType;
import scott.barleydb.api.specification.NodeSpec;
import scott.barleydb.bootstrap.GenerateModels;
import scott.barleydb.build.specification.staticspec.CommonDefaultsPlatformSpec;
import scott.barleydb.build.specification.staticspec.Entity;
import scott.barleydb.build.specification.staticspec.Enumeration;

public class ClassicSpec extends CommonDefaultsPlatformSpec {

  /**
   * generate the query and model classes for the spec.
   * @param args
   */
  public static void main(String args[]) {
      GenerateModels.execute(ClassicSpec.class);
  }

  /**
   * define the spec with it's namespace
   */
  public ClassicSpec() {
     super("scott.classicspec");
  }


  /**
   * Define an enumeration callled ClientType
   * @author scott
   *
   */
  @Enumeration(JdbcType.INT)
  public static class ClientTyp {
      public static int INTERNAL = 1;
      public static int EXTERNAL = 2;
  }


  /**
   * common definition for entities
   * @author scott
   *
   */
  public interface StandardEntity {

     public static NodeSpec id = longPrimaryKey();

     public static NodeSpec modifiedAt = optimisticLock();

  }

  @Entity("CLS_CLIENT")
  public static class Client implements StandardEntity {

    public static NodeSpec name = name();

    public static NodeSpec type = mandatoryEnum(ClientTyp.class);

    public static NodeSpec billingAddress = mandatoryOwns(BillingAddress.class);

  }

  @Entity("CLS_BILLING_ADDRESS")
  public static class BillingAddress implements StandardEntity {

    public static NodeSpec iban = mandatoryChar(40);

  }

  @Entity("CLS_SERVICE")
  public static class Service implements StandardEntity {

    public static NodeSpec name = name();

    public static NodeSpec application = mandatoryRefersTo(Application.class);

    public static NodeSpec costPerCall = mandatoryBigDecimal(9,2);

  }

  @Entity("CLS_APPLICATION")
  public static class Application implements StandardEntity {

    public static NodeSpec name = name();

    public static NodeSpec services = ownsMany(Service.class, Service.application);

  }

  @Entity("CLS_CLIENT_SERVICE_CALLS")
  public static class ClientServiceCalls implements StandardEntity {

    public static NodeSpec client = mandatoryRefersTo(Client.class);

    public static NodeSpec service = mandatoryRefersTo(Service.class);

    public static NodeSpec cost = mandatoryBigDecimal(9,2);

  }

}
