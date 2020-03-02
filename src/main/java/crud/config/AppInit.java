package crud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionTrackingMode;
import java.util.EnumSet;
import java.util.Set;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Метод, указывающий на класс конфигурации - что здесь вставляем? Секьюрити?
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                SecurityConfig.class, WebConfig.class
        };
    }

    // Добавление конфигурации, в которой инициализируем ViewResolver, для корректного отображения jsp.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebConfig.class
        };
    }

    /* Данный метод указывает url, на котором будет базироваться приложение */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

//    @Override
//    protected void registerDispatcherServlet(ServletContext servletContext) {
//        String servletName = getServletName();
//        Assert.hasLength(servletName, "getServletName() must not return empty or null");
//
//        WebApplicationContext servletAppContext = createServletApplicationContext();
//        Assert.notNull(servletAppContext,
//                "createServletApplicationContext() did not return an application " +
//                        "context for servlet [" + servletName + "]");
//
//        FrameworkServlet dispatcherServlet = createDispatcherServlet(servletAppContext);
//        dispatcherServlet.setContextInitializers(getServletApplicationContextInitializers());
//
//        ServletRegistration.Dynamic registration = servletContext.addServlet(servletName, dispatcherServlet);
//        Assert.notNull(registration,
//                "Failed to register servlet with name '" + servletName + "'." +
//                        "Check if there is another servlet registered under the same name.");
//
//        registration.setLoadOnStartup(1);
//        registration.addMapping(getServletMappings());
//        registration.setAsyncSupported(isAsyncSupported());
////добавил
//        servletContext.setSessionTrackingModes(getSessionTrackingModes());
////
//        Filter[] filters = getServletFilters();
//        if (!ObjectUtils.isEmpty(filters)) {
//            for (Filter filter : filters) {
//                registerServletFilter(servletContext, filter);
//            }
//        }
//
//        customizeRegistration(registration);
//    }
//
//    protected Set<SessionTrackingMode> getSessionTrackingModes() {
//        return EnumSet.of(SessionTrackingMode.COOKIE);
//    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[]{characterEncodingFilter};
    }
}
