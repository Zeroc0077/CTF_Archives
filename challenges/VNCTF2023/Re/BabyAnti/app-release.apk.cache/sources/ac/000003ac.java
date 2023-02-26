package p043v;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import p017i.C0173b;
import p043v.InterfaceC0803c;

/* renamed from: v.j */
/* loaded from: classes.dex */
public class C0814j {

    /* renamed from: a */
    private final InterfaceC0803c f1454a;

    /* renamed from: b */
    private final String f1455b;

    /* renamed from: c */
    private final InterfaceC0820k f1456c;

    /* renamed from: d */
    private final InterfaceC0803c.InterfaceC0806c f1457d;

    /* renamed from: v.j$a */
    /* loaded from: classes.dex */
    private final class C0815a implements InterfaceC0803c.InterfaceC0804a {

        /* renamed from: a */
        private final InterfaceC0818c f1458a;

        /* renamed from: v.j$a$a */
        /* loaded from: classes.dex */
        class C0816a implements InterfaceC0819d {

            /* renamed from: a */
            final /* synthetic */ InterfaceC0803c.InterfaceC0805b f1460a;

            C0816a(InterfaceC0803c.InterfaceC0805b interfaceC0805b) {
                this.f1460a = interfaceC0805b;
            }

            @Override // p043v.C0814j.InterfaceC0819d
            /* renamed from: a */
            public void mo253a(String str, String str2, Object obj) {
                this.f1460a.mo254a(C0814j.this.f1456c.mo230b(str, str2, obj));
            }

            @Override // p043v.C0814j.InterfaceC0819d
            /* renamed from: b */
            public void mo252b(Object obj) {
                this.f1460a.mo254a(C0814j.this.f1456c.mo229c(obj));
            }

            @Override // p043v.C0814j.InterfaceC0819d
            /* renamed from: c */
            public void mo251c() {
                this.f1460a.mo254a(null);
            }
        }

        C0815a(InterfaceC0818c interfaceC0818c) {
            this.f1458a = interfaceC0818c;
        }

        /* renamed from: b */
        private String m255b(Exception exc) {
            StringWriter stringWriter = new StringWriter();
            exc.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.toString();
        }

        @Override // p043v.InterfaceC0803c.InterfaceC0804a
        /* renamed from: a */
        public void mo256a(ByteBuffer byteBuffer, InterfaceC0803c.InterfaceC0805b interfaceC0805b) {
            try {
                this.f1458a.mo183a(C0814j.this.f1456c.mo227e(byteBuffer), new C0816a(interfaceC0805b));
            } catch (RuntimeException e) {
                C0173b.m1929c("MethodChannel#" + C0814j.this.f1455b, "Failed to handle method call", e);
                interfaceC0805b.mo254a(C0814j.this.f1456c.mo231a("error", e.getMessage(), null, m255b(e)));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v.j$b */
    /* loaded from: classes.dex */
    public final class C0817b implements InterfaceC0803c.InterfaceC0805b {

        /* renamed from: a */
        private final InterfaceC0819d f1462a;

        C0817b(InterfaceC0819d interfaceC0819d) {
            this.f1462a = interfaceC0819d;
        }

        @Override // p043v.InterfaceC0803c.InterfaceC0805b
        /* renamed from: a */
        public void mo254a(ByteBuffer byteBuffer) {
            try {
                if (byteBuffer == null) {
                    this.f1462a.mo251c();
                } else {
                    try {
                        this.f1462a.mo252b(C0814j.this.f1456c.mo226f(byteBuffer));
                    } catch (C0808d e) {
                        this.f1462a.mo253a(e.f1448d, e.getMessage(), e.f1449e);
                    }
                }
            } catch (RuntimeException e2) {
                C0173b.m1929c("MethodChannel#" + C0814j.this.f1455b, "Failed to handle method call result", e2);
            }
        }
    }

    /* renamed from: v.j$c */
    /* loaded from: classes.dex */
    public interface InterfaceC0818c {
        /* renamed from: a */
        void mo183a(C0813i c0813i, InterfaceC0819d interfaceC0819d);
    }

    /* renamed from: v.j$d */
    /* loaded from: classes.dex */
    public interface InterfaceC0819d {
        /* renamed from: a */
        void mo253a(String str, String str2, Object obj);

        /* renamed from: b */
        void mo252b(Object obj);

        /* renamed from: c */
        void mo251c();
    }

    public C0814j(InterfaceC0803c interfaceC0803c, String str) {
        this(interfaceC0803c, str, C0827q.f1467b);
    }

    public C0814j(InterfaceC0803c interfaceC0803c, String str, InterfaceC0820k interfaceC0820k) {
        this(interfaceC0803c, str, interfaceC0820k, null);
    }

    public C0814j(InterfaceC0803c interfaceC0803c, String str, InterfaceC0820k interfaceC0820k, InterfaceC0803c.InterfaceC0806c interfaceC0806c) {
        this.f1454a = interfaceC0803c;
        this.f1455b = str;
        this.f1456c = interfaceC0820k;
        this.f1457d = interfaceC0806c;
    }

    /* renamed from: c */
    public void m259c(String str, Object obj) {
        m258d(str, obj, null);
    }

    /* renamed from: d */
    public void m258d(String str, Object obj, InterfaceC0819d interfaceC0819d) {
        this.f1454a.mo268e(this.f1455b, this.f1456c.mo228d(new C0813i(str, obj)), interfaceC0819d == null ? null : new C0817b(interfaceC0819d));
    }

    /* renamed from: e */
    public void m257e(InterfaceC0818c interfaceC0818c) {
        if (this.f1457d != null) {
            this.f1454a.mo269d(this.f1455b, interfaceC0818c != null ? new C0815a(interfaceC0818c) : null, this.f1457d);
        } else {
            this.f1454a.mo267f(this.f1455b, interfaceC0818c != null ? new C0815a(interfaceC0818c) : null);
        }
    }
}