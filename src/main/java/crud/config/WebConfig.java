package crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration//данный класс используется контейнером Spring IoC, как конфигурационный класс для создания бинов
@EnableWebMvc//включения модуля Spring MVC в приложение
@ComponentScan("crud")//сканируем (ищем) бины (@Component) в пакете "crud"
public class WebConfig implements WebMvcConfigurer {//имплеминтируемся от интерфейса, чтобы изменить настройки по умолчанию

    // определяет местоположение статических ресурсов
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {//
        registry.addResourceHandler("/WEB-INF/pages/**").addResourceLocations("/pages/");
    }

    @Bean//перед методом, информирует Spring о том, что возвращаемый данным методом объект
    // должен быть зарегистрирован, как бин
    // а этот бин инициализирует View нашего проекта
    public InternalResourceViewResolver setupViewResolver() {//
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        //resolver.resolveViewName("")
        return resolver;
    }

}
