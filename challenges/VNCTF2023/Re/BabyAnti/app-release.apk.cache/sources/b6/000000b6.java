package p003b;

import java.util.HashMap;
import p003b.C0073b;

/* renamed from: b.a */
/* loaded from: classes.dex */
public class C0072a<K, V> extends C0073b<K, V> {

    /* renamed from: h */
    private HashMap<K, C0073b.C0076c<K, V>> f102h = new HashMap<>();

    @Override // p003b.C0073b
    /* renamed from: c */
    protected C0073b.C0076c<K, V> mo2126c(K k) {
        return this.f102h.get(k);
    }

    public boolean contains(K k) {
        return this.f102h.containsKey(k);
    }

    @Override // p003b.C0073b
    /* renamed from: f */
    public V mo2123f(K k) {
        V v = (V) super.mo2123f(k);
        this.f102h.remove(k);
        return v;
    }
}