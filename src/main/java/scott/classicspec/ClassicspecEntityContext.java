package scott.classicspec;

import scott.barleydb.api.core.entity.EntityContext;
import scott.barleydb.api.core.Environment;

public class ClassicspecEntityContext extends EntityContext {

  private static final long serialVersionUID = 1L;

  public ClassicspecEntityContext(Environment env) {
    super(env, "scott.classicspec");
  }
}