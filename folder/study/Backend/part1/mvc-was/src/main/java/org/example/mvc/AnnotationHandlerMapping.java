package org.example.mvc;

import org.example.mvc.annotation.Controller;
import org.example.mvc.annotation.RequestMapping;
import org.example.mvc.controller.RequestMethod;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping{

    private final Object[] basePackage;

    // The value is populated on initialization.
    private Map<HandlerKey, AnnotationHandler> handlers = new HashMap<>();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    // After the initialize() method is initialized in the DispatcherServlet class, the handlers that are Maps are automatically initialized.
    public void initialize() {
        Reflections reflections = new Reflections(basePackage);

        // Get all classes with the Controller annotation that are under basePackage. (HomeController)
        Set<Class<?>> claszzesWithControllerAnnotation = reflections.getTypesAnnotatedWith(Controller.class);

        // Gets all the parameters that have a RequestMapping attached to them in a class type object.
        claszzesWithControllerAnnotation.forEach(clazz ->
                Arrays.stream(clazz.getDeclaredMethods()).forEach(declaredMethod -> {
                    RequestMapping requestMapping = declaredMethod.getDeclaredAnnotation(RequestMapping.class);

                    // @RequestMapping(value = "/", method = RequestMethod.GET)
                    // Get the value and create a HandlerKey.
                    Arrays.stream(getRequestMethods(requestMapping))
                            .forEach(requestMethod -> handlers.put(
                                    new HandlerKey(requestMethod, requestMapping.value()), new AnnotationHandler(clazz, declaredMethod)
                            ));
                })
        );
    }

    // Returns the requestMapping annotation passed in, depending on the method
    private RequestMethod[] getRequestMethods(RequestMapping requestMapping) {
        return requestMapping.method();
    }

    @Override
    public Object findHandler(HandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }
}
