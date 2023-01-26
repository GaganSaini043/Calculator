package app.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class MainActivity : AppCompatActivity() {

    @BindView(R.id.tvInput)
    @JvmField
    var tvInput :  TextView? = null

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }

    @OnClick
    fun onDigit(view : View){
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    @OnClick
    fun onClear(view : View){
        tvInput?.text = ""
    }

    @OnClick(R.id.btn_decimal)
    fun onDecimalPoint(view : View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    @OnClick
    fun onOperator(view : View){
        tvInput?.text.let {
            if(lastNumeric && !onOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

     private fun onOperatorAdded(value : String) : Boolean{

         return if(value.startsWith("-")){
             false
         }
         else{
             value.contains("+") ||
                     value.contains("-") ||
                     value.contains("/") ||
                     value.contains("*")
         }

    }
}