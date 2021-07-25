package common.proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DaoTest {
    @Test
    public void test() throws InstantiationException, IllegalAccessException {
        Pojo pojo = getInstance(Pojo.class);
        Assert.assertEquals("d a", pojo.getKey());
    }

    @Test
    public void testJavaProxy() throws InstantiationException, IllegalAccessException {
        Pojo pojo = getInstanceJavaProxy(Pojo.class);
        Assert.assertEquals("d a", pojo.getKey());
    }

    private <T> T getInstance(Class<T> clz) throws IllegalAccessException, InstantiationException {
        InvocationHandler invocationHandler = new MyProxy("d");
        Class<? extends T> proxy = new ByteBuddy()
                .subclass(clz)
                .method(ElementMatchers.any())
                .intercept(InvocationHandlerAdapter.of(invocationHandler))
                .make()
                .load(clz.getClassLoader())
                .getLoaded();
        return proxy.newInstance();
    }

    private <T> T getInstanceJavaProxy(Class<T> clz) throws IllegalAccessException, InstantiationException {
        InvocationHandler invocationHandler = new MyProxy("d");
        return (T) Proxy.newProxyInstance(clz.getClassLoader(),
                new Class[] { clz },
                invocationHandler);
    }
}
