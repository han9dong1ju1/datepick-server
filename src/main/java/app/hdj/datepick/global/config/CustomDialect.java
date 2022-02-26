package app.hdj.datepick.global.config;


import org.hibernate.dialect.MariaDB103Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class CustomDialect extends MariaDB103Dialect {
    public CustomDialect() {
        super();
        this.registerFunction("match", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE, "match(?1) against  (?2 in boolean mode)"));
    }
}
