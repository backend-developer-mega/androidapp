package com.learning.games.education.kidspower.kidsDatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.learning.games.education.kidspower.kidsModel.kidsModelVideo;
import com.learning.games.education.kidspower.kidsCustom.kidsConstant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0006\u0010\u0012\u001a\u00020\u0011J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J \u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R!\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/kidslearning/kidsplay/kidsgames/kidseducation/preschool/database/DatabaseHelper;", "Landroid/database/sqlite/SQLiteOpenHelper;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext$app_debug", "()Landroid/content/Context;", "setContext$app_debug", "videoDetails", "Ljava/util/ArrayList;", "Lcom/kidslearning/kidsplay/kidsgames/kidseducation/preschool/model/ModelVideo;", "Lkotlin/collections/ArrayList;", "getVideoDetails", "()Ljava/util/ArrayList;", "checkDataBase", "", "copyDataBase", "", "createDataBase", "onCreate", "db", "Landroid/database/sqlite/SQLiteDatabase;", "onUpgrade", "oldVersion", "", "newVersion", "app_debug"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* compiled from: DatabaseHelper.kt */
public final class kidsDatabaseHelper extends SQLiteOpenHelper {
    private Context context;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public kidsDatabaseHelper(Context context2) {
        super(context2, kidsConstant.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, kidsConstant.DATABASE_VERSION);
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
    }

    public final Context getContext$app_debug() {
        return this.context;
    }

    public final void setContext$app_debug(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "<set-?>");
        this.context = context2;
    }

    public final ArrayList<kidsModelVideo> getVideoDetails() {
        ArrayList arrPfVideoDetails = new ArrayList();
        Cursor cursor = getWritableDatabase().rawQuery(Intrinsics.stringPlus("SELECT * FROM kids WHERE id=", kidsConstant.VIDEO_CATEGORY_ID), (String[]) null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            kidsModelVideo modelVideo = new kidsModelVideo();
            String videoDescription = cursor.getString(cursor.getColumnIndexOrThrow(kidsConstant.VIDEO));
            Intrinsics.checkNotNullExpressionValue(videoDescription, "videoDescription");
//            modelVideo.setVideoId((String) StringsKt.split$default((CharSequence) videoDescription, new String[]{"#"}, false, 0, 6, (Object) null).get(0));
            modelVideo.setVideoId((String) StringsKt.split(videoDescription,new String[]{"#"},false,0).get(0));
            Intrinsics.checkNotNullExpressionValue(videoDescription, "videoDescription");
//            modelVideo.setVideoTitle((String) StringsKt.split$default((CharSequence) videoDescription, new String[]{"#"}, false, 0, 6, (Object) null).get(1));
            modelVideo.setVideoTitle((String) StringsKt.split(videoDescription,new String[]{"#"},false,0).get(1));
            StringBuilder sb = new StringBuilder();
            sb.append("https://i3.ytimg.com/vi/");
            Intrinsics.checkNotNullExpressionValue(videoDescription, "videoDescription");
//            sb.append((String) StringsKt.split$default((CharSequence) videoDescription, new String[]{"#"}, false, 0, 6, (Object) null).get(0));
            sb.append((String) StringsKt.split(videoDescription,new String[]{"#"},false,0).get(0));
            sb.append("/hqdefault.jpg");
            modelVideo.setVideoThumb(sb.toString());
            arrPfVideoDetails.add(modelVideo);
            cursor.moveToNext();
        }
        return arrPfVideoDetails;
    }

    public void onCreate(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Intrinsics.checkNotNullParameter(db, "db");
    }

    public final void createDataBase() throws IOException {
        if (checkDataBase()) {
            Log.e("TAG", "createDataBase:::::If:::: ");
            return;
        }
        Log.e("TAG", "createDataBase:::::Else::: ");
        getWritableDatabase();
        getReadableDatabase();
        close();
        copyDataBase();
    }

    private final boolean checkDataBase() {
        return new File(Intrinsics.stringPlus(kidsConstant.DB_PATH, kidsConstant.DATABASE_NAME)).exists();
    }

    private final void copyDataBase() throws IOException {
        InputStream myInput = this.context.getAssets().open(Intrinsics.stringPlus("databases/", kidsConstant.DATABASE_NAME));
        Intrinsics.checkNotNullExpressionValue(myInput, "context.assets.open(\"databases/\" + Constant.DATABASE_NAME)");
        FileOutputStream myOutput = new FileOutputStream(Intrinsics.stringPlus(kidsConstant.DB_PATH, kidsConstant.DATABASE_NAME));
        byte[] buffer = new byte[1024];
        while (myInput.read(buffer) > 0) {
            myOutput.write(buffer);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }
}
