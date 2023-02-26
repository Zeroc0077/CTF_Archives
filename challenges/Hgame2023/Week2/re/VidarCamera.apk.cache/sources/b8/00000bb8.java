package com.example.android.camera2.basic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.android.camera2.basic.databinding.ActivityCameraBinding;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraActivity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\u000bH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0010"}, d2 = {"Lcom/example/android/camera2/basic/CameraActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "activityCameraBinding", "Lcom/example/android/camera2/basic/databinding/ActivityCameraBinding;", "encrypt", "Lkotlin/UIntArray;", "enc", "encrypt-hkIa6DI", "([I)[I", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "Companion", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class CameraActivity extends AppCompatActivity {
    public static final long ANIMATION_FAST_MILLIS = 50;
    public static final long ANIMATION_SLOW_MILLIS = 100;
    public static final Companion Companion = new Companion(null);
    public static final int FLAGS_FULLSCREEN = 4357;
    private static final long IMMERSIVE_FLAG_TIMEOUT = 500;
    private ActivityCameraBinding activityCameraBinding;

    /* renamed from: encrypt-hkIa6DI  reason: not valid java name */
    private final int[] m8encrypthkIa6DI(int[] iArr) {
        int i;
        int[] m175constructorimpl = UIntArray.m175constructorimpl(4);
        UIntArray.m186setVXSXFK8(m175constructorimpl, 0, 2233);
        UIntArray.m186setVXSXFK8(m175constructorimpl, 1, 4455);
        UIntArray.m186setVXSXFK8(m175constructorimpl, 2, 6677);
        UIntArray.m186setVXSXFK8(m175constructorimpl, 3, 8899);
        int i2 = 0;
        while (i2 < 9) {
            int i3 = 0;
            int i4 = 0;
            do {
                i3++;
                i = i2 + 1;
                UIntArray.m186setVXSXFK8(iArr, i2, UInt.m122constructorimpl(UIntArray.m181getpVg5ArA(iArr, i2) + UInt.m122constructorimpl(UInt.m122constructorimpl(UInt.m122constructorimpl(UIntArray.m181getpVg5ArA(m175constructorimpl, UInt.m122constructorimpl(i4 & 3)) + i4) ^ UInt.m122constructorimpl(UInt.m122constructorimpl(UInt.m122constructorimpl(UIntArray.m181getpVg5ArA(iArr, i) << 4) ^ UInt.m122constructorimpl(UIntArray.m181getpVg5ArA(iArr, i) >>> 5)) + UIntArray.m181getpVg5ArA(iArr, i))) ^ i4)));
                UIntArray.m186setVXSXFK8(iArr, i, UInt.m122constructorimpl(UIntArray.m181getpVg5ArA(iArr, i) + UInt.m122constructorimpl(UInt.m122constructorimpl(UInt.m122constructorimpl(UInt.m122constructorimpl(UIntArray.m181getpVg5ArA(iArr, i2) << 4) ^ UInt.m122constructorimpl(UIntArray.m181getpVg5ArA(iArr, i2) >>> 5)) + UIntArray.m181getpVg5ArA(iArr, i2)) ^ UInt.m122constructorimpl(UIntArray.m181getpVg5ArA(m175constructorimpl, UInt.m122constructorimpl(UInt.m122constructorimpl(i4 >>> 11) & 3)) + i4))));
                i4 = UInt.m122constructorimpl(i4 + 878077251);
            } while (i3 <= 32);
            i2 = i;
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityCameraBinding inflate = ActivityCameraBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.activityCameraBinding = inflate;
        if (inflate != null) {
            setContentView(inflate.getRoot());
            CameraActivity cameraActivity = this;
            final EditText editText = new EditText(cameraActivity);
            final AlertDialog create = new AlertDialog.Builder(cameraActivity).setTitle("请输入序列号").setView(editText).setNeutralButton("buy serial number", (DialogInterface.OnClickListener) null).setPositiveButton("check", (DialogInterface.OnClickListener) null).setCancelable(false).create();
            create.show();
            create.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.example.android.camera2.basic.-$$Lambda$CameraActivity$H7vbOQZH_iHcmul3P8UWGZPgvEc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CameraActivity.m9onCreate$lambda0(editText, this, create, view);
                }
            });
            create.getButton(-3).setOnClickListener(new View.OnClickListener() { // from class: com.example.android.camera2.basic.-$$Lambda$CameraActivity$svU5YW1WhTtdEXyl8GuRGORdsYw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CameraActivity.m10onCreate$lambda1(CameraActivity.this, view);
                }
            });
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("activityCameraBinding");
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m9onCreate$lambda0(EditText inputsomething, CameraActivity this$0, AlertDialog alertDialog, View view) {
        Intrinsics.checkNotNullParameter(inputsomething, "$inputsomething");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String obj = inputsomething.getText().toString();
        if (obj.length() != 40) {
            Toast.makeText(this$0, "序列号不正确", 0).show();
            return;
        }
        int[] m175constructorimpl = UIntArray.m175constructorimpl(10);
        for (int i = 0; i < 40; i += 4) {
            UIntArray.m186setVXSXFK8(m175constructorimpl, i / 4, UInt.m122constructorimpl(UInt.m122constructorimpl(UInt.m122constructorimpl(UInt.m122constructorimpl(obj.charAt(i)) + UInt.m122constructorimpl(obj.charAt(i + 1) << '\b')) + UInt.m122constructorimpl(obj.charAt(i + 2) << 16)) + UInt.m122constructorimpl(obj.charAt(i + 3) << 24)));
        }
        int[] m8encrypthkIa6DI = this$0.m8encrypthkIa6DI(m175constructorimpl);
        UInt[] uIntArr = {UInt.m116boximpl(637666042), UInt.m116boximpl(457511012), UInt.m116boximpl(-2038734351), UInt.m116boximpl(578827205), UInt.m116boximpl(-245529892), UInt.m116boximpl(-1652281167), UInt.m116boximpl(435335655), UInt.m116boximpl(733644188), UInt.m116boximpl(705177885), UInt.m116boximpl(-596608744)};
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            if (uIntArr[i2].m173unboximpl() != UIntArray.m181getpVg5ArA(m8encrypthkIa6DI, i2)) {
                Toast.makeText(this$0, "序列号不正确", 0).show();
                return;
            } else if (i3 > 9) {
                alertDialog.dismiss();
                return;
            } else {
                i2 = i3;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-1  reason: not valid java name */
    public static final void m10onCreate$lambda1(CameraActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setData(Uri.parse("https://www.google.com/search?q=%E5%AE%89%E5%8D%93%E9%80%86%E5%90%91&newwindow=1&sxsrf=ALiCzsaz5ChqTv6BNFCqfuwvl4nHRpyCtw%3A1673016303320&ei=7zO4Y8CQE5iB-AbUz4HgDA&ved=0ahUKEwiAxNmzl7P8AhWYAN4KHdRnAMwQ4dUDCA8&uact=5&oq=%E5%AE%89%E5%8D%93%E9%80%86%E5%90%91&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQAzIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQ6BAgjECc6EQguEIAEELEDEIMBEMcBENEDOgsIABCABBCxAxCDAToICC4QsQMQgwE6CgguEMcBENEDEEM6CwguEIAEEMcBENEDOgUILhCABDoECAAQQzoICAAQsQMQgwE6BwgAEIAEEAw6BggAEAQQHjoJCAAQBBAeEPEEOggIABAIEAQQHjoKCAAQCBAEEB4QCjoICAAQCBAeEAw6BwgAEIAEEApKBAhBGABKBAhGGABQAFiAHmC-KmgEcAB4AYABpgWIAZwWkgELMC44LjEuMC4yLjGYAQCgAQHAAQE&sclient=gws-wiz-serp"));
        this$0.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        ActivityCameraBinding activityCameraBinding = this.activityCameraBinding;
        if (activityCameraBinding != null) {
            activityCameraBinding.fragmentContainer.postDelayed(new Runnable() { // from class: com.example.android.camera2.basic.-$$Lambda$CameraActivity$54f3_efRvoOQ9VbJMgP6XvXkJlA
                @Override // java.lang.Runnable
                public final void run() {
                    CameraActivity.m11onResume$lambda2(CameraActivity.this);
                }
            }, IMMERSIVE_FLAG_TIMEOUT);
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("activityCameraBinding");
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onResume$lambda-2  reason: not valid java name */
    public static final void m11onResume$lambda2(CameraActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityCameraBinding activityCameraBinding = this$0.activityCameraBinding;
        if (activityCameraBinding != null) {
            activityCameraBinding.fragmentContainer.setSystemUiVisibility(FLAGS_FULLSCREEN);
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("activityCameraBinding");
            throw null;
        }
    }

    /* compiled from: CameraActivity.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/example/android/camera2/basic/CameraActivity$Companion;", "", "()V", "ANIMATION_FAST_MILLIS", "", "ANIMATION_SLOW_MILLIS", "FLAGS_FULLSCREEN", "", "IMMERSIVE_FLAG_TIMEOUT", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}