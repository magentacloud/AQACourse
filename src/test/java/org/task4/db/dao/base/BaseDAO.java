package org.task4.db.dao.base;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.core.mapper.ColumnMappers;
import org.task3.env.Env;
import org.task3.env.config.DBConfig;
import org.task4.db.dao.DataSourceProvider;

@Slf4j
public class BaseDAO {
    protected Jdbi jdbi;

    public BaseDAO(){
        log.info("Начало инициализации базового DAO");
        jdbi = Jdbi.create(DataSourceProvider.getH2DataSource(Env.DB.DB_CONFIG));
        jdbi.installPlugin(new H2DatabasePlugin());
        jdbi.getConfig(ColumnMappers.class).setCoalesceNullPrimitivesToDefaults(false);

        log.info("Базовый DAO успешно инициализирован");
    }
}
