package gr.codehub.j101.p01jvm.reference;

import gr.codehub.j101.p01jvm.model.Webpage;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class WeakReferences {
    private static Map<String, Object> cachedPages = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        weakDemo();
    }

    private static void weakDemo() throws InterruptedException {
        rememberPageWeak(new Webpage("example1.com", "Hi from weak example1.com"));
        rememberPage(new Webpage("example2.com", "Hi from example2.com"));
        rememberPageWeak(new Webpage("example3.com", "Hi from weak example3.com"));

        Webpage p1 = recallWebpage("example1.com");
        System.out.println("The content of " + p1.getUrl() + " is " + p1.getContent());

        System.gc();
        Thread.sleep(10000);

        Webpage p2 = recallWebpage("example2.com");
        System.out.println("The content of " + p2.getUrl() + " is " + p2.getContent());
        Webpage p3 = recallWebpage("example3.com");
        System.out.println("The content of " + p3.getUrl() + " is " + p3.getContent());
    }

    public static void rememberPage(Webpage webpage) {
        cachedPages.put(webpage.getUrl(), webpage);
    }

    public static void rememberPageWeak(Webpage webpage) {
        cachedPages.put(webpage.getUrl(), new WeakReference(webpage));
    }

    public static Webpage recallWebpage(String url) {
        Object o = cachedPages.get(url);
        if (o == null) return null;
        if (o instanceof WeakReference) return ((WeakReference<Webpage>) o).get();
        if (o instanceof Webpage) return (Webpage) o;
        throw new RuntimeException("Unknown class in webpage cache");
    }


}
