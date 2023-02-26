package p000a;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: a.b */
/* loaded from: classes.dex */
public class C0003b extends AbstractC0005c {

    /* renamed from: a */
    private final Object f5a = new Object();

    /* renamed from: b */
    private final ExecutorService f6b = Executors.newFixedThreadPool(4, new ThreadFactoryC0004a());

    /* renamed from: c */
    private volatile Handler f7c;

    /* renamed from: a.b$a */
    /* loaded from: classes.dex */
    class ThreadFactoryC0004a implements ThreadFactory {

        /* renamed from: a */
        private final AtomicInteger f8a = new AtomicInteger(0);

        ThreadFactoryC0004a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(String.format("arch_disk_io_%d", Integer.valueOf(this.f8a.getAndIncrement())));
            return thread;
        }
    }

    /* renamed from: d */
    private static Handler m2262d(Looper looper) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            return Handler.createAsync(looper);
        }
        if (i >= 16) {
            try {
                return (Handler) Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(looper, null, Boolean.TRUE);
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException unused) {
            } catch (InvocationTargetException unused2) {
                return new Handler(looper);
            }
        }
        return new Handler(looper);
    }

    @Override // p000a.AbstractC0005c
    /* renamed from: a */
    public void mo2261a(Runnable runnable) {
        this.f6b.execute(runnable);
    }

    @Override // p000a.AbstractC0005c
    /* renamed from: b */
    public boolean mo2260b() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    @Override // p000a.AbstractC0005c
    /* renamed from: c */
    public void mo2259c(Runnable runnable) {
        if (this.f7c == null) {
            synchronized (this.f5a) {
                if (this.f7c == null) {
                    this.f7c = m2262d(Looper.getMainLooper());
                }
            }
        }
        this.f7c.post(runnable);
    }
}