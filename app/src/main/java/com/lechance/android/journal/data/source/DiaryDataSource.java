package com.lechance.android.journal.data.source;

import android.support.annotation.NonNull;

import com.lechance.android.journal.data.bean.Diary;

import java.util.List;

/**
 * This class was Created by lechance
 */
public interface DiaryDataSource {


    void getDiarys(@NonNull LoadDiarysCallback callback);

    void getDiary(@NonNull String diaryId, @NonNull GetDiaryCallback callback);

    void saveDiary(@NonNull Diary diary);

    void refreshDiarys();

    void deleteAllDiarys();

    void deleteDiary(@NonNull String diaryId);

    interface LoadDiarysCallback {
        void onDiaryLoad(List<Diary> list);

        void onDataNotAvailable();
    }

    interface GetDiaryCallback {
        void onDiaryLoaded(Diary diary);

        void onDataNotAvailable();
    }
}
