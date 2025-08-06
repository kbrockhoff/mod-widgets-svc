package org.codekaizen.demos.widgets;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import org.codekaizen.demos.widgets.model.Widget;
import org.codekaizen.demos.widgets.repository.WidgetRepository;

import io.micronaut.core.annotation.NonNull;
import javax.validation.Valid;

@Controller("/api/widgets")
public class WidgetController {

    private final WidgetRepository repository;

    public WidgetController(WidgetRepository repository) {
        this.repository = repository;
    }

    @Get
    public Page<Widget> list(@QueryValue(defaultValue = "0") int page,
                           @QueryValue(defaultValue = "10") int size) {
        return repository.findAll(Pageable.from(page, size));
    }

    @Get("/{id}")
    public HttpResponse<Widget> get(@PathVariable("id") Integer id) {
        return repository.findById(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }

    @Post
    @Status(HttpStatus.CREATED)
    public Widget create(@Body @Valid @NonNull Widget widget) {
        // Ensure no ID is set for creation
        widget.setId(null);
        return repository.save(widget);
    }

    @Put("/{id}")
    public HttpResponse<Widget> update(@PathVariable("id") Integer id, @Body @Valid @NonNull Widget widget) {
        if (!repository.existsById(id)) {
            return HttpResponse.notFound();
        }
        widget.setId(id);
        return HttpResponse.ok(repository.save(widget));
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        repository.deleteById(id);
    }
}
