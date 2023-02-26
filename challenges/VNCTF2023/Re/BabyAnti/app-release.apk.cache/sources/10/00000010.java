package p001a0;

import java.io.Serializable;
import kotlin.jvm.internal.C0399e;
import kotlin.jvm.internal.C0403i;

/* renamed from: a0.k */
/* loaded from: classes.dex */
public final class C0016k<T> implements Serializable {

    /* renamed from: d */
    public static final C0017a f12d = new C0017a(null);

    /* renamed from: a0.k$a */
    /* loaded from: classes.dex */
    public static final class C0017a {
        private C0017a() {
        }

        public /* synthetic */ C0017a(C0399e c0399e) {
            this();
        }
    }

    /* renamed from: a0.k$b */
    /* loaded from: classes.dex */
    public static final class C0018b implements Serializable {

        /* renamed from: d */
        public final Throwable f13d;

        public C0018b(Throwable exception) {
            C0403i.m1236e(exception, "exception");
            this.f13d = exception;
        }

        public boolean equals(Object obj) {
            return (obj instanceof C0018b) && C0403i.m1240a(this.f13d, ((C0018b) obj).f13d);
        }

        public int hashCode() {
            return this.f13d.hashCode();
        }

        public String toString() {
            return "Failure(" + this.f13d + ')';
        }
    }

    /* renamed from: a */
    public static <T> Object m2250a(Object obj) {
        return obj;
    }

    /* renamed from: b */
    public static final Throwable m2249b(Object obj) {
        if (obj instanceof C0018b) {
            return ((C0018b) obj).f13d;
        }
        return null;
    }

    /* renamed from: c */
    public static final boolean m2248c(Object obj) {
        return obj instanceof C0018b;
    }

    /* renamed from: d */
    public static final boolean m2247d(Object obj) {
        return !(obj instanceof C0018b);
    }
}