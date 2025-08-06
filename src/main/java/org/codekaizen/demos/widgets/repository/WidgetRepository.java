package org.codekaizen.demos.widgets.repository;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;
import org.codekaizen.demos.widgets.model.Widget;

@JdbcRepository(dialect = Dialect.H2)
public interface WidgetRepository extends PageableRepository<Widget, Integer> {
    // Basic CRUD operations are inherited from CrudRepository:
    // save(), findById(), existsById(), findAll(), count(), deleteById(), delete(), deleteAll()
}
