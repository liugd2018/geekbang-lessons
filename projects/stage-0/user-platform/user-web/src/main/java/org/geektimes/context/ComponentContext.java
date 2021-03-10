package org.geektimes.context;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * TODO
 *
 * @author <a href="mailto:liugd2020@gmail.com">liuguodong</a>
 * @since 1.0
 */
public class ComponentContext {


    private static final String COMPONENT_ENV_CONTEXT_NAME = "java:comp/env";

    private Context envContext; // Component Env Context

    void init(){
        Context context = null;
        try {
            context = new InitialContext();
            this.envContext = (Context) context.lookup(COMPONENT_ENV_CONTEXT_NAME);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }



    }
}
