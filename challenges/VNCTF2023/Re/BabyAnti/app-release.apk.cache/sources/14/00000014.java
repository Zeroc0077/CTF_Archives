package p001a0;

import java.io.Serializable;
import kotlin.jvm.internal.C0399e;
import kotlin.jvm.internal.C0403i;
import p022k0.InterfaceC0358a;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: a0.m */
/* loaded from: classes.dex */
public final class C0020m<T> implements InterfaceC0010e<T>, Serializable {

    /* renamed from: d */
    private InterfaceC0358a<? extends T> f14d;

    /* renamed from: e */
    private volatile Object f15e;

    /* renamed from: f */
    private final Object f16f;

    public C0020m(InterfaceC0358a<? extends T> initializer, Object obj) {
        C0403i.m1236e(initializer, "initializer");
        this.f14d = initializer;
        this.f15e = C0022o.f17a;
        this.f16f = obj == null ? this : obj;
    }

    public /* synthetic */ C0020m(InterfaceC0358a interfaceC0358a, Object obj, int i, C0399e c0399e) {
        this(interfaceC0358a, (i & 2) != 0 ? null : obj);
    }

    /* renamed from: a */
    public boolean m2244a() {
        return this.f15e != C0022o.f17a;
    }

    @Override // p001a0.InterfaceC0010e
    public T getValue() {
        T t;
        T t2 = (T) this.f15e;
        C0022o c0022o = C0022o.f17a;
        if (t2 != c0022o) {
            return t2;
        }
        synchronized (this.f16f) {
            t = (T) this.f15e;
            if (t == c0022o) {
                InterfaceC0358a<? extends T> interfaceC0358a = this.f14d;
                C0403i.m1239b(interfaceC0358a);
                t = interfaceC0358a.invoke();
                this.f15e = t;
                this.f14d = null;
            }
        }
        return t;
    }

    public String toString() {
        return m2244a() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}