package p008d0;

import kotlin.jvm.internal.AbstractC0404j;
import kotlin.jvm.internal.C0403i;
import p008d0.InterfaceC0133e;
import p022k0.InterfaceC0373p;

/* renamed from: d0.g */
/* loaded from: classes.dex */
public interface InterfaceC0137g {

    /* renamed from: d0.g$a */
    /* loaded from: classes.dex */
    public static final class C0138a {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: d0.g$a$a */
        /* loaded from: classes.dex */
        public static final class C0139a extends AbstractC0404j implements InterfaceC0373p<InterfaceC0137g, InterfaceC0140b, InterfaceC0137g> {

            /* renamed from: d */
            public static final C0139a f160d = new C0139a();

            C0139a() {
                super(2);
            }

            @Override // p022k0.InterfaceC0373p
            /* renamed from: a */
            public final InterfaceC0137g invoke(InterfaceC0137g acc, InterfaceC0140b element) {
                C0130c c0130c;
                C0403i.m1236e(acc, "acc");
                C0403i.m1236e(element, "element");
                InterfaceC0137g mo219g = acc.mo219g(element.getKey());
                C0143h c0143h = C0143h.f161d;
                if (mo219g == c0143h) {
                    return element;
                }
                InterfaceC0133e.C0135b c0135b = InterfaceC0133e.f158a;
                InterfaceC0133e interfaceC0133e = (InterfaceC0133e) mo219g.mo220b(c0135b);
                if (interfaceC0133e == null) {
                    c0130c = new C0130c(mo219g, element);
                } else {
                    InterfaceC0137g mo219g2 = mo219g.mo219g(c0135b);
                    if (mo219g2 == c0143h) {
                        return new C0130c(element, interfaceC0133e);
                    }
                    c0130c = new C0130c(new C0130c(mo219g2, element), interfaceC0133e);
                }
                return c0130c;
            }
        }

        /* renamed from: a */
        public static InterfaceC0137g m1974a(InterfaceC0137g interfaceC0137g, InterfaceC0137g context) {
            C0403i.m1236e(context, "context");
            return context == C0143h.f161d ? interfaceC0137g : (InterfaceC0137g) context.mo218o(interfaceC0137g, C0139a.f160d);
        }
    }

    /* renamed from: d0.g$b */
    /* loaded from: classes.dex */
    public interface InterfaceC0140b extends InterfaceC0137g {

        /* renamed from: d0.g$b$a */
        /* loaded from: classes.dex */
        public static final class C0141a {
            /* renamed from: a */
            public static <R> R m1972a(InterfaceC0140b interfaceC0140b, R r, InterfaceC0373p<? super R, ? super InterfaceC0140b, ? extends R> operation) {
                C0403i.m1236e(operation, "operation");
                return operation.invoke(r, interfaceC0140b);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: b */
            public static <E extends InterfaceC0140b> E m1971b(InterfaceC0140b interfaceC0140b, InterfaceC0142c<E> key) {
                C0403i.m1236e(key, "key");
                if (C0403i.m1240a(interfaceC0140b.getKey(), key)) {
                    C0403i.m1238c(interfaceC0140b, "null cannot be cast to non-null type E of kotlin.coroutines.CoroutineContext.Element.get");
                    return interfaceC0140b;
                }
                return null;
            }

            /* renamed from: c */
            public static InterfaceC0137g m1970c(InterfaceC0140b interfaceC0140b, InterfaceC0142c<?> key) {
                C0403i.m1236e(key, "key");
                return C0403i.m1240a(interfaceC0140b.getKey(), key) ? C0143h.f161d : interfaceC0140b;
            }

            /* renamed from: d */
            public static InterfaceC0137g m1969d(InterfaceC0140b interfaceC0140b, InterfaceC0137g context) {
                C0403i.m1236e(context, "context");
                return C0138a.m1974a(interfaceC0140b, context);
            }
        }

        @Override // p008d0.InterfaceC0137g
        /* renamed from: b */
        <E extends InterfaceC0140b> E mo220b(InterfaceC0142c<E> interfaceC0142c);

        InterfaceC0142c<?> getKey();
    }

    /* renamed from: d0.g$c */
    /* loaded from: classes.dex */
    public interface InterfaceC0142c<E extends InterfaceC0140b> {
    }

    /* renamed from: b */
    <E extends InterfaceC0140b> E mo220b(InterfaceC0142c<E> interfaceC0142c);

    /* renamed from: g */
    InterfaceC0137g mo219g(InterfaceC0142c<?> interfaceC0142c);

    /* renamed from: o */
    <R> R mo218o(R r, InterfaceC0373p<? super R, ? super InterfaceC0140b, ? extends R> interfaceC0373p);

    /* renamed from: x */
    InterfaceC0137g mo217x(InterfaceC0137g interfaceC0137g);
}