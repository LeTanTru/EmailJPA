package dopamine.connection;

import dopamine.constants.Constant;
import com.zaxxer.hikari.HikariDataSource;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;
import java.net.URL;
import java.util.*;

public class DBConnection implements PersistenceUnitInfo {

    @Override
    public String getPersistenceUnitName() {
        return "JpaPU";
    }

    @Override
    public String getPersistenceProviderClassName() {
        return "org.hibernate.jpa.HibernatePersistenceProvider";
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return PersistenceUnitTransactionType.RESOURCE_LOCAL;
    }

    @Override
    public DataSource getJtaDataSource() {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl(Constant.DB_URL);
        dataSource.setUsername(Constant.USERNAME);
        dataSource.setPassword(Constant.PASSWORD);

        return dataSource;
    }

    @Override
    public DataSource getNonJtaDataSource() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getMappingFileNames() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<URL> getJarFileUrls() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getManagedClassNames() {
        return List.of("dopamine.model.User");
    }

    @Override
    public boolean excludeUnlistedClasses() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ValidationMode getValidationMode() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Properties getProperties() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPersistenceXMLSchemaVersion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addTransformer(ClassTransformer transformer) {
        // TODO Auto-generated method stub

    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        // TODO Auto-generated method stub
        return null;
    }
}
