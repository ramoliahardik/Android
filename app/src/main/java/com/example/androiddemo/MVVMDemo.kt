package com.example.androiddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.components.Lazy
import kotlinx.android.synthetic.main.activity_mvvmdemo.*
import java.lang.StringBuilder

data class Quote(val quoteText: String, val author: String) {
    override fun toString(): String {
        return "$quoteText - $author"
    }
}

class FakeQuoteDao {
    private val quoteList = mutableListOf<Quote>()
    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.value = quoteList
    }

    fun addQuote(quote: Quote) {
        quoteList.add(quote)
        quotes.value = quoteList
    }

    fun getQuotes() = quotes as LiveData<List<Quote>>
}

class FakeDatabase private constructor() {

    var quoteDao = FakeQuoteDao()

    companion object {
        @Volatile
        private var instance: FakeDatabase? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: FakeDatabase().also { instance = it }
            }
    }
}

class QuoteRepository private constructor(private val quoteDao: FakeQuoteDao) {

    companion object {
        @Volatile var instance : QuoteRepository? = null

        fun getInstance(quoteDao: FakeQuoteDao) =
            instance ?: synchronized(this) {
                instance ?: QuoteRepository(quoteDao).also { instance = it }
            }
    }

    fun addQuote(quote: Quote) {
        quoteDao.addQuote(quote)
    }

    fun getQuotes() = quoteDao.getQuotes()
}

class QuoteViewModel(val quoteRepository: QuoteRepository): ViewModel() {
    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)
    fun getQuote() = quoteRepository.getQuotes()
}

class QuoteViewModelFactory(val quoteRepository: QuoteRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuoteViewModel(quoteRepository) as T
    }
}

object InjectorUtils {
    fun provideFactory(): QuoteViewModelFactory {
        val quoteRepository = QuoteRepository.getInstance(FakeDatabase.getInstance().quoteDao)
        return QuoteViewModelFactory(quoteRepository)
    }
}

class MVVMDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvmdemo)

        val factory = InjectorUtils.provideFactory()
        val viewModel = ViewModelProvider(this, factory).get(QuoteViewModel::class.java)

        viewModel.getQuote().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach { quote ->
                stringBuilder.append(quote)
            }
            tvMVVM.text = stringBuilder.toString()
        })

        btnMVVMAdd.setOnClickListener {
            val quote = Quote(etMVVMQuote.text.toString(), etMVVMName.text.toString())
            viewModel.addQuote(quote)
            etMVVMQuote.setText("")
            etMVVMName.setText("")
        }
    }
}