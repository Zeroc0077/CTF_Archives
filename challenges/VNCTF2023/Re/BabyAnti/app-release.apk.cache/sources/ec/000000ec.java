package com.VNCTF2023.anti_cheat;

import java.util.Map;
import java.util.Random;
import p027n.InterfaceC0504a;
import p043v.C0813i;
import p043v.C0814j;

/* loaded from: classes.dex */
public class AntiCheatPlugin implements InterfaceC0504a, C0814j.InterfaceC0818c {

    /* renamed from: a */
    private C0814j f147a;

    /* renamed from: b */
    public int f148b;

    /* renamed from: c */
    public int f149c;

    /* renamed from: d */
    public int f150d;

    /* renamed from: b */
    private boolean m1987b(int i, int i2) {
        return this.f148b == i && this.f149c == i2;
    }

    @Override // p043v.C0814j.InterfaceC0818c
    /* renamed from: a */
    public void mo183a(C0813i c0813i, C0814j.InterfaceC0819d interfaceC0819d) {
        Boolean valueOf;
        if (c0813i.f1452a.equals("saveMirrorValue")) {
            int nextInt = new Random().nextInt();
            this.f150d = nextInt;
            Map map = (Map) c0813i.f1453b;
            sendValue(nextInt ^ ((Integer) map.get("HP")).intValue(), ((Integer) map.get("Score")).intValue() ^ this.f150d);
            valueOf = Boolean.TRUE;
        } else if (!c0813i.f1452a.equals("cmpMirrorValue")) {
            interfaceC0819d.mo251c();
            return;
        } else {
            Map map2 = (Map) c0813i.f1453b;
            getValue();
            int i = this.f148b;
            int i2 = this.f150d;
            this.f148b = i ^ i2;
            this.f149c ^= i2;
            valueOf = Boolean.valueOf(m1987b(((Integer) map2.get("HP")).intValue(), ((Integer) map2.get("Score")).intValue()));
        }
        interfaceC0819d.mo252b(valueOf);
    }

    @Override // p027n.InterfaceC0504a
    /* renamed from: f */
    public void mo121f(InterfaceC0504a.C0506b c0506b) {
        this.f147a.m257e(null);
    }

    public native void getValue();

    @Override // p027n.InterfaceC0504a
    /* renamed from: h */
    public void mo119h(InterfaceC0504a.C0506b c0506b) {
        C0814j c0814j = new C0814j(c0506b.m964b(), "anti_cheat");
        this.f147a = c0814j;
        c0814j.m257e(this);
    }

    public native void sendValue(int i, int i2);
}