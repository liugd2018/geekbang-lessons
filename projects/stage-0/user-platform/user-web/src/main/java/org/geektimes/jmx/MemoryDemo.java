package org.geektimes.jmx;

/**
 * TODO
 *
 * @author <a href="mailto:liugd2020@gmail.com">liuguodong</a>
 * @since 1.0
 */
import org.jolokia.client.J4pClient;
import org.jolokia.client.exception.J4pException;
import org.jolokia.client.request.*;

import javax.management.MalformedObjectNameException;

public class MemoryDemo {
    public static void main(String[] args) throws MalformedObjectNameException, J4pException {
        J4pClient client = new J4pClient("http://localhost:8080/jolokia");
        // /jolokia/read/java.lang:type=Memory/HeapMemoryUsage/used

        J4pReadRequest request =
                new J4pReadRequest("java.lang:type=Memory","HeapMemoryUsage");
        request.setPath("used");
        J4pReadResponse response = client.execute(request);
        System.out.println("Memory used: " + response.getValue());
    }
}