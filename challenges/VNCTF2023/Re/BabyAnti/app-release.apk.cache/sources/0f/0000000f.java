package p001a0;

import java.io.Serializable;
import kotlin.jvm.internal.C0403i;

/* renamed from: a0.j */
/* loaded from: classes.dex */
public final class C0015j<A, B> implements Serializable {

    /* renamed from: d */
    private final A f10d;

    /* renamed from: e */
    private final B f11e;

    public C0015j(A a, B b) {
        this.f10d = a;
        this.f11e = b;
    }

    /* renamed from: a */
    public final A m2254a() {
        return this.f10d;
    }

    /* renamed from: b */
    public final B m2253b() {
        return this.f11e;
    }

    /* renamed from: c */
    public final A m2252c() {
        return this.f10d;
    }

    /* renamed from: d */
    public final B m2251d() {
        return this.f11e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0015j) {
            C0015j c0015j = (C0015j) obj;
            return C0403i.m1240a(this.f10d, c0015j.f10d) && C0403i.m1240a(this.f11e, c0015j.f11e);
        }
        return false;
    }

    public int hashCode() {
        A a = this.f10d;
        int hashCode = (a == null ? 0 : a.hashCode()) * 31;
        B b = this.f11e;
        return hashCode + (b != null ? b.hashCode() : 0);
    }

    public String toString() {
        return '(' + this.f10d + ", " + this.f11e + ')';
    }
}