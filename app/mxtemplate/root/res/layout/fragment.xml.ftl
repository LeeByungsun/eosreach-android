<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.constraintlayout.widget.Guideline
        android:id="@+id/vertical_start_guideline"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        app:layout_constraintGuide_begin="@dimen/padding_large" />

    <androidx.constraintlayout.widget.Guideline
        android:id="@+id/vertical_end_guideline"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        app:layout_constraintGuide_end="@dimen/padding_large" />

    <androidx.constraintlayout.widget.Guideline
        android:id="@+id/horizontal_start_guideline"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        app:layout_constraintGuide_begin="@dimen/padding_large" />

    <androidx.constraintlayout.widget.Guideline
        android:id="@+id/horizontal_end_guideline"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        app:layout_constraintGuide_end="@dimen/padding_large" />

    <ProgressBar
        android:id="@+id/${layoutElementName}_progress"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        app:layout_constraintLeft_toLeftOf="@id/vertical_start_guideline"
        app:layout_constraintRight_toRightOf="@id/vertical_end_guideline"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
