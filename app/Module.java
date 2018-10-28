import com.google.inject.AbstractModule;
import models.Company;
import play.api.db.evolutions.DynamicEvolutions;
import play.db.ebean.DefaultEbeanConfig;
import play.db.ebean.EbeanConfig;
import play.db.ebean.EbeanDynamicEvolutions;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        bind(DBInitSetup.class).asEagerSingleton();
    }
}
