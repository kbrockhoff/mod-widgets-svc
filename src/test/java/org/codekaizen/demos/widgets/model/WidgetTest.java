package org.codekaizen.demos.widgets.model;

import io.micronaut.serde.ObjectMapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class WidgetTest {

    @Inject
    ObjectMapper objectMapper;

    @Test
    void testDefaultConstructor() {
        Widget widget = new Widget();
        assertNull(widget.getId());
        assertNull(widget.getName());
        assertNull(widget.getDescription());
    }

    @Test
    void testParameterizedConstructor() {
        String name = "Test Widget";
        String description = "A widget for testing";
        Widget widget = new Widget(name, description);
        
        assertNull(widget.getId());
        assertEquals(name, widget.getName());
        assertEquals(description, widget.getDescription());
    }

    @Test
    void testSettersAndGetters() {
        Widget widget = new Widget();
        
        Integer id = 1;
        String name = "Test Widget";
        String description = "A widget for testing";
        
        widget.setId(id);
        widget.setName(name);
        widget.setDescription(description);
        
        assertEquals(id, widget.getId());
        assertEquals(name, widget.getName());
        assertEquals(description, widget.getDescription());
    }

    @Test
    void testSerialization() throws Exception {
        Widget widget = new Widget("Test Widget", "A widget for testing");
        widget.setId(1);
        
        String json = objectMapper.writeValueAsString(widget);
        Widget deserializedWidget = objectMapper.readValue(json, Widget.class);
        
        assertEquals(widget.getId(), deserializedWidget.getId());
        assertEquals(widget.getName(), deserializedWidget.getName());
        assertEquals(widget.getDescription(), deserializedWidget.getDescription());
    }
}
