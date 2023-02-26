package p005c;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* renamed from: c.d */
/* loaded from: classes.dex */
public class C0121d<K, V> {

    /* renamed from: g */
    static Object[] f140g;

    /* renamed from: h */
    static int f141h;

    /* renamed from: i */
    static Object[] f142i;

    /* renamed from: j */
    static int f143j;

    /* renamed from: d */
    int[] f144d = C0114b.f122a;

    /* renamed from: e */
    Object[] f145e = C0114b.f124c;

    /* renamed from: f */
    int f146f = 0;

    /* renamed from: a */
    private void m2001a(int i) {
        if (i == 8) {
            synchronized (C0121d.class) {
                Object[] objArr = f142i;
                if (objArr != null) {
                    this.f145e = objArr;
                    f142i = (Object[]) objArr[0];
                    this.f144d = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f143j--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (C0121d.class) {
                Object[] objArr2 = f140g;
                if (objArr2 != null) {
                    this.f145e = objArr2;
                    f140g = (Object[]) objArr2[0];
                    this.f144d = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f141h--;
                    return;
                }
            }
        }
        this.f144d = new int[i];
        this.f145e = new Object[i << 1];
    }

    /* renamed from: b */
    private static int m2000b(int[] iArr, int i, int i2) {
        try {
            return C0114b.m2023a(iArr, i, i2);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    /* renamed from: d */
    private static void m1998d(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (C0121d.class) {
                if (f143j < 10) {
                    objArr[0] = f142i;
                    objArr[1] = iArr;
                    for (int i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    f142i = objArr;
                    f143j++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (C0121d.class) {
                if (f141h < 10) {
                    objArr[0] = f140g;
                    objArr[1] = iArr;
                    for (int i3 = (i << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    f140g = objArr;
                    f141h++;
                }
            }
        }
    }

    /* renamed from: c */
    public void m1999c(int i) {
        int i2 = this.f146f;
        int[] iArr = this.f144d;
        if (iArr.length < i) {
            Object[] objArr = this.f145e;
            m2001a(i);
            if (this.f146f > 0) {
                System.arraycopy(iArr, 0, this.f144d, 0, i2);
                System.arraycopy(objArr, 0, this.f145e, 0, i2 << 1);
            }
            m1998d(iArr, objArr, i2);
        }
        if (this.f146f != i2) {
            throw new ConcurrentModificationException();
        }
    }

    public void clear() {
        int i = this.f146f;
        if (i > 0) {
            int[] iArr = this.f144d;
            Object[] objArr = this.f145e;
            this.f144d = C0114b.f122a;
            this.f145e = C0114b.f124c;
            this.f146f = 0;
            m1998d(iArr, objArr, i);
        }
        if (this.f146f > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return m1996f(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return m1994h(obj) >= 0;
    }

    /* renamed from: e */
    int m1997e(Object obj, int i) {
        int i2 = this.f146f;
        if (i2 == 0) {
            return -1;
        }
        int m2000b = m2000b(this.f144d, i2, i);
        if (m2000b >= 0 && !obj.equals(this.f145e[m2000b << 1])) {
            int i3 = m2000b + 1;
            while (i3 < i2 && this.f144d[i3] == i) {
                if (obj.equals(this.f145e[i3 << 1])) {
                    return i3;
                }
                i3++;
            }
            for (int i4 = m2000b - 1; i4 >= 0 && this.f144d[i4] == i; i4--) {
                if (obj.equals(this.f145e[i4 << 1])) {
                    return i4;
                }
            }
            return i3 ^ (-1);
        }
        return m2000b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0121d) {
            C0121d c0121d = (C0121d) obj;
            if (size() != c0121d.size()) {
                return false;
            }
            for (int i = 0; i < this.f146f; i++) {
                try {
                    K m1993i = m1993i(i);
                    V m1990l = m1990l(i);
                    Object obj2 = c0121d.get(m1993i);
                    if (m1990l == null) {
                        if (obj2 != null || !c0121d.containsKey(m1993i)) {
                            return false;
                        }
                    } else if (!m1990l.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (size() != map.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.f146f; i2++) {
                try {
                    K m1993i2 = m1993i(i2);
                    V m1990l2 = m1990l(i2);
                    Object obj3 = map.get(m1993i2);
                    if (m1990l2 == null) {
                        if (obj3 != null || !map.containsKey(m1993i2)) {
                            return false;
                        }
                    } else if (!m1990l2.equals(obj3)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    /* renamed from: f */
    public int m1996f(Object obj) {
        return obj == null ? m1995g() : m1997e(obj, obj.hashCode());
    }

    /* renamed from: g */
    int m1995g() {
        int i = this.f146f;
        if (i == 0) {
            return -1;
        }
        int m2000b = m2000b(this.f144d, i, 0);
        if (m2000b >= 0 && this.f145e[m2000b << 1] != null) {
            int i2 = m2000b + 1;
            while (i2 < i && this.f144d[i2] == 0) {
                if (this.f145e[i2 << 1] == null) {
                    return i2;
                }
                i2++;
            }
            for (int i3 = m2000b - 1; i3 >= 0 && this.f144d[i3] == 0; i3--) {
                if (this.f145e[i3 << 1] == null) {
                    return i3;
                }
            }
            return i2 ^ (-1);
        }
        return m2000b;
    }

    public V get(Object obj) {
        return getOrDefault(obj, null);
    }

    public V getOrDefault(Object obj, V v) {
        int m1996f = m1996f(obj);
        return m1996f >= 0 ? (V) this.f145e[(m1996f << 1) + 1] : v;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: h */
    public int m1994h(Object obj) {
        int i = this.f146f * 2;
        Object[] objArr = this.f145e;
        if (obj == null) {
            for (int i2 = 1; i2 < i; i2 += 2) {
                if (objArr[i2] == null) {
                    return i2 >> 1;
                }
            }
            return -1;
        }
        for (int i3 = 1; i3 < i; i3 += 2) {
            if (obj.equals(objArr[i3])) {
                return i3 >> 1;
            }
        }
        return -1;
    }

    public int hashCode() {
        int[] iArr = this.f144d;
        Object[] objArr = this.f145e;
        int i = this.f146f;
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            Object obj = objArr[i2];
            i4 += (obj == null ? 0 : obj.hashCode()) ^ iArr[i3];
            i3++;
            i2 += 2;
        }
        return i4;
    }

    /* renamed from: i */
    public K m1993i(int i) {
        return (K) this.f145e[i << 1];
    }

    public boolean isEmpty() {
        return this.f146f <= 0;
    }

    /* renamed from: j */
    public V m1992j(int i) {
        Object[] objArr = this.f145e;
        int i2 = i << 1;
        V v = (V) objArr[i2 + 1];
        int i3 = this.f146f;
        int i4 = 0;
        if (i3 <= 1) {
            m1998d(this.f144d, objArr, i3);
            this.f144d = C0114b.f122a;
            this.f145e = C0114b.f124c;
        } else {
            int i5 = i3 - 1;
            int[] iArr = this.f144d;
            if (iArr.length <= 8 || i3 >= iArr.length / 3) {
                if (i < i5) {
                    int i6 = i + 1;
                    int i7 = i5 - i;
                    System.arraycopy(iArr, i6, iArr, i, i7);
                    Object[] objArr2 = this.f145e;
                    System.arraycopy(objArr2, i6 << 1, objArr2, i2, i7 << 1);
                }
                Object[] objArr3 = this.f145e;
                int i8 = i5 << 1;
                objArr3[i8] = null;
                objArr3[i8 + 1] = null;
            } else {
                m2001a(i3 > 8 ? i3 + (i3 >> 1) : 8);
                if (i3 != this.f146f) {
                    throw new ConcurrentModificationException();
                }
                if (i > 0) {
                    System.arraycopy(iArr, 0, this.f144d, 0, i);
                    System.arraycopy(objArr, 0, this.f145e, 0, i2);
                }
                if (i < i5) {
                    int i9 = i + 1;
                    int i10 = i5 - i;
                    System.arraycopy(iArr, i9, this.f144d, i, i10);
                    System.arraycopy(objArr, i9 << 1, this.f145e, i2, i10 << 1);
                }
            }
            i4 = i5;
        }
        if (i3 == this.f146f) {
            this.f146f = i4;
            return v;
        }
        throw new ConcurrentModificationException();
    }

    /* renamed from: k */
    public V m1991k(int i, V v) {
        int i2 = (i << 1) + 1;
        Object[] objArr = this.f145e;
        V v2 = (V) objArr[i2];
        objArr[i2] = v;
        return v2;
    }

    /* renamed from: l */
    public V m1990l(int i) {
        return (V) this.f145e[(i << 1) + 1];
    }

    public V put(K k, V v) {
        int i;
        int m1997e;
        int i2 = this.f146f;
        if (k == null) {
            m1997e = m1995g();
            i = 0;
        } else {
            int hashCode = k.hashCode();
            i = hashCode;
            m1997e = m1997e(k, hashCode);
        }
        if (m1997e >= 0) {
            int i3 = (m1997e << 1) + 1;
            Object[] objArr = this.f145e;
            V v2 = (V) objArr[i3];
            objArr[i3] = v;
            return v2;
        }
        int i4 = m1997e ^ (-1);
        int[] iArr = this.f144d;
        if (i2 >= iArr.length) {
            int i5 = 4;
            if (i2 >= 8) {
                i5 = (i2 >> 1) + i2;
            } else if (i2 >= 4) {
                i5 = 8;
            }
            Object[] objArr2 = this.f145e;
            m2001a(i5);
            if (i2 != this.f146f) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.f144d;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.f145e, 0, objArr2.length);
            }
            m1998d(iArr, objArr2, i2);
        }
        if (i4 < i2) {
            int[] iArr3 = this.f144d;
            int i6 = i4 + 1;
            System.arraycopy(iArr3, i4, iArr3, i6, i2 - i4);
            Object[] objArr3 = this.f145e;
            System.arraycopy(objArr3, i4 << 1, objArr3, i6 << 1, (this.f146f - i4) << 1);
        }
        int i7 = this.f146f;
        if (i2 == i7) {
            int[] iArr4 = this.f144d;
            if (i4 < iArr4.length) {
                iArr4[i4] = i;
                Object[] objArr4 = this.f145e;
                int i8 = i4 << 1;
                objArr4[i8] = k;
                objArr4[i8 + 1] = v;
                this.f146f = i7 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public V putIfAbsent(K k, V v) {
        V v2 = get(k);
        return v2 == null ? put(k, v) : v2;
    }

    public V remove(Object obj) {
        int m1996f = m1996f(obj);
        if (m1996f >= 0) {
            return m1992j(m1996f);
        }
        return null;
    }

    public boolean remove(Object obj, Object obj2) {
        int m1996f = m1996f(obj);
        if (m1996f >= 0) {
            V m1990l = m1990l(m1996f);
            if (obj2 == m1990l || (obj2 != null && obj2.equals(m1990l))) {
                m1992j(m1996f);
                return true;
            }
            return false;
        }
        return false;
    }

    public V replace(K k, V v) {
        int m1996f = m1996f(k);
        if (m1996f >= 0) {
            return m1991k(m1996f, v);
        }
        return null;
    }

    public boolean replace(K k, V v, V v2) {
        int m1996f = m1996f(k);
        if (m1996f >= 0) {
            V m1990l = m1990l(m1996f);
            if (m1990l == v || (v != null && v.equals(m1990l))) {
                m1991k(m1996f, v2);
                return true;
            }
            return false;
        }
        return false;
    }

    public int size() {
        return this.f146f;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f146f * 28);
        sb.append('{');
        for (int i = 0; i < this.f146f; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            K m1993i = m1993i(i);
            if (m1993i != this) {
                sb.append(m1993i);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V m1990l = m1990l(i);
            if (m1990l != this) {
                sb.append(m1990l);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}