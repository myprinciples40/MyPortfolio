package org.example.mvc.view;

// Interface that takes a view name and determines which view to do
public interface ViewResolver {
    View resolveView(String viewName);
}
