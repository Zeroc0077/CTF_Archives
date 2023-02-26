package com.example.dino_run;

import android.os.Bundle;
import android.util.Log;
import io.flutter.embedding.android.ActivityC0184d;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes.dex */
public class MainActivity extends ActivityC0184d {
    static {
        System.loadLibrary("anticheat");
    }

    /* renamed from: P */
    private byte[] m1985P() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(getApplicationInfo().sourceDir)));
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                zipInputStream.close();
                zipInputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            if (nextEntry.getName().equals("classes.dex")) {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = zipInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
            }
            zipInputStream.closeEntry();
        }
    }

    private native int makeCRC32Digest(byte[] bArr, int i);

    /* renamed from: O */
    public void m1986O() {
        byte[] m1985P = m1985P();
        Log.i("Dex Digest", String.valueOf(makeCRC32Digest(m1985P, m1985P.length)));
    }

    @Override // io.flutter.embedding.android.ActivityC0184d, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            m1986O();
        } catch (IOException e) {
            Log.e("MainActivity", e.toString());
        }
    }
}