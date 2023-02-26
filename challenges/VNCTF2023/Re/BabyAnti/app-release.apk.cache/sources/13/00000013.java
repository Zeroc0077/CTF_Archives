package p001a0;

import kotlin.jvm.internal.C0403i;
import p001a0.C0016k;

/* renamed from: a0.l */
/* loaded from: classes.dex */
public final class C0019l {
    /* renamed from: a */
    public static final Object m2246a(Throwable exception) {
        C0403i.m1236e(exception, "exception");
        return new C0016k.C0018b(exception);
    }

    /* renamed from: b */
    public static final void m2245b(Object obj) {
        if (obj instanceof C0016k.C0018b) {
            throw ((C0016k.C0018b) obj).f13d;
        }
    }
}