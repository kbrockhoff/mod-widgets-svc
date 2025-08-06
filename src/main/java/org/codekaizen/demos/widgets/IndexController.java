package org.codekaizen.demos.widgets;

import io.micronaut.http.annotation.*;

@Controller("/widgetsSvc")
public class IndexController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}