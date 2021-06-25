package com.cyborg.paging.presentation.common

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class DebounceEditText(context: Context, attributeSet: AttributeSet) :
    AppCompatEditText(context, attributeSet) {

    companion object {
        private const val DE_BOUNCE_TIME_IN_MILLISECONDS = 300L
    }


    private var debounceTextChangeListener: TextChangeListener? = null
    private var subject: PublishSubject<Editable> = PublishSubject.create()
    private var disposable: Disposable =
        subject.debounce(DE_BOUNCE_TIME_IN_MILLISECONDS,
            TimeUnit.MILLISECONDS,
            AndroidSchedulers.mainThread())
            .subscribe { editable: Editable? ->
                debounceTextChangeListener?.onTextChange(editable)
            }

    private val debounceTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            //NO-OP
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            //NO-OP
        }

        override fun afterTextChanged(s: Editable) {
            subject.onNext(s)
        }
    }

    fun registerDebounceTextChangeListener(debounceTextChangeListener: TextChangeListener?) {
        this.debounceTextChangeListener = debounceTextChangeListener

        if (debounceTextChangeListener == null) {
            this.removeTextChangedListener(debounceTextWatcher)
        } else {
            this.addTextChangedListener(debounceTextWatcher)
        }
    }

    fun deregisterDebounceCustomTextChangeListener() {
        subject.onComplete()
        disposable.dispose()
        removeTextChangedListener(debounceTextWatcher)
    }

    interface TextChangeListener {
        fun onTextChange(editable: Editable?)
    }
}