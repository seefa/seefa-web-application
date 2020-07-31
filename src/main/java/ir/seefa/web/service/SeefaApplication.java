package ir.seefa.web.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 25 Jul 2020 T 07:24:30
 */
@ApplicationPath("/service/")
public class SeefaApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(RestWebApplication.class);
        return classes;
    }
}
