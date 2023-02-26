package p003b;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* renamed from: b.b */
/* loaded from: classes.dex */
public class C0073b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* renamed from: d */
    C0076c<K, V> f103d;

    /* renamed from: e */
    private C0076c<K, V> f104e;

    /* renamed from: f */
    private WeakHashMap<InterfaceC0079f<K, V>, Boolean> f105f = new WeakHashMap<>();

    /* renamed from: g */
    private int f106g = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b.b$a */
    /* loaded from: classes.dex */
    public static class C0074a<K, V> extends AbstractC0078e<K, V> {
        C0074a(C0076c<K, V> c0076c, C0076c<K, V> c0076c2) {
            super(c0076c, c0076c2);
        }

        @Override // p003b.C0073b.AbstractC0078e
        /* renamed from: b */
        C0076c<K, V> mo2121b(C0076c<K, V> c0076c) {
            return c0076c.f110g;
        }

        @Override // p003b.C0073b.AbstractC0078e
        /* renamed from: c */
        C0076c<K, V> mo2120c(C0076c<K, V> c0076c) {
            return c0076c.f109f;
        }
    }

    /* renamed from: b.b$b */
    /* loaded from: classes.dex */
    private static class C0075b<K, V> extends AbstractC0078e<K, V> {
        C0075b(C0076c<K, V> c0076c, C0076c<K, V> c0076c2) {
            super(c0076c, c0076c2);
        }

        @Override // p003b.C0073b.AbstractC0078e
        /* renamed from: b */
        C0076c<K, V> mo2121b(C0076c<K, V> c0076c) {
            return c0076c.f109f;
        }

        @Override // p003b.C0073b.AbstractC0078e
        /* renamed from: c */
        C0076c<K, V> mo2120c(C0076c<K, V> c0076c) {
            return c0076c.f110g;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b.b$c */
    /* loaded from: classes.dex */
    public static class C0076c<K, V> implements Map.Entry<K, V> {

        /* renamed from: d */
        final K f107d;

        /* renamed from: e */
        final V f108e;

        /* renamed from: f */
        C0076c<K, V> f109f;

        /* renamed from: g */
        C0076c<K, V> f110g;

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof C0076c) {
                C0076c c0076c = (C0076c) obj;
                return this.f107d.equals(c0076c.f107d) && this.f108e.equals(c0076c.f108e);
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f107d;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f108e;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f107d.hashCode() ^ this.f108e.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f107d + "=" + this.f108e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b.b$d */
    /* loaded from: classes.dex */
    public class C0077d implements Iterator<Map.Entry<K, V>>, InterfaceC0079f<K, V> {

        /* renamed from: d */
        private C0076c<K, V> f111d;

        /* renamed from: e */
        private boolean f112e = true;

        C0077d() {
        }

        @Override // p003b.C0073b.InterfaceC0079f
        /* renamed from: a */
        public void mo2117a(C0076c<K, V> c0076c) {
            C0076c<K, V> c0076c2 = this.f111d;
            if (c0076c == c0076c2) {
                C0076c<K, V> c0076c3 = c0076c2.f110g;
                this.f111d = c0076c3;
                this.f112e = c0076c3 == null;
            }
        }

        @Override // java.util.Iterator
        /* renamed from: b */
        public Map.Entry<K, V> next() {
            C0076c<K, V> c0076c;
            if (this.f112e) {
                this.f112e = false;
                c0076c = C0073b.this.f103d;
            } else {
                C0076c<K, V> c0076c2 = this.f111d;
                c0076c = c0076c2 != null ? c0076c2.f109f : null;
            }
            this.f111d = c0076c;
            return this.f111d;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f112e) {
                return C0073b.this.f103d != null;
            }
            C0076c<K, V> c0076c = this.f111d;
            return (c0076c == null || c0076c.f109f == null) ? false : true;
        }
    }

    /* renamed from: b.b$e */
    /* loaded from: classes.dex */
    private static abstract class AbstractC0078e<K, V> implements Iterator<Map.Entry<K, V>>, InterfaceC0079f<K, V> {

        /* renamed from: d */
        C0076c<K, V> f114d;

        /* renamed from: e */
        C0076c<K, V> f115e;

        AbstractC0078e(C0076c<K, V> c0076c, C0076c<K, V> c0076c2) {
            this.f114d = c0076c2;
            this.f115e = c0076c;
        }

        /* renamed from: e */
        private C0076c<K, V> m2118e() {
            C0076c<K, V> c0076c = this.f115e;
            C0076c<K, V> c0076c2 = this.f114d;
            if (c0076c == c0076c2 || c0076c2 == null) {
                return null;
            }
            return mo2120c(c0076c);
        }

        @Override // p003b.C0073b.InterfaceC0079f
        /* renamed from: a */
        public void mo2117a(C0076c<K, V> c0076c) {
            if (this.f114d == c0076c && c0076c == this.f115e) {
                this.f115e = null;
                this.f114d = null;
            }
            C0076c<K, V> c0076c2 = this.f114d;
            if (c0076c2 == c0076c) {
                this.f114d = mo2121b(c0076c2);
            }
            if (this.f115e == c0076c) {
                this.f115e = m2118e();
            }
        }

        /* renamed from: b */
        abstract C0076c<K, V> mo2121b(C0076c<K, V> c0076c);

        /* renamed from: c */
        abstract C0076c<K, V> mo2120c(C0076c<K, V> c0076c);

        @Override // java.util.Iterator
        /* renamed from: d */
        public Map.Entry<K, V> next() {
            C0076c<K, V> c0076c = this.f115e;
            this.f115e = m2118e();
            return c0076c;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f115e != null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b.b$f */
    /* loaded from: classes.dex */
    public interface InterfaceC0079f<K, V> {
        /* renamed from: a */
        void mo2117a(C0076c<K, V> c0076c);
    }

    /* renamed from: a */
    public Iterator<Map.Entry<K, V>> m2128a() {
        C0075b c0075b = new C0075b(this.f104e, this.f103d);
        this.f105f.put(c0075b, Boolean.FALSE);
        return c0075b;
    }

    /* renamed from: b */
    public Map.Entry<K, V> m2127b() {
        return this.f103d;
    }

    /* renamed from: c */
    protected C0076c<K, V> mo2126c(K k) {
        C0076c<K, V> c0076c = this.f103d;
        while (c0076c != null && !c0076c.f107d.equals(k)) {
            c0076c = c0076c.f109f;
        }
        return c0076c;
    }

    /* renamed from: d */
    public C0073b<K, V>.C0077d m2125d() {
        C0073b<K, V>.C0077d c0077d = new C0077d();
        this.f105f.put(c0077d, Boolean.FALSE);
        return c0077d;
    }

    /* renamed from: e */
    public Map.Entry<K, V> m2124e() {
        return this.f104e;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C0073b) {
            C0073b c0073b = (C0073b) obj;
            if (size() != c0073b.size()) {
                return false;
            }
            Iterator<Map.Entry<K, V>> it = iterator();
            Iterator<Map.Entry<K, V>> it2 = c0073b.iterator();
            while (it.hasNext() && it2.hasNext()) {
                Map.Entry<K, V> next = it.next();
                Map.Entry<K, V> next2 = it2.next();
                if ((next == null && next2 != null) || (next != null && !next.equals(next2))) {
                    return false;
                }
            }
            return (it.hasNext() || it2.hasNext()) ? false : true;
        }
        return false;
    }

    /* renamed from: f */
    public V mo2123f(K k) {
        C0076c<K, V> mo2126c = mo2126c(k);
        if (mo2126c == null) {
            return null;
        }
        this.f106g--;
        if (!this.f105f.isEmpty()) {
            for (InterfaceC0079f<K, V> interfaceC0079f : this.f105f.keySet()) {
                interfaceC0079f.mo2117a(mo2126c);
            }
        }
        C0076c<K, V> c0076c = mo2126c.f110g;
        C0076c<K, V> c0076c2 = mo2126c.f109f;
        if (c0076c != null) {
            c0076c.f109f = c0076c2;
        } else {
            this.f103d = c0076c2;
        }
        C0076c<K, V> c0076c3 = mo2126c.f109f;
        if (c0076c3 != null) {
            c0076c3.f110g = c0076c;
        } else {
            this.f104e = c0076c;
        }
        mo2126c.f109f = null;
        mo2126c.f110g = null;
        return mo2126c.f108e;
    }

    public int hashCode() {
        Iterator<Map.Entry<K, V>> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().hashCode();
        }
        return i;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        C0074a c0074a = new C0074a(this.f103d, this.f104e);
        this.f105f.put(c0074a, Boolean.FALSE);
        return c0074a;
    }

    public int size() {
        return this.f106g;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<Map.Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}