package org.codekaizen.demos.widgets.repository;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.codekaizen.demos.widgets.model.Widget;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class WidgetRepositoryTest {

    @Inject
    WidgetRepository repository;

    @Test
    void testSaveAndRetrieveWidget() {
        Widget widget = new Widget("Test Widget", "A test widget");
        Widget savedWidget = repository.save(widget);
        
        assertNotNull(savedWidget.getId());
        assertTrue(repository.existsById(savedWidget.getId()));
        
        Widget retrievedWidget = repository.findById(savedWidget.getId()).orElse(null);
        assertNotNull(retrievedWidget);
        assertEquals("Test Widget", retrievedWidget.getName());
        assertEquals("A test widget", retrievedWidget.getDescription());
    }

    @Test
    void testUpdateWidget() {
        Widget widget = repository.save(new Widget("Original Name", "Original description"));
        assertNotNull(widget.getId());
        
        widget.setName("Updated Name");
        widget.setDescription("Updated description");
        Widget updatedWidget = repository.update(widget);
        
        assertEquals("Updated Name", updatedWidget.getName());
        assertEquals("Updated description", updatedWidget.getDescription());
        
        Widget retrievedWidget = repository.findById(widget.getId()).orElse(null);
        assertNotNull(retrievedWidget);
        assertEquals("Updated Name", retrievedWidget.getName());
        assertEquals("Updated description", retrievedWidget.getDescription());
    }

    @Test
    void testDeleteWidget() {
        Widget widget = repository.save(new Widget("To Delete", "Will be deleted"));
        assertNotNull(widget.getId());
        
        repository.deleteById(widget.getId());
        
        assertFalse(repository.existsById(widget.getId()));
        assertTrue(repository.findById(widget.getId()).isEmpty());
    }

    @Test
    void testFindAll() {
        repository.deleteAll(); // Clear any existing data
        
        repository.save(new Widget("Widget 1", "First widget"));
        repository.save(new Widget("Widget 2", "Second widget"));
        
        Iterable<Widget> widgets = repository.findAll();
        int count = 0;
        for (Widget w : widgets) {
            assertNotNull(w.getId());
            assertTrue(w.getName().startsWith("Widget "));
            count++;
        }
        
        assertEquals(2, count);
        assertEquals(2, repository.count());
    }
}
